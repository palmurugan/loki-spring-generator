package <%= packageName %>.web.rest;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genesis.common.controller.AbstractRestController;
import <%= packageName %>.domain.<%= entityName%>;
import <%= packageName %>.service.<%= entityName%>Service;


@RestController
@RequestMapping("/<%= entityName.toLowerCase() %>")
public class <%= entityName%>Resource extends AbstractRestController<<%= entityName%>, Long> {

	@Inject
    public <%= entityName%>Resource(<%= entityName%>Service ref) {
		  super(ref);
    }
}
