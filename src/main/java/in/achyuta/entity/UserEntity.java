package in.achyuta.entity;

import java.util.List;

import in.achyuta.constants.AppConstants;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = AppConstants.USER_ENTITY_TBL)
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer userId;
	private String userName;
	private String userEmail;
	private String password;
	private Long userPhn;
	private String accountStatus;
	
	@OneToMany(mappedBy = AppConstants.USER_STUDENT_ENQUIRY_FK,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<StudentEnquiryEntity> enquires;
	

}
