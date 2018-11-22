package com.magnaalianza.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tfarmland")
public class Farmland {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_producer")
	private Producer producer = new Producer();

	@Transient
	private int idProducer;

	// This insert a list of coffee types into the tfarmland_x_coffee table
	@ManyToMany
	@JoinTable(name="tfarmland_x_coffee", joinColumns = {@JoinColumn(name="id_farmland")}, inverseJoinColumns = {@JoinColumn(name = "id_coffee")})
	private List<Coffee> coffeTypes = new ArrayList<>();


	public Farmland(String name, Producer myProducer) {
		this.name = name;
		this.producer = myProducer;
	}

	public Farmland() {
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIdProducer() {
		return idProducer;
	}

	public void setIdProducer(int idProducer) {
		this.idProducer = idProducer;
	}

	public List<Coffee> getCoffeTypes() {
		return coffeTypes;
	}

	public void setCoffeTypes(List<Coffee> coffeTypes) {
		this.coffeTypes = coffeTypes;
	}

	//////////////////////////////////


	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer myProducer) {
		this.producer = myProducer;
	}
}
