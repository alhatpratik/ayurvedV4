package project.RestController;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.Entity.Login;
import project.model.Sub_login;
import project.repositories.LoginInteface;

@RestController
@RequestMapping(path = "/ayurved")
@CrossOrigin("*")
public class LoginController {
	
	@Autowired
	LoginInteface log_interface;

	@PostMapping("/login/user")
	@CrossOrigin("*")
	public Object checkLogin(@RequestBody Sub_login log)
	{
		System.out.println("in");
		String userName = log.getUname();
		String pass = log.getPassword();
		System.out.println(userName+"    "+pass);
		Login l = log_interface.getLogin(userName,pass);
		
		if(l !=null)
		{
			return l;
		}
		else
		{
			return "Incorrect Details";
		}

	}
	
}
