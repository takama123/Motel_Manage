package com.nguyenndd.project.motel_manage.entities;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;

@Data
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity{
	@Id
    @Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@Column(name = "create_date")
	private ZonedDateTime createDate;
	
	@Column(name = "create_by")
	private String name;
	
	@Column(name = "modify_date")
	private ZonedDateTime modifyDate;
	
	@Column(name = "modify_by")
	private String modifyBy;
	
	@PrePersist
	protected void prePersist() {
		ZonedDateTime current = ZonedDateTime.now();
		if (this.createDate == null) {
			this.createDate = current;
		}
		
		if (this.modifyDate == null) {
			this.modifyDate = current;
		}
	}
	
	@PreUpdate
	protected void preUpdate() {
		this.modifyDate = ZonedDateTime.now();
	}
	
}
