package airport.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "flights")
public class Flight implements Comparable<Flight>, Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "flight_id")
	private int id; // id
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "flight", // Refers to flight in the traveler class
	cascade = { CascadeType.ALL })// All types but remove
	private List<Traveler> travelers;
	
	@Column(name = "departureTime")
	private double departureTime;
	
	@Column(name = "landingTime")
	private double landingTime;
	
	@Column(name = "destination")
	private String destination;
	
	public Flight() {}
	public Flight(double departureTime, double landingTime, String destination) {
		this.departureTime = departureTime;
		this.landingTime = landingTime;
		this.travelers = new ArrayList<Traveler>();
		this.destination = destination;
	}
	
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public double getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(double departureTime) {
		this.departureTime = departureTime;
	}

	public double getLandingTime() {
		return landingTime;
	}
	public List<Traveler> getTravelers() {
		return travelers;
	}
	public void setLandingTime(double landingTime) {
		this.landingTime = landingTime;
	}

	public boolean addTraveler(Traveler traveler){
		boolean r = travelers.add(traveler);
		traveler.setFlight(this);
		return r;

	}

	public boolean removeTraveler(Traveler traveler) {
		boolean r = travelers.remove(traveler);
		traveler.setFlight(null);
		return r;
	}
	

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj == this)
			return true;
		if(obj == null || obj.getClass() != this.getClass())
			return false;
		Flight f = (Flight)obj;
		return f.id == this.id;
	}

	@Override
	public int compareTo(Flight flight) {
		return Double.compare(this.id, flight.id);
	}

	@Override
	public String toString() {
		return "Flight : id=" + id + ", destination = " + destination
				+", departureTime = "
				+ String.valueOf(departureTime).replace('.', ':') + ", landingTime = " + String.valueOf(landingTime).replace('.', ':')
				+ "\n" + "travelers:" + travelers;
	}

	

}