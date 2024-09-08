package in.achyuta.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.achyuta.entity.EnquiryStatusEntity;

@Repository
public interface EnquiryStatusRepo extends JpaRepository<EnquiryStatusEntity, Integer>{

}
