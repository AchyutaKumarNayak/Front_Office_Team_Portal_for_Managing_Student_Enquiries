package in.achyuta.entity;

import in.achyuta.constants.AppConstants;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = AppConstants.ENQUIRY_STATUS_ENTITY_TBL)
public class EnquiryStatusEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer statusId;
	private String statusName;

}
