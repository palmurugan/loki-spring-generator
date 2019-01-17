package <%=packageName %>.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import <%=packageName %>.dto.<%=entityName%>DTO;
import com.genesis.common.service.impl.GenericServiceImpl;
import <%=packageName %>.repository.<%=entityName%>Repository;
import <%=packageName %>.domain.<%=entityName%>;
import <%=packageName %>.mapper.<%=entityName%>Mapper;
import <%= packageName %>.service.<%= entityName%>Service;

@Service
public class <%= entityName%>ServiceImpl extends GenericServiceImpl<<%= entityName%>, <%=entityName%>DTO, Long> implements <%= entityName%>Service {

	@Autowired
	private <%=entityName%>Repository reference;

	@Autowired
	private <%=entityName%>Mapper mapper;

	public <%= entityName%>ServiceImpl(<%=entityName%>Repository reference, <%=entityName%>Mapper mapper) {
		super(reference, mapper);
	}

	@Override
	public boolean fieldValueExists(Long id, Object value, String fieldName) throws UnsupportedOperationException {
		<%= entityName%> entityReference = null;
		if (id != null) {<% attributes.forEach(attribute => { if(attribute.unique) { %>
			if (fieldName.equals("<%=attribute.name%>")) {
				entityReference = reference.findBy<%=attribute.name.charAt(0).toUpperCase()+ attribute.name.slice(1)%>(value.toString());
			}<%} }); %>
			if (entityReference != null && !entityReference.getId().equals(id)) {
				return Boolean.TRUE;
			}
		} else { <% attributes.forEach(attribute => { if(attribute.unique) { %>
			if (fieldName.equals("<%=attribute.name%>")) {
				return reference.existsBy<%=attribute.name.charAt(0).toUpperCase()+ attribute.name.slice(1)%>(value.toString());
			}<%} }); %>	
		}
		return Boolean.FALSE;
	}
}
