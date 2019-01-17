package <%=packageName %>.service;

import java.util.Optional;

import com.genesis.common.service.FieldValueExist;
import com.genesis.common.service.IGenericService;
import <%=packageName %>.dto.<%=entityName%>DTO;

public interface <%=entityName%>Service extends IGenericService<<%=entityName%>DTO, Long>, FieldValueExist {

}
