package com.magnaalianza.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tproducer")
public class Producer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="nominal_address")
	private String nominalAddress;
	
	@Column(name="province")
	private String province;
	
	@Column(name="canton")
	private String canton;
		
	@Column(name="district")
	private String district;
	
	@Column(name="identification")
	private String identification;
	
	@Column(name="company_name")
	private String companyName;

	public Producer(String firstname, String direccion_nominal, String province, String canton, String district,
					String cedula, String companyName) {
		this.firstname = firstname;
		this.nominalAddress = direccion_nominal;
		this.province = province;
		this.canton = canton;
		this.district = district;
		this.identification = cedula;
		this.companyName = companyName;
	}

	public Producer() {
		
	}

	public Producer(String firstname) {
		this.firstname = firstname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getNominalAddress() {
		return nominalAddress;
	}

	public void setNominalAddress(String nominal_address) {
		this.nominalAddress = nominal_address;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCanton() {
		return canton;
	}

	public void setCanton(String canton) {
		this.canton = canton;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
}
