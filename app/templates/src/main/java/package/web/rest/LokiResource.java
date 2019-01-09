package <%=packageName %>.web.rest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loki")
public class LokiResource {

	@RequestMapping(method = RequestMethod.GET)
	public String getStatus() {
		return "UP!";
	}

}
