package com.fbt.dao.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "unsubscribe_details", catalog = "corp_contact")
public class Unsubscribe_Details {
	private Integer id;
	private String email;
	private String comments;
	private Date commented_time;

	private Unsubscribe_Type unsubscribe_Type;

	public Unsubscribe_Details() {
		super();
	}

	public Unsubscribe_Details(Integer id, String email, String comments, Date commented_time,
			Unsubscribe_Type unsubscribe_Type) {
		super();
		this.id = id;
		this.email = email;
		this.comments = comments;
		this.commented_time = commented_time;
		this.unsubscribe_Type = unsubscribe_Type;
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

	@Column(name = "comments")
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "commented_time")
	public Date getCommented_time() {
		return commented_time;
	}

	public void setCommented_time(Date commented_time) {
		this.commented_time = commented_time;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unsubscribe_type_Id", nullable = false)
	public Unsubscribe_Type getUnsubscribe_Type() {
		return unsubscribe_Type;
	}

	public void setUnsubscribe_Type(Unsubscribe_Type unsubscribe_Type) {
		this.unsubscribe_Type = unsubscribe_Type;
	}

}
