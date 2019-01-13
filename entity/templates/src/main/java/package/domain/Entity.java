package <%=packageName %>.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

import com.genesis.common.domain.Auditable;
import com.genesis.common.annotation.Unique;
import <%= packageName %>.service.impl.<%= entityName%>ServiceImpl;
<% if(typeof mappings !== 'undefined') { %> <% mappings.forEach(mapping => { %>
import <%= packageName %>.domain.<%= mapping.entity%>;
<% }); %><%}%>
import lombok.Data;

@Entity
@Table(name = "<%= entityName.toLowerCase() %>")
@Unique.List({<% attributes.forEach(attribute => { if(attribute.unique) {%>
	@Unique(service = <%= entityName%>ServiceImpl.class, fieldName = "<%=attribute.name%>", message = "<%=attribute.name%> already exists"),<%} }); %>
})
@Data
public class <%= entityName %> extends Auditable<String> implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique=true, nullable= false)
	private Long id;
     
    <% attributes.forEach(attribute => { %>
    @Column(name = "<%= attribute.name.toLowerCase() %>"<% if(attribute.unique) {%>, unique=true<%}%><%if(!attribute.nullable) {%>, nullable=false<%}%>)<% if(!attribute.nullable) {%>
    @NotNull(message = "<%= attribute.name.toLowerCase() %> is required")<% } %><% if(!attribute.nullable && attribute.type == 'String'){%>
	@NotBlank(message = "<%= attribute.name.toLowerCase() %> should not be empty")<% } %><% if(attribute.minLength != null) { %> 
    @Size(min=<%=attribute.minLength%>, message="<%= attribute.name %> should have atleast <%=attribute.minLength%> characters")<% } %><% if(attribute.maxLength != null) { %>
    @Size(max=<%=attribute.maxLength%>, message="<%= attribute.name %> should not have more than <%=attribute.maxLength%> characters")<%}%><% if(attribute.regex != null) { %>
    @Pattern(regexp = "<%= attribute.regex.pattern %>",message="<%= attribute.regex.errorMessage %>")<%}%>
    private <%= attribute.type %> <%= attribute.name %>;
    <% }); %><% if(typeof mappings !== 'undefined') { %> <% mappings.forEach(mapping => { %>
    @<%=mapping.type%>(cascade = CascadeType.ALL)
    private Set<<%=mapping.entity %>> <%=mapping.placeholder%>; 
    <% }); %><%}%>
}
