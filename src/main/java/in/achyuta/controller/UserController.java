package in.achyuta.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.achyuta.bindings.LoginForm;
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
	public String unlockUserAccount(@ModelAttribute("unlock") UnlockForm form, Model model) {
		
		if((form.getNewPassword()).equals(form.getConfirmPassword())) {
			boolean status = userService.unlock(form);
			if(status) {
				model.addAttribute("succMsg", "Account unlocked Succesfully");
			}else {
				model.addAttribute("errMsg", "Temporary password is incorrect , Please check your email");
			}
		}else {
			model.addAttribute("errMsg", "New password and Confirm password must be same");
		}
//		System.out.println(form);
		return "unlock";
	}
	
	@GetMapping("/login")
	public String logInPage(Model model){
		 model.addAttribute("loginForm", new LoginForm());
		return "login";
	}
	@PostMapping("/login")
	public String logInPageHandle(@ModelAttribute("login") LoginForm form,Model model){
		String status = userService.login(form);
		if(status.contains("success")) {
			return "redirect:/dashboard";
		}else {
			model.addAttribute("errMsg", status);
		}
		
		return "login";
	}
	
	
	@GetMapping("/forgot")
	public String forgotPwdPage() {
		return "forgotPwd";
	}
	@PostMapping("/forgot")
	public String forgotPwdPageHandle(@RequestParam("email") String email,Model model) {
		System.out.println(email);
		boolean status = userService.fotgot(email);
		if (status) {
			model.addAttribute("succMsg", "Password is send to "+email);
		}else {
			model.addAttribute("errMsg","Invalid Credentials");
		}
		return "forgotPwd";
	}
	
}
