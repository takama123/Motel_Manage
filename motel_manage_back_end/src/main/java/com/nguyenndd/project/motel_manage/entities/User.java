package com.nguyenndd.project.motel_manage.entities;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "user_name", nullable = false, length = 20)
	private String userName;

	@Column(length = 20, nullable = false)
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "birthday")
	private ZonedDateTime birthday;

	@Column(length = 100)
	private String address;

	@Column(length = 100)
	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "identity_id")
	private String identityId;

}
