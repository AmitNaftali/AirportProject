package airport.cli;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import airport.entities.Flight;
import airport.entities.Traveler;
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
	
	@RequestMapping("")
    public String showFlightsToDestination (HttpServletRequest request, Model model){


        String flightName= request.getParameter("flightName");

        try {
            List<Flight> destenation = service.showFlightsToDestinations(flightName);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ""; //show all flights to selected destination
    }
	
	@RequestMapping("/actions")
    public String showActions()
    {
        return "show-actions"; //show actions flights
    }
    // Process the form
    @RequestMapping("/processAction")

    public String processAction(@ModelAttribute("flight") Flight flight,@ModelAttribute("user") User user ,HttpServletRequest request,Model modle) {
        int action = (int) request.getAttribute("action");
        return "hello-student";
    }

    public String actions(int decision,TravelerService service,Flight dest,User user) throws Exception {
        Traveler t = new Traveler(Integer.parseInt(user.getPassword()),user.getUsername());
        switch(decision) {
        case 1:
            service.addTravelerToFlight(dest.getId(), t);
            return"";//add page
        case 2:
            service.removeTravelerFromFlight(dest.getId(), t);
            return"";//del page
        case 3:
            return "" ;//show traveler fligthts
        case 4:
            return "show-destinations";
        case 0:
            System.out.println("Exited.");
            return "log_out";
        }
        return "";
    }
}
