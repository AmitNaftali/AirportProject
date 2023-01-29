package airport.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import airport.service.TravelerService;

@Controller
public class FlightController {
	@Autowired
	private TravelerService service;
	
	
}
