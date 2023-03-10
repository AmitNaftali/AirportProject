package airport.dal;

import java.util.List;

import org.springframework.stereotype.Component;

import airport.entities.Flight;
import airport.entities.Traveler;

@Component
public interface FileDao {
	public List<Flight> getAll() throws Exception;
	public void save(Flight a) throws Exception;
	public void update(Flight a) throws Exception;
	public void delete(int id) throws Exception;
	public Flight get(int id) throws Exception;
	public Traveler getTraveler(String travelerName) throws Exception;
	public void saveTraveler(Traveler traveler) throws Exception;
	public void addTravelerToFlight(int flightId, Traveler traveler) throws Exception;
	public void removeTravelerFromFlight(int flightId, Traveler traveler) throws Exception;
}
