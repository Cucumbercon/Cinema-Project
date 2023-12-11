package edu.uga.cinemabooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemaBookingApplication {

	private static CinemaBookingApplication variable;
	private CinemaBookingApplication() {}
	public static void main(String[] args) {
		SpringApplication.run(CinemaBookingApplication.class, args);
	}

	public static CinemaBookingApplication getVariable() {
		return variable;
	}

}
