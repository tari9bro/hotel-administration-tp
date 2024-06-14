package model.entities;

import java.io.Serializable;



 

public class Client implements Serializable {
	public Client(Integer id, String name, String pass) {
		super();
		this.id = id;
		this.name = name;
		this.pass = pass;
	}

	public Client(String name, String pass) {
		super();
		this.name = name;
		this.pass = pass;
	}

	private static final long serialVersionUID = 1L;

	


	

	private Integer id;

	private String name;

	
	private String pass;

	public Client() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}