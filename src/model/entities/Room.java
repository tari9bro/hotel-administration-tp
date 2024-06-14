package model.entities;

import java.io.Serializable;





public class Room implements Serializable {
	private static final long serialVersionUID = 1L;


	private Integer id;


	public Room(Integer roomId) {
		super();
		this.id = roomId;
	}

	private Integer reservationDuration;


	
	

	
	public Room() {
		super();
	}

	public Room(Integer roomId,  Integer reservationDuration) {
		super();
		this.id = roomId;
		this.reservationDuration = reservationDuration;
		
	}

	

	

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer roomId) {
		this.id = roomId;
	}

	public Integer getReservationDuration() {
		return this.reservationDuration;
	}

	public void setReservationDuration(Integer reservationDuration) {
		this.reservationDuration = reservationDuration;
	}

	

	

}