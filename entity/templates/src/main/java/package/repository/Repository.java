package <%= packageName %>.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import <%= packageName %>.domain.<%= entityName%>;

@Repository
public interface <%= entityName%>Repository extends PagingAndSortingRepository<<%= entityName%>, Long> {
     <% attributes.forEach(attribute => { if(attribute.unique) { %>
     public boolean existsBy<%=attribute.name.charAt(0).toUpperCase()+ attribute.name.slice(1)%>(String value);
	public <%= entityName%> findBy<%=attribute.name.charAt(0).toUpperCase()+ attribute.name.slice(1)%>(String value);<% } %>
     <% }); %>
}
