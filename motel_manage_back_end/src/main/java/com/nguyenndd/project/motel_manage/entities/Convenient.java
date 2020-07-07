package com.nguyenndd.project.motel_manage.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class Convenient extends BaseEntity{
	private String name;
	
	private String decription;
	
    @ManyToMany(mappedBy = "convenients")
    private List<Room> rooms;
}
