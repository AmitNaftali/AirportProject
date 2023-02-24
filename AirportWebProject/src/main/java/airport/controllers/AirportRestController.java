package airport.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import airport.entities.Flight;
import airport.exceptions.FlightNotFoundException;
import airport.service.TravelerService;

@RestController
public class AirportRestController {

	@Autowired
	private TravelerService service;

	@GetMapping("/showMainScreen/flights/{flightId}")
	public Flight getFlight(@PathVariable int flightId) throws Exception {
		Flight flight = null;
		List<Flight> flights = null;
		try {
			flights = service.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flightId >= flights.size() || flightId < 1)
			throw new FlightNotFoundException("Flight with id - " + flightId + " could not be found");
		flight = flights.get(flightId - 1);
		return flight;
	}

	@GetMapping("/showMainScreen/flights")
	public List<Flight> getFlights() {
		List<Flight> flights = null;
		try {
			flights = service.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flights;
	}

	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(Exception e) {

		// Create a studentErrorResponse

		StudentErrorResponse error = new StudentErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(),

				System.currentTimeMillis());

		// Return ResponseEntity

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}

}
