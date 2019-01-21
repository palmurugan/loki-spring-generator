package <%=packageName %>.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.genesis.common.domain.Auditable;
import com.genesis.common.annotation.Unique;
import <%= packageName %>.service.impl.<%= entityName%>ServiceImpl;
<% if(typeof mappings !== 'undefined') { %> <% mappings.forEach(mapping => { %>
import <%= packageName %>.domain.<%= mapping.entity%>;
<% }); %><%}%>

@Entity
@Table(name = "<%= entityName.toLowerCase() %>")
public class <%= entityName %> extends Auditable<String> implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique=true, nullable= false)
	private Long id;
     
    <% attributes.forEach(attribute => { %>
    @Column(name = "<%= attribute.name.toLowerCase() %>"<% if(attribute.unique) {%>, unique=true<%}%><%if(!attribute.nullable) {%>, nullable=false<%}%>)
    private <%= attribute.type %> <%= attribute.name %>;
    <% }); %><% if(typeof mappings !== 'undefined') { %> <% mappings.forEach(mapping => { %>
    @<%=mapping.type%>(<%if(typeof mapping.type=='OneToMany'){%>cascade = CascadeType.ALL<%}%>)
    @JoinColumn(name = "<%=mapping.joinColumn%>") <% if(typeof mapping.type === 'OneToMany') {%>
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
