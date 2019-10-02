package com.nguyenndd.project.motel_manage.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Register extends BaseEntity {
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column
	private String email;
	
	@Column
	private String address;
	
	@Column
	private String decription;
	
	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;
}
