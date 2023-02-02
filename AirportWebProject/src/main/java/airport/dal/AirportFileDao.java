package airport.dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import airport.entities.Airport;
import airport.entities.Flight;



@Component
public class AirportFileDao implements FileDao{

	@Override
	public List<Flight> getAll() throws Exception {
		fillAirport();
		return Airport.getInstance().getFlights();
	}

	@Override
	public void save(Flight flight) throws Exception {
		Airport.getInstance().addFlight(flight);
		serialize();
	}

	@Override
	public void update(Flight flight) throws Exception {
		fillAirport();
		Airport.getInstance().updateFlight(flight);
		serialize();
	}

	@Override
	public void delete(int flightId) throws Exception {
		fillAirport();
		Airport.getInstance().deleteFlight(flightId);
		serialize();
	}

	@Override
	public Flight get(int flightId) throws Exception {
		fillAirport();
		return Airport.getInstance().getFlight(flightId);
	}
	
	private void fillAirport() throws Exception
	{
		try {
			deserialize();
		}catch(Exception e)//file empty
		{
			serialize();
		}
	}

	private void serialize() throws Exception
	{
		File file = ResourceUtils.getFile("classpath:airport.dat");
		FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        // Method for serialization of object
        out.writeObject(Airport.getInstance());
        out.close();
        fileOut.close();   
	}
	private void deserialize() throws Exception
	{
		// Add the student object as a model attribute
		// Deserialization
		File file = ResourceUtils.getFile("classpath:airport.dat");
		FileInputStream fileInput = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileInput);
        // Method for deserialization of object
        Airport.setAirport((Airport)in.readObject());
        in.close();
        fileInput.close();
	}	
}
