package in.achyuta.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StudentEnquiryForm {
	
	private String studentName;
	private Long studentPhn;
	private String courseMode;
	private String courseName;
	private LocalDate createdDate;
	private String enquiryStatus;

}
