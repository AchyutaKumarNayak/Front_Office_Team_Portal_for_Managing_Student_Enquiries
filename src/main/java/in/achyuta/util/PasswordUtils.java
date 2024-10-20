package in.achyuta.util;

import org.apache.commons.lang3.RandomStringUtils;

import in.achyuta.constants.AppConstants;

public class PasswordUtils {
	
	public static String getTempPassword() {
		String characters = AppConstants.UTIL_RAND_PWD_GEN_CHARS;
		String pwd = RandomStringUtils.random( 6, characters );
		return pwd;
	}

}
