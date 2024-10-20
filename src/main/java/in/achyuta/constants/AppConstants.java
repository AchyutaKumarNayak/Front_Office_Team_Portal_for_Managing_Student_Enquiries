package in.achyuta.constants;

public class AppConstants {
	
	private AppConstants() {}
	
	public static final String INDEX_CONTROLLER_INDEX                      ="index";
	public static final String SESSION_USER_ID                             ="userId";
	public static final String STUDENT_ENQUIRY_CONTROLLER_DASHBOARD_RES    ="dashboardRes";
	public static final String STUDENT_ENQUIRY_CONTROLLER_DASHBOARD        ="dashboard";
	public static final String STUDENT_ENQUIRY_CONTROLLER_COURSRES_NAME    ="coursesName";
	public static final String STUDENT_ENQUIRY_CONTROLLER_ENQS_STS         ="enquiriesStatus";
	public static final String STUDENT_ENQUIRY_CONTROLLER_ADD_ENQS         ="addEnquiry";
	public static final String STUDENT_ENQUIRY_CONTROLLER_STUDENT_ENQ_FORM ="studentEnquiryForm";
	public static final String STUDENT_ENQUIRY_CONTROLLER_STUDENT_ENQS     ="enquiries";
	public static final String STUDENT_ENQUIRY_CONTROLLER_STUDENT_VIEW_ENQ ="viewEnquiry";
	public static final String STUDENT_ENQUIRY_CONTROLLER_STUDENT_FILTER_ENQ="filter-enquiries";
	public static final String STUDENT_ENQUIRY_CONTROLLER_STUDENT_FILT_ENQ ="filterEnquiry";
	public static final String SUCCESS_MSG_KEY           ="succMsg";
	public static final String ERR_MSG_KEY               ="errMsg";
	public static final String USER_CONTROLLER_USER      ="user";
	public static final String USER_CONTROLLER_SIGNUP    ="signup";
	public static final String USER_CONTROLLER_UNLOCK    ="unlock";
	public static final String USER_CONTROLLER_LOGIN     ="login";
	public static final String USER_CONTROLLER_LOGIN_FORM="loginForm";
	public static final String USER_CONTROLLER_SUCCESS   ="success";
	public static final String USER_CONTROLLER_FORGOT_PWD="forgotPwd";
	public static final String USER_CONTROLLER_EMAIL     ="email";
	public static final String SUCCESS_MSG_SIGNUP_VALUE  ="Check your Email";
	public static final String SUCCESS_MSG_UNLOCK_VALUE  ="Account unlocked Succesfully";
	public static final String SUCCESS_MSG_FORGOT_VALUE  ="Password is send to ";
	public static final String ERR_MSG_SIGNUP_VALUE      ="Choose unique email";
	public static final String ERR_MSG_UNLOCK_VALUE1     ="Temporary password is incorrect , Please check your email";
	public static final String ERR_MSG_UNLOCK_VALUE2     ="New password and Confirm password must be same";
	public static final String ERR_MSG_FORGOT_VALUE      ="Invalid Credentials";
	public static final String SUCCESS_MSG_ENQ_VALUE     ="Details added Succesfully";
	public static final String ERR_MSG_ENQ_VALUE         ="Problem occured while saving details";
	public static final String COURSE_ENTITY_TBL         ="AIT_COURSE_DTLS";
	public static final String ENQUIRY_STATUS_ENTITY_TBL ="AIT_ENQUIRY_STATUS";
	public static final String STUDENT_ENQUIRY_ENTITY_TBL="AIT_STUDENT_ENQUIRY";
	public static final String USER_ENTITY_TBL           ="AIT_USER_DTLS";
	public static final String STUDENT_ENQUIRY_USER_FK   ="user_id";
	public static final String USER_STUDENT_ENQUIRY_FK   ="user";
	public static final String ENQ_SERVICE_FILTER_ENROLLED  ="Enrolled";
	public static final String ENQ_SERVICE_FILTER_LOST      ="Lost";
	public static final String USER_SERVICE_ERR_LOGIN_VALUE ="Invalid Credentials";
	public static final String USER_SERVICE_ERR_LOCKED_VALUE="Your Account is Locked";
	public static final String USER_SERVICE_LOCKED          ="LOCKED";
	public static final String USER_SERVICE_UNLOCKED        ="UNLOCKED";
	public static final String USER_SERVICE_EMAIL_SUB       ="Your Temporary Password is send to your mail";
	public static final String USER_SERVICE_EMPTY_STR       ="";
	public static final String USER_SERVICE_EMAIL_BODY1     ="<h1>Unlock your account by using below temporary password </h1>";
	public static final String USER_SERVICE_EMAIL_BODY2     ="Your Temporary password is   ";
	public static final String USER_SERVICE_EMAIL_BODY3     ="<br/>";
	public static final String USER_SERVICE_EMAIL_BODY4     ="<a href=http://localhost:8080/unlock?email=";
	public static final String USER_SERVICE_EMAIL_BODY5     ="> Click here to Unlock your Account</a>";
	public static final String USER_SERVICE_FORGOT_EMAIL_SUB="Recovery of password";
	public static final String UTIL_RAND_PWD_GEN_CHARS      ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	public static final String RUNNER_COURSE_NAME1="Java";
	public static final String RUNNER_COURSE_NAME2="Python";
	public static final String RUNNER_COURSE_NAME3="AWS";
	public static final String RUNNER_COURSE_NAME4="DevOps";
	public static final String RUNNER_STS1        ="New";
	public static final String RUNNER_STS2        ="Enrolled";
	public static final String RUNNER_STS3        ="Lost";
	
	//USERCONTROLLER MAPPING
	public static final String USER_CONTROLLER_MAPPING_SIGNUP="/signup";
	public static final String USER_CONTROLLER_MAPPING_UNLOCK="/unlock";
	public static final String USER_CONTROLLER_MAPPING_LOGIN="/login";
	public static final String USER_CONTROLLER_MAPPING_FORGOT="/forgot";
	
	//StudentEnquiryController Mapping
	public static final String STD_ENQ_CONTROLLER_MAPPING_LOGOUT="/logout";
	public static final String STD_ENQ_CONTROLLER_MAPPING_DASHBOARD="/dashboard";
	public static final String STD_ENQ_CONTROLLER_MAPPING_ENQUIRY="/enquiry";
	public static final String STD_ENQ_CONTROLLER_MAPPING_ENQUIRIES="/enquires";
	
	//IndexController Mapping
	public static final String INDEX_CONTROLLER_MAPPING="/";

	


}
