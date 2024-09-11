package in.achyuta.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.achyuta.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {
	
	public UserEntity findByUserEmail(String email);
	public UserEntity findByUserEmailAndPassword(String email,String password);

}
