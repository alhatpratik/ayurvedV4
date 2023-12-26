package project.RestController;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.Entity.Pathological_Tests;
import project.Entity.Patient;
import project.EntityDAO.PathologyTestLogic;
import project.model.Summary;

@RestController
@RequestMapping("/ayurved")
@CrossOrigin("*")
public class PathologyTestController {

	
	@Autowired
	PathologyTestLogic pathology_test_logic;
	
	@PostMapping("/pathology/tests/xray")
	@CrossOrigin("*")
	public List<Pathological_Tests> get_pathology_x_ray_tests(@RequestBody Patient p)
	{
		LocalDate date = p.getDate();
		
		List<Pathological_Tests> pathology_list = pathology_test_logic.getAll_X_Ray_Tests(date);

		return pathology_list;
	}
	
	@PostMapping("/pathology/tests/ecg")
	@CrossOrigin("*")
	public List<Pathological_Tests> get_pathology_ECG_tests(@RequestBody Patient p)
	{
		LocalDate date = p.getDate();
		
		List<Pathological_Tests> pathology_list = pathology_test_logic.getAll_ECG_Tests(date);

		return pathology_list;
	}
}
