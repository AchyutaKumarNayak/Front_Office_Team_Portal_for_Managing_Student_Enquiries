package in.achyuta.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "AIT_STUDENT_ENQUIRY")
public class StudentEnquiryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE )
	private Integer enquiryId;
	private String studentName;
	private Long studentPhn;
	private String classMode;
	private String courseName;
	private String enquiryStatus;
	private LocalDate createdDate;
	private LocalDate updatedDate;
	@ManyToOne
    @JoinColumn(name = "user_id")
	private UserEntity user;
	
	

}
