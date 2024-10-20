package in.achyuta.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import in.achyuta.constants.AppConstants;
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
@Table(name = AppConstants.STUDENT_ENQUIRY_ENTITY_TBL)
public class StudentEnquiryEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE )
	private Integer enquiryId;
	private String studentName;
	private Long studentPhn;
	private String courseMode;
	private String courseName;
	private String enquiryStatus;
	@CreationTimestamp
	private LocalDate createdDate;
	@UpdateTimestamp
	private LocalDate updatedDate;
	@ManyToOne
    @JoinColumn(name = AppConstants.STUDENT_ENQUIRY_USER_FK)
	private UserEntity user;
	
	

}
