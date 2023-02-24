package airport.entities;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "travelers")
public class Traveler implements Comparable<Traveler>,Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "traveler_id")
	private int passportId;
	
	@Column(name = "fullName")
	private String fullName;
	
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "flight_id")
	private Flight flight;
	

	public Traveler() {}
	
	public Traveler(String fullName) {
		this.fullName = fullName;
	}
	@JsonIgnore
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	public int getPassportId() {
		return passportId;
	}

	public void setPassportId(int passportId) {
		this.passportId = passportId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public String toString() {
		return "Traveller: passportId=" + passportId + ", fullName=" + fullName;
	}
	//compare by passport id
	@Override
	public int compareTo(Traveler t) {
		return this.passportId-t.getPassportId();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj == this)
			return true;
		if(obj == null)
			return false;
		if(obj.getClass() != this.getClass())
			return false;
		Traveler t = (Traveler)obj;
		return t.passportId == this.passportId;
	}
}