package airport.dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import airport.entities.Airport;
import airport.entities.Flight;
import airport.entities.Traveler;

@Component
public class AirportFileDao implements FileDao {
	private SessionFactory factory;

	@Override
	public List<Flight> getAll() throws Exception {
		// Create session
		Session session = factory.openSession();
		session.beginTransaction();
		
		List<Flight> list = session.createQuery("from Flight").getResultList();
		for(Flight f : list)
		{
			System.out.println(f.getTravelers());
		}
		
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@Override
	public void save(Flight flight) throws Exception {
		Session session = factory.openSession();
		session.beginTransaction();
		
		session.save(flight);
		
		session.getTransaction().commit();
		session.close();
		}

	@Override
	public void update(Flight flight) throws Exception {
		Session session = factory.openSession();
		session.beginTransaction();
		
		Flight f = session.get(Flight.class, flight.getId());
		f.setDestination(flight.getDestination());
		f.setDepartureTime(flight.getDepartureTime());
		f.setLandingTime(flight.getLandingTime());
		
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void delete(int flightId) throws Exception {
		Session session = factory.openSession();
		session.beginTransaction();
		
		Flight f = session.get(Flight.class, flightId);
		session.delete(f);
		
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Flight get(int flightId) throws Exception {
		Session session = factory.openSession();
		session.beginTransaction();
		
		Flight flight = session.get(Flight.class, flightId);
		System.out.println(flight.getTravelers()); // lazy - load data
		
		session.getTransaction().commit();
		session.close();
		return flight;
	}
	@Override
	public Traveler getTraveler(String travelerName) throws Exception {
		Session session = factory.openSession();
		session.beginTransaction();
		
		List<Traveler> traveler = session.createQuery("from Traveler t where t.fullName = " + "'" + travelerName + "'").getResultList();
		
		session.getTransaction().commit();
		session.close();
		return traveler.get(0);
	}
	@Override
	public void saveTraveler(Traveler traveler) throws Exception {
		Session session = factory.openSession();
		session.beginTransaction();
		
		session.save(traveler);
		
		session.getTransaction().commit();
		session.close();
	}
	
	public void addTravelerToFlight(int flightId, Traveler traveler) throws Exception
	{
		Session session = factory.openSession();
		session.beginTransaction();
		
		Flight flight = session.get(Flight.class, flightId);
		System.out.println(flight.getTravelers()); // load travelers
		Traveler t = session.get(Traveler.class, traveler.getPassportId());
		flight.addTraveler(t);
		
		session.getTransaction().commit();
		session.close();
	}
	public void removeTravelerFromFlight(int flightId, Traveler traveler) throws Exception
	{
		Session session = factory.openSession();
		session.beginTransaction();
		
		Flight flight = session.get(Flight.class, flightId);
		System.out.println(flight.getTravelers()); // load travelers
		Traveler t = session.get(Traveler.class, traveler.getPassportId());
		flight.removeTraveler(t);
		
		session.getTransaction().commit();
		session.close();
	}

	@PreDestroy
	public void containerCloseUp() throws Exception {
		factory.close();
	}

	@PostConstruct
	public void containerStartUp() throws Exception {
		factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Flight.class)
				.addAnnotatedClass(Traveler.class)
				.buildSessionFactory();
		System.out.println(getAll());
	}

}
