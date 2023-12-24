package project.EntityDAO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateLogic {
	
	public LocalDate getDate(String date_str)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		LocalDate ld = null;
		try {
		
			Date dt = sdf.parse(date_str);

			ld = dt.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			DateFormat dtf = null;
			
			System.out.println(ld);
		
		} catch (ParseException e) {
			
			e.printStackTrace();
		
		}

		return ld;
	}
	

}
