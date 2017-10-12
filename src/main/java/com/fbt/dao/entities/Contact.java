package com.fbt.dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contact", catalog = "corp_contact")
public class Contact {
	private Integer id;
	private String email;
	private Integer subscribe_status;

	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(Integer id, String email, Integer subscribe_status) {
		super();
		this.id = id;
		this.email = email;
		this.subscribe_status = subscribe_status;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "subscribe_status")
	public Integer getSubscribe_status() {
		return subscribe_status;
	}

	public void setSubscribe_status(Integer subscribe_status) {
		this.subscribe_status = subscribe_status;
	}

}
