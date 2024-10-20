package in.achyuta.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.achyuta.entity.StudentEnquiryEntity;

@Repository
public interface StudentEnquiryRepo extends JpaRepository<StudentEnquiryEntity, Integer> {
    
}
