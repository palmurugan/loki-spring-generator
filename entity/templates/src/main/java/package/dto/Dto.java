package <%=packageName %>.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import com.genesis.common.annotation.Unique;
import <%= packageName %>.service.impl.<%= entityName%>ServiceImpl;
<% if(typeof mappings !== 'undefined') { %> <% mappings.forEach(mapping => { %>
import <%= packageName %>.dto.<%= mapping.entity%>DTO;
<% }); %><%}%>
@Unique.List({<% attributes.forEach(attribute => { if(attribute.unique) {%>
	@Unique(service = <%= entityName%>ServiceImpl.class, fieldName = "<%=attribute.name%>", message = "<%=attribute.name%> already exists"),<%} }); %>
})
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
    @Valid
    @<%=mapping.type%>(<%if(typeof mapping.type=='OneToMany'){%>cascade = CascadeType.ALL<%}%>)
    <% if(typeof mapping.type === 'OneToMany') {%>
    private Set<<%=mapping.entity %>> <%=mapping.placeholder%>;<% } else {%>
    private <%=mapping.entity %> <%=mapping.placeholder%>; <%}%>
    <% }); %><%}%>

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    <% attributes.forEach(attribute => { %>
    public <%=attribute.type%> get<%=attribute.name.charAt(0).toUpperCase()+ attribute.name.slice(1)%>() {
        return <%=attribute.name%>;
    }

    public void set<%=attribute.name.charAt(0).toUpperCase()+ attribute.name.slice(1)%>(<%=attribute.type%> <%=attribute.name%>) {
        this.<%=attribute.name%> = <%=attribute.name%>;
    }
    <% }); %>

    <% if(typeof mappings !== 'undefined') { %><% mappings.forEach(mapping => { %>
    <%if(typeof mapping.type=='OneToMany'){ %>
    public Set<<%=mapping.entity%>> get<%=mapping.placeholder.charAt(0).toUpperCase()+ mapping.placeholder.slice(1)%>() {
        return <%=mapping.placeholder%>;
    }

    public void set<%=mapping.placeholder.charAt(0).toUpperCase()+ mapping.placeholder.slice(1)%>(Set<<%=mapping.entity%>> <%=mapping.placeholder%>) {
        this.<%=mapping.placeholder%> = <%=mapping.placeholder%>;
    }
    <%} else {%>
    public <%=mapping.entity%> get<%=mapping.placeholder.charAt(0).toUpperCase()+ mapping.placeholder.slice(1)%>() {
        return <%=mapping.placeholder%>;
    }

    public void set<%=mapping.placeholder.charAt(0).toUpperCase()+ mapping.placeholder.slice(1)%>(<%=mapping.entity%> <%=mapping.placeholder%>) {
        this.<%=mapping.placeholder%> = <%=mapping.placeholder%>;
    }
    <% } %>
    <% }); }%>
}
