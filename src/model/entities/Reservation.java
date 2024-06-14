package model.entities;



public class Reservation {
    private Integer requestID;
    private Integer clientID;
    private Integer roomID;
    private String status;

    public Reservation(Integer clientID, String status) {
		super();
		this.clientID = clientID;
		this.status = status;
	}

	public Reservation(Integer requestID, Integer clientID, Integer roomID, String status) {
        this.requestID = requestID;
        this.clientID = clientID;
        this.roomID = roomID;
        this.status = status;
    }

    public Reservation(Integer requestID, Integer clientID, Integer roomID) {
		super();
		this.requestID = requestID;
		this.clientID = clientID;
		this.roomID = roomID;
	}

	public Reservation(Integer clientID, Integer roomID, String status) {
		super();
		this.clientID = clientID;
		this.roomID = roomID;
		this.status = status;
	}
	public Reservation( Integer roomID, String status,Integer requestID) {
		super();
		this.requestID = requestID;
		this.roomID = roomID;
		this.status = status;
	}
	public Integer getRequestID() {
        return requestID;
    }

    public void setRequestID(Integer requestID) {
        this.requestID = requestID;
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
        this.roomID = roomID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "requestID=" + requestID +
                ", clientID=" + clientID +
                ", roomID=" + roomID +
                ", status='" + status + '\'' +
                '}';
    }
}

