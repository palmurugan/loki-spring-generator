<%#
 Copyright 2019-2020 the original author or authors from the Loki project.

 This file is part of the Loki project.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
-%>
package <%=packageName %>.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genesis.common.service.impl.GenericServiceImpl;
import <%=packageName %>.repository.<%=entityName%>Repository;
import <%=packageName %>.domain.<%=entityName%>;
import <%= packageName %>.service.<%= entityName%>Service;

@Service
public class <%= entityName%>ServiceImpl extends GenericServiceImpl<<%= entityName%>, Long> implements <%= entityName%>Service {

	@Autowired
	private <%=entityName%>Repository reference;

	public <%= entityName%>ServiceImpl(<%=entityName%>Repository reference) {
		super(reference);
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
