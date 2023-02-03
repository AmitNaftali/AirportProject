package airport.cli;

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
			List<String> destenations = service.getDestinations();
			model.addAttribute("destenations", destenations);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "main_page"; // show destenation flights
	}

	@RequestMapping("/showFlightsToDestination")
	public String showFlightsToDestination(Model model, HttpServletRequest request) {

		String flightName = request.getParameter("chosenFlight");
		try {
			List<Flight> destenations = service.showFlightsToDestinations(flightName);
			model.addAttribute("flightDestenations", destenations);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "dest_flights"; // show all flights to selected destination
	}

	@RequestMapping("/proccessActions")
	public String processAction(@RequestParam("flight") String id, HttpServletRequest request, Model model) {
		int decision = 0;
		String option = request.getParameter("option");
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
		User user = (User) request.getSession().getAttribute("user");
		return actions(decision, user, Integer.parseInt(id), model);
	}

	public String actions(int decision, User user, int destId, Model model) {
		// Traveler t = new
		// Traveler(Integer.parseInt(user.getPassword()),user.getUsername());
		Traveler t = new Traveler(1, "1");
		model.addAttribute("action", decision);
		switch (decision) {
		case 1:

			try {
				service.addTravelerToFlight(destId, t);
			} catch (FullFlightException ffe) {

			} catch (TravelerAlreadyExistsException taee) {

			} catch (FlightNotFoundException fnfe) {

			} catch (Exception e) {
				e.printStackTrace();

				return "";
			}
		case 2:

			try {
				service.removeTravelerFromFlight(destId, t);
			} catch (TravelerNotFoundException tnfe) {

			} catch (FlightNotFoundException fnfe) {

			} catch (Exception e) {
				e.printStackTrace();

				return "";
			}
		case 3:

			try {
				service.getTravelerFlights(t, destId);
			} catch (TravelerNotFoundException tnfe) {

			} catch (Exception e) {
				e.printStackTrace();

				return ""; // errors
			}

		case 4:
			return "redirect:/showMainScreen";

		case 0:
			System.out.println("Exited.");
			return "result";
		}
		return "";
	}

}
