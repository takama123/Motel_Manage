package com.nguyenndd.project.motel_manage.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Motel extends BaseEntity {

	private boolean status;

	@Column(length = 100)
	private String name;

	@Column(length = 100)
	private String address;

	private float rating;

	@Column(length = 12)
	private String phone;

	@Column(length = 500)
	private String decription;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User owner;
}
