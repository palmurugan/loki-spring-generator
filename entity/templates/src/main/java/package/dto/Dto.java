package <%=packageName %>.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

<% if(typeof mappings !== 'undefined') { %> <% mappings.forEach(mapping => { %>
import <%= packageName %>.dto.<%= mapping.entity%>DTO;
<% }); %><%}%>

import lombok.Data;

@Data
public class <%=entityName%>DTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
     
    <% attributes.forEach(attribute => { %>
    <% if(!attribute.nullable) {%>
    @NotNull(message = "<%= attribute.name.toLowerCase() %> is required")<% } %><% if(!attribute.nullable && attribute.type == 'String'){%>
	@NotBlank(message = "<%= attribute.name.toLowerCase() %> should not be empty")<% } %><% if(attribute.minLength != null) { %> 
    @Size(min=<%=attribute.minLength%>, message="<%= attribute.name %> should have atleast <%=attribute.minLength%> characters")<% } %><% if(attribute.maxLength != null) { %>
    @Size(max=<%=attribute.maxLength%>, message="<%= attribute.name %> should not have more than <%=attribute.maxLength%> characters")<%}%><% if(attribute.regex != null) { %>
    @Pattern(regexp = "<%= attribute.regex.pattern %>",message="<%= attribute.regex.errorMessage %>")<%}%>
    private <%= attribute.type %> <%= attribute.name %>;
    <% }); %><% if(typeof mappings !== 'undefined') { %> <% mappings.forEach(mapping => { %>
    @<%=mapping.type%>(<%if(typeof mapping.type=='OneToMany'){%>cascade = CascadeType.ALL<%}%>)
    <% if(typeof mapping.type === 'OneToMany') {%>
    private Set<<%=mapping.entity %>> <%=mapping.placeholder%>;<% } else {%>
    private <%=mapping.entity %> <%=mapping.placeholder%>; <%}%>
    <% }); %><%}%>
}
