package com.sterling.digicheck.state.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ESTADO")
public class StateEntity implements Serializable{
	private static final long serialVersionUID = -7327022415786804653L;
	@Id
	@Column(name = "EST_CODIGO")
	private String code;
	@Column(name = "EST_NOMBRE")
	private String name;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
