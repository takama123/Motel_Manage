package com.nguyenndd.project.motel_manage.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Service extends BaseEntity {

	@Column(length = 50)
	private String name;
	
	@Column
	private long price;
	
	@Column
	private String decription;
}
