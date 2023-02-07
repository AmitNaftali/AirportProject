package airport.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import airport.dal.FileDao;
import airport.entities.Airport;
import airport.entities.Flight;
import airport.entities.Plane;
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
	private int maxTravelers = 30;
    private int maxFlights = 30;
    private int maxDestinations = 10;
	
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
		//update flight
		for(Flight flight : dependency.getAll())
			if(flight.getId() == flightId) {
				if (flight.getTravelers().size() == maxTravelers) {
					throw new FullFlightException("cannot add traveler:" + traveler +" flight is full");
				}
				if(flight.getTravelers().contains(traveler))
					throw new TravelerAlreadyExistsException("traveler:" + traveler + " already exist in flight");
				
				flight.addTraveler(traveler);
				dependency.update(flight);
				System.out.println("traveler added");
				return;
			}
		throw new FlightNotFoundException("flight: with id:" + flightId + "could not found");
	}
	public void removeTravelerFromFlight(int flightId,Traveler traveler) throws Exception {
		for(Flight flight : dependency.getAll())
			if(flight.getId() == flightId) {
				if (flight.getTravelers().size() == 0 || !flight.removeTraveler(traveler)) {
					throw new TravelerNotFoundException("could not found traveler" + traveler + " in flight");
				} 
				
				dependency.update(flight);
				System.out.println("traveler removed");
				return;
			}
		throw new FlightNotFoundException("flight: with id:" + flightId + "could not found");
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
	
	@PreDestroy
	@PostConstruct
	public void containerStartUp()  throws Exception{
		getAll();
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
		System.out.println(Airport.getInstance());
	}

	public int getMaxTravelers() {
		return maxTravelers;
	}

	public void setMaxTravelers(int maxTravelers) {
		this.maxTravelers = maxTravelers;
	}

	public int getMaxFlights() {
		return maxFlights;
	}

	public void setMaxFlights(int maxFlights) {
		this.maxFlights = maxFlights;
	}
	
	public int getMaxDestinations() {
		return maxDestinations;
	}

	public void setMaxDestinations(int maxDestinations) {
		this.maxDestinations = maxDestinations;
	}
	public List<Flight> getTravelerFlights(Traveler traveler,int destId) throws Exception{
		ArrayList<Flight> flights = new ArrayList<Flight>();
		for(Flight flight : dependency.getAll()) {
			if(flight.getId() == destId && flight.getTravelers().contains(traveler))
				flights.add(flight);
		}
		if(flights.isEmpty())
			throw new TravelerNotFoundException("could not found traveler" + traveler + " in with id flights to " + destId + "!");
		return flights;
	}
}
