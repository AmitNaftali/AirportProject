package airport.cli;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import airport.service.TravelerService;

@Controller
public class AirportController {
	@Autowired
	private TravelerService service;
	
	@RequestMapping("/showMainScreen")
	public String showAllDestenation(@ModelAttribute("user") User user,HttpServletRequest request)
	{
		try {
			List<String> destenations = service.getDestinations();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ""; //show destenation flights
	}
}
