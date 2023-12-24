package project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import project.Entity.Login;

public interface LoginInteface extends JpaRepository<Login, Long> {

	@Query("select u from Login u where u.userName=:uname and u.password=:pass")
	public Login getLogin(String uname,String pass); 

}
