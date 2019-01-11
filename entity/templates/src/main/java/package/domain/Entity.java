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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.genesis.common.annotation.Unique;
import com.genesis.common.domain.Auditable;
import com.genesis.common.annotation.Unique;
import com.genesis.common.validator.ValidationGroup;
import <%= packageName %>.service.impl.<%= entityName%>ServiceImpl;
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
	@NotBlank(message = "<%= attribute.name.toLowerCase() %> should not be empty")<% } %>
    private <%= attribute.type %> <%= attribute.name %>;
   <% }); %>
}
