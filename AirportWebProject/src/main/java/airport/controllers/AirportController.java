package airport.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import airport.entities.Flight;
import airport.entities.Traveler;
import airport.exceptions.FlightNotFoundException;
import airport.exceptions.FullFlightException;
import airport.exceptions.TravelerAlreadyExistsException;
import airport.exceptions.TravelerNotFoundException;
import airport.service.TravelerService;

@Controller
public class AirportController {
	@Autowired
	private TravelerService service;

	@RequestMapping("/showMainScreen")
	public String showAllDestenation(Model model, HttpServletRequest request) {
		try {
			System.out.println(service.getTraveler());
			List<String> destenations = service.getDestinations();
			model.addAttribute("destenations", destenations);
		}catch (Exception e) {//user didnt select flight
			return "redirect:showMainScreen";
		}
		return "main_page"; // show destenation flights
	}

	@RequestMapping("/showFlightsToDestination")
	public String showFlightsToDestination(Model model, HttpServletRequest request) {

		String flightName = request.getParameter("chosenFlight");
		service.setDestination(flightName);
		try {
			List<String> dests = service.getDestinations();
			model.addAttribute("destenations", dests);
			List<Flight> destenations = service.showFlightsToDestinations(flightName);
			model.addAttribute("flightDestenations", destenations);
			}catch(FlightNotFoundException fnfe){
			model.addAttribute("flightNfound2","please select a flight to continue");
			return "main_page";
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "dest_flights"; // show all flights to selected destination
	}

	@RequestMapping("/proccessActions")
	public String processAction(@RequestParam(value="flight",required=false) String id, HttpServletRequest request, Model model) {
		System.out.println(id);
		String option = request.getParameter("option");
		int decision = 0;
		if (option.equals("Add to flight"))
			decision = 1;
		if (option.equals("Remove from flight"))
			decision = 2;
		if (option.equals("Show your flights"))
			decision = 3;
		if (option.equals("Show all flights"))
			decision = 4;
		if (option.equals("Log out"))
			decision = 0;
		if(decision == 3 || decision == 4)
		{
			return actions(decision,0,model);// 0 for invalid id
		}
		if(id == null)
		{
			try {
				List<Flight> dests = service.showFlightsToDestinations(service.getDestination());
				model.addAttribute("flightDestenations", dests);
			} catch (Exception e) {
				e.printStackTrace();
				return "redirect:showMainScreen";
			}
			model.addAttribute("flightNfound1", "please select a flight for this action");
			return "dest_flights";
		}
		return actions(decision, Integer.parseInt(id), model);
	}

	public String actions(int decision, int destId, Model model) {
		Traveler t = service.getTraveler();
		switch (decision) {
		case 1:
			try {
				service.addTravelerToFlight(destId, t);
				model.addAttribute("addFlight",service.get(destId));
				model.addAttribute("destFlight",service.getDestination());
			} catch (FullFlightException ffe) {
				model.addAttribute("exception", ffe.getMessage());
				decision = 5;
			} catch (TravelerAlreadyExistsException taee) {
				model.addAttribute("exception", taee.getMessage());
				decision = 5;
			} catch (FlightNotFoundException fnfe) {
				model.addAttribute("exception", fnfe.getMessage());
				decision = 5;
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 2:

			try {
				service.removeTravelerFromFlight(destId, t);
				model.addAttribute("removeFlight",service.get(destId));
			} catch (TravelerNotFoundException tnfe) {
				model.addAttribute("exception", tnfe.getMessage());
				decision = 5;
			} catch (FlightNotFoundException fnfe) {
				model.addAttribute("exception", fnfe.getMessage());
				decision = 5;
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 3:

			try {
				Flight travelerFlight = service.getTravelerFlight(t, destId);
				model.addAttribute("travelerFlight",travelerFlight);
			} catch (TravelerNotFoundException tnfe) {
				model.addAttribute("exception", tnfe.getMessage());
				decision = 5;
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case 4:
			return "redirect:/showMainScreen";

		case 0:
			System.out.println("Exited.");
			break;
		}
		model.addAttribute("action", decision);
		return "result";
	}

}
