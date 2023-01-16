package model.entities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.exception.DomainException;

public class Reservation {

	private Integer roomNumber;
	private LocalDate checkIn;
	private LocalDate checkOut;
	
	private static DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public Reservation() {
		
	}

	public Reservation(Integer roomNumber, LocalDate checkIn, LocalDate checkOut) {
		if (!checkOut.isAfter(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date.") ;
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public LocalDate getCheckin() {
		return checkIn;
	}


	public LocalDate getCheckout() {
		return checkOut;
	}
	
	public long duration() {
		Duration t1 = Duration.between(checkIn.atStartOfDay(), checkOut.atStartOfDay());
		return t1.toDays();
	
	}
	
	public void updateDates(LocalDate checkIn, LocalDate checkOut) {
		
		LocalDate now = LocalDate.now();
		if (checkIn.isBefore(now) || checkOut.isBefore(now)) {
			throw new DomainException("Reservation dates for update must be future dates") ;
		}
		
		if (!checkOut.isAfter(checkIn)) {
			throw new DomainException("Check-out date must be after check-in date.") ;
		}
		
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	
	@Override
	public String toString() {
		return "Room"
				+ roomNumber
				+ " , check-in: "
				+ checkIn.format(fmt1)
				+ " , check-out: "
				+ checkOut.format(fmt1)
				+ " , "
				+ duration()
				+ " nigths.";
				
	}

	
	
	
	
	
}
