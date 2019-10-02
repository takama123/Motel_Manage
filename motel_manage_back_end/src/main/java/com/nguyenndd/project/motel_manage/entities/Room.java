package com.nguyenndd.project.motel_manage.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.nguyenndd.project.motel_manage.enums.Status;

@Entity
public class Room extends BaseEntity {

	private String name;

	private long price;

	private String address;

	private Status status;

	private float acreage;

	private String decription;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "room_convenient",
			   joinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id"),
			   inverseJoinColumns = @JoinColumn(name = "convenient_id", referencedColumnName = "id"))
	private List<Convenient> convenients;

	@ManyToOne
	@JoinColumn(name = "motel_id")
	private Motel motel;
}
