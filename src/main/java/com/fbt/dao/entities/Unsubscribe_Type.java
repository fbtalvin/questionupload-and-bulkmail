package com.fbt.dao.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "unsubscribe_type", catalog = "corp_contact")
public class Unsubscribe_Type implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String unsubscribe_types;

	private Set<Unsubscribe_Details> unsubscribe_Details = new HashSet<Unsubscribe_Details>();

	public Unsubscribe_Type() {
		super();
	}

	public Unsubscribe_Type(Integer id, String unsubscribe_types) {
		super();
		this.id = id;
		this.unsubscribe_types = unsubscribe_types;
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

	@Column(name = "unsubscribe_types")
	public String getUnsubscribe_types() {
		return unsubscribe_types;
	}

	public void setUnsubscribe_types(String unsubscribe_types) {
		this.unsubscribe_types = unsubscribe_types;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unsubscribe_Type")
	public Set<Unsubscribe_Details> getUnsubscribe_Details() {
		return unsubscribe_Details;
	}

	public void setUnsubscribe_Details(Set<Unsubscribe_Details> unsubscribe_Details) {
		this.unsubscribe_Details = unsubscribe_Details;
	}

}
