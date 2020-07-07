package com.nguyenndd.project.motel_manage.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Comment extends BaseEntity{
	
	@Column(length = 500)
	private String comment;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;
}
