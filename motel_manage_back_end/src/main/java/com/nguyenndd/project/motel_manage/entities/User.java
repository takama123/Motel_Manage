package com.nguyenndd.project.motel_manage.entities;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.nguyenndd.project.motel_manage.enums.Role;

import lombok.Data;

@Entity
@Data
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = {"user_name"}))
public class User extends BaseEntity{

	@Column(name = "user_name", nullable = false, length = 20)
	private String userName;

	@Column(length = 20, nullable = false)
	private String password;

	@Column(name = "first_name", length = 100)
	private String firstName;

	@Column(name = "last_name", length = 100)
	private String lastName;

	@Column(name = "birthday")
	private ZonedDateTime birthday;

	@Column(length = 100)
	private String address;

	@Column(length = 100)
	private String email;

	@Column(name = "phone")
	private String phone;
	
	@Column
	private String mobile;

	@Column(name = "identity_id", length = 10)
	private String identityId;
	
	private Role role;
	
	private boolean status;

}
