package in.achyuta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.achyuta.bindings.SignUpForm;
import in.achyuta.bindings.UnlockForm;
import in.achyuta.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/signup")
	public String signUpPage(Model model) {
		//when my sign up page is loading  i want to send my empty binding Obj
		model.addAttribute("user", new SignUpForm());//key(th:object), new empty binding Obj
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signUpPage(@ModelAttribute("user") SignUpForm form,Model model) {
		boolean status = userService.signUp(form);
		if (status) {
			model.addAttribute("succMsg", "Check your Email");
		}
		else {
			model.addAttribute("errMsg", "Choose unique email");
		}
		return "signup";
	}
	
	@GetMapping("/unlock")
	public String unlockPage(@RequestParam String email,Model model) {
		
		UnlockForm unlockForm= new UnlockForm();
		unlockForm.setUserEmail(email);
		model.addAttribute("unlock", unlockForm);
		return "unlock";
	}
	@PostMapping("/unlock")
	public String unlockPage(@ModelAttribute("unlock") UnlockForm form, Model model) {
		
		
		return "unlock";
	}
	
	@GetMapping("/login")
	public String logInPage() {
		return "login";
	}
	
	
	@GetMapping("/forgot")
	public String forgotPwdPage() {
		return "forgotPwd";
	}

}
