package airport.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import airport.dal.FileDao;
import airport.entities.Flight;
import airport.entities.Traveler;
import airport.exceptions.FlightAlreadyExistException;
import airport.exceptions.FlightNotFoundException;
import airport.exceptions.FullAirportException;
import airport.exceptions.FullFlightException;
import airport.exceptions.TravelerAlreadyExistsException;
import airport.exceptions.TravelerNotFoundException;


@Component
public class TravelerService {
	@Autowired
	private FileDao dependency;
	private Traveler traveler;
	private String destination;
	
	private final int maxTravelers = 30;
    private final int maxFlights = 30;
    private final int maxDestinations = 10;
	
	public List<Flight> getAll() throws Exception {
		/*Flight f1 = new Flight(1, 13, 22, new Plane("707",35), "Japan");
		dependency.save(f1);
		Flight f2 = new Flight(2, 20, 16, new Plane("747",30), "Japan");
		dependency.save(f2);
		Flight f3 = new Flight(3, 13, 20, new Plane("757",28), "New York");
		dependency.save(f3);
		Flight f4 = new Flight(4, 20, 22, new Plane("737",30), "Paris");
		dependency.save(f4);
		Flight f5 = new Flight(5, 21, 23, new Plane("727",22), "New York");
		dependency.save(f5);
		Flight f6 = new Flight(6, 9, 16, new Plane("717",30), "Paris");
		dependency.save(f6);*/
		/*dependency.delete(1);
		dependency.delete(2);
		dependency.delete(3);
		dependency.delete(4);
		dependency.delete(5);
		dependency.delete(6);*/
		return dependency.getAll();
	}

	public void save(Flight flight) throws Exception {
		if((dependency.getAll()).contains(flight))
			throw new FlightAlreadyExistException("flight: " + flight + " already exist");
		if(maxFlights <= dependency.getAll().size())
			throw new FullAirportException("cannot add flight. airport has reached max flights:" + maxFlights);
		if(maxDestinations <= getDestinations().size())
			throw new FullAirportException("cannot add flight. airport has reached max destinations:" + maxDestinations);
		
		dependency.save(flight);
	}

	public void update(Flight flightChange) throws Exception {
		for(Flight flight : dependency.getAll())
			if(flight.getId() == flightChange.getId()) {
				
				 dependency.update(flightChange);
				 return;
			}
		throw new FlightNotFoundException("flight: " + flightChange + "could not found");
	}

	public void delete(int flightId) throws Exception {
		for(Flight flight : dependency.getAll())
			if(flight.getId() == flightId) {
				 dependency.delete(flightId);
				 return;
			}
		throw new FlightNotFoundException("flight: with id:" + flightId + "could not found");
	}

	public Flight get(int flightId) throws Exception {
		for(Flight flight : dependency.getAll())
			if(flight.getId() == flightId) {
				
				 return dependency.get(flightId);
			}
		throw new FlightNotFoundException("flight: with id:" + flightId + "could not found");
	}
	public void addTravelerToFlight(int flightId,Traveler traveler) throws Exception {
		if(traveler == null)
			throw new TravelerNotFoundException("traveler does not exist in system!");
		Traveler t = dependency.getTraveler(traveler.getFullName());
		if(t.getFlight() != null)
			throw new TravelerAlreadyExistsException("traveler:" + t + " has already register to a flight");
		Flight flight = dependency.get(flightId);
		if(flight == null)
			throw new FlightNotFoundException("flight: with id:" + flightId + "could not found");
		if (flight.getTravelers().size() == maxTravelers) {
			throw new FullFlightException("cannot add traveler:" + t +" flight is full");
		}
		if(flight.getTravelers().contains(t))
			throw new TravelerAlreadyExistsException("traveler:" + t + " already exist in flight");
		dependency.addTravelerToFlight(flight.getId(),t);
		System.out.println("traveler added");
	}
	public void removeTravelerFromFlight(int flightId,Traveler traveler) throws Exception {
		if(traveler == null)
			throw new TravelerNotFoundException("traveler does not exist in system!");
		Traveler t = dependency.getTraveler(traveler.getFullName());
		Flight flight = dependency.get(flightId);
		if(flight == null)
			throw new FlightNotFoundException("flight: with id:" + flightId + " could not be found");
		if (flight.getTravelers().size() == 0) 
			throw new TravelerNotFoundException("could not found traveler" + t + " in flight");
		dependency.removeTravelerFromFlight(flight.getId(),t);
		System.out.println("traveler removed");
	}
	
	
	public List<String> getDestinations() throws Exception
	{
		ArrayList<String> list = new ArrayList<String>();
		for(Flight f : dependency.getAll())
			if(!list.contains(f.getDestination()))
				list.add(f.getDestination());
		return list;
	}
	
	public List<Flight> showFlightsToDestinations(String destination) throws Exception {
		String printFlights = "";
		int count = 1;
		ArrayList<Flight> flights = new ArrayList<Flight>();
		for(Flight flight : dependency.getAll())
			if(flight.getDestination().equals(destination)) {
				printFlights += count++ + ".";
				printFlights += flight.toString();
				printFlights += "\n";
				flights.add(flight);
			}
		if(flights.size() == 0)
			throw new FlightNotFoundException("flight with destination:" + destination + " does not exist");
		System.out.println(printFlights);
		return flights;	
	}

	public List<Flight> getTravelerFlights(Traveler traveler,int destId) throws Exception{
		if(traveler == null)
			throw new TravelerNotFoundException("traveler does not exist in system!");
		ArrayList<Flight> flights = new ArrayList<Flight>();
		for(Flight flight : dependency.getAll()) {
			if(flight.getId() == destId && flight.getTravelers().contains(traveler))
				flights.add(flight);
		}
		if(flights.isEmpty())
			throw new TravelerNotFoundException("could not found traveler" + traveler + " in with id flights to " + destId + "!");
		return flights;
	}
	
	public Traveler findTraveler(String name) throws Exception
	{
		return dependency.getTraveler(name);
	}
	
	public void saveTraveler(Traveler traveler) throws Exception
	{
		dependency.saveTraveler(traveler);
	}
	
	
	
	
	public Traveler getTraveler() {
		return traveler;
	}

	public void setTraveler(Traveler traveler) {
		this.traveler = traveler;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

}
