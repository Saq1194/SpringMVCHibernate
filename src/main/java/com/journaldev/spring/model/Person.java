package com.journaldev.spring.model;

import javax.persistence.*;
import java.util.List;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 * @author pankaj
 *
 */
@Entity(name="Person")
@Table(name="Person")
public class Person {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="name" , nullable = false)
	private String name;

	@Column(name="country")
	private String country;

//	@OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "person_id", updatable = false, insertable = false)
	private List<Address> addresses;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public String toString(){
		return "id="+id+", name="+name+", country="+country;
	}

}
