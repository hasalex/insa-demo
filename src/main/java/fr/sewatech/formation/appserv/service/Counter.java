package fr.sewatech.formation.appserv.service;

import java.io.Serializable;

public class Counter implements Serializable {

	private static final long serialVersionUID = -297393420610043154L;
	private Integer number = 1;
	
	public Integer getNumber() {
		return number++;
	}

    @Override
	public String toString() {
		return "Count : " + number;
	}
}
