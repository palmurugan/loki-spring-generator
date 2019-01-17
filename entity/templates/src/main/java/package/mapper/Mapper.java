package <%=packageName %>.mapper;

import com.genesis.common.mapper.GenericMapper;

import <%=packageName %>.domain.<%=entityName%>;
import <%=packageName %>.dto.<%=entityName%>DTO;

import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper extends GenericMapper<<%=entityName%>, <%=entityName%>DTO> {

}