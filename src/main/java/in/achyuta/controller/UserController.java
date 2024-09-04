package in.achyuta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	@GetMapping("/login")
	public String logInPage() {
		return "login";
	}
	@GetMapping("/signup")
	public String signUpPage() {
		return "signup";
	}
	@GetMapping("/forgot")
	public String forgotPwdPage() {
		return "forgotPwd";
	}

}
