package airport.cli;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import airport.entities.Flight;
import airport.service.TravelerService;

@Controller
public class AirportController {
	@Autowired
	private TravelerService service;
	
	@RequestMapping("/showMainScreen")
	public String showAllDestenation(Model model,HttpServletRequest request)
	{
		try {
			List<String> destenations = service.getDestinations();
			destenations.add("1");
			destenations.add("2");
			model.addAttribute("destenations", destenations);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "main_page"; //show destenation flights
	}
	@RequestMapping("/showFlightsToDestination")
	 public String showFlightsToDestination (HttpServletRequest request){


	        String flightName= request.getParameter("chosenFlight");
	        System.out.println(flightName);
	        try {
	            List<Flight> destenation = service.showFlightsToDestinations(flightName);
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return ""; //show all flights to selected destination
	    }
}
