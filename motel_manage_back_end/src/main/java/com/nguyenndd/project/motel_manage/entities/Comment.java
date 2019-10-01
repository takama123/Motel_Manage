package com.nguyenndd.project.motel_manage.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class Comment extends BaseEntity{
	
	@Column(length = 500)
	private String comment;
}
