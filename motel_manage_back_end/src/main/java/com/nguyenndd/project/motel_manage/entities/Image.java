package com.nguyenndd.project.motel_manage.entities;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Image extends BaseEntity {

	private String url;
	
	private String decription;
}
