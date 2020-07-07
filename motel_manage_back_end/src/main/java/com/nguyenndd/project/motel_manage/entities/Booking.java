package com.nguyenndd.project.motel_manage.entities;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.nguyenndd.project.motel_manage.enums.BookingStatus;

import lombok.Data;

@Entity
@Data
public class Booking extends BaseEntity {

	@Column
	private BookingStatus status;
	
	@Column(name = "expected_check_in_date")
	private ZonedDateTime expectedCheckInDate;
	
	@Column(name = "check_in_date")
	private ZonedDateTime checkInDate;
	
	@Column(name = "check_out_date")
	private ZonedDateTime checkOutDate;
	
	private String decription;
	
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
	private User customer;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
	private Room room;
}
