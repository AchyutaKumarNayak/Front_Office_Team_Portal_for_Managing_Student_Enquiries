package in.achyuta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import in.achyuta.constants.AppConstants;

@Controller
public class IndexController {
	
	
	@GetMapping(AppConstants.INDEX_CONTROLLER_MAPPING)
	public String getIndex() {
		return AppConstants.INDEX_CONTROLLER_INDEX;
	}

}
