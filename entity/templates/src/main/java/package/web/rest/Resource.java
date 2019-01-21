package <%= packageName %>.web.rest;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genesis.common.controller.AbstractRestController;
import <%= packageName %>.dto.<%= entityName%>DTO;
import <%= packageName %>.service.<%= entityName%>Service;
import <%= packageName %>.validator.<%= entityName%>Validator;


@RestController
@RequestMapping("/<%=entityName.toLowerCase()%>s")
public class <%= entityName%>Resource extends AbstractRestController<<%= entityName%>DTO, Long> {

	@Inject
    public <%= entityName%>Resource(<%= entityName%>Service service, <%=entityName%>Validator validator) {
		  super(service, validator);
    }
}
