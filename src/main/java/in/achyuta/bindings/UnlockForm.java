package in.achyuta.bindings;

import lombok.Data;

@Data
public class UnlockForm {
	
	private String userEmail;
	private String tempPassword;
	private String newPassword;
	private String confirmPassword;
	

}
