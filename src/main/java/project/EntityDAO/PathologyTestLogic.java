package project.EntityDAO;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.Entity.Pathological_Tests;
import project.repositories.Pathological_TestsRepo;

@Component
public class PathologyTestLogic {
	
	@Autowired
	Pathological_TestsRepo pathological_test_repo;
	
	public List<Pathological_Tests> getAll_X_Ray_Tests(LocalDate date)
	{
		List<Pathological_Tests> pathology_list = pathological_test_repo.getAll_X_Ray_Tests(date);
		return pathology_list;
	}

	public List<Pathological_Tests> getAll_ECG_Tests(LocalDate date)
	{
		List<Pathological_Tests> pathology_list = pathological_test_repo.getAll_ECG_Tests(date);
		return pathology_list;
	}

}
