package <%= packageName %>.validator;

import com.genesis.common.validator.GenericValidator;
import <%=packageName %>.dto.<%=entityName%>DTO;
import org.springframework.stereotype.Component;

@Component
public class <%=entityName%>Validator implements GenericValidator<<%=entityName%>DTO> {

    @Override
    public void validate(<%=entityName%>DTO dto) {
        
    }
}
