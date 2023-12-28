package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainExceptions;
	
public class Reservation {
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Reservation() {

	}

	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainExceptions {
		if (checkOut.before(checkIn)) {
			throw new DomainExceptions("Error in reservation -  Check-out date must be after Check-in");
		}

		Date now = new Date();
		if (checkOut.before(now) || checkIn.before(now)) {
			throw new DomainExceptions("Error in reservation -  only future dates accepted");
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

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public long duration() {
		long diff;
		diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

	}

	public void updateDates(Date checkIn, Date checkOut) throws DomainExceptions {

		Date now = new Date();
		if (checkOut.before(now) || checkIn.before(now)) {
			throw new DomainExceptions("Error in reservation -  only future dates accepted");
		}

		if (checkOut.before(checkIn)) {
			throw new DomainExceptions("Error in reservation -  Check-out date must be after Check-in");
		}

		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	@Override
	public String toString() {
		return 	"Room " 
				+ roomNumber  
				+ ", check-in: " 
				+ sdf.format(checkIn)
				+ ", check-out: "
				+ sdf.format(checkOut)
				+", "
				+ duration()
				+" nights";
	}

}