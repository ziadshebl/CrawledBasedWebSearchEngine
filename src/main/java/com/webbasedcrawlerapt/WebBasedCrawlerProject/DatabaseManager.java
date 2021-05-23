
// package com.telekord.boms.models;

// import java.util.Set;

// import javax.persistence.CascadeType;
// import javax.persistence.Entity;
// import javax.persistence.FetchType;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.OneToMany;
// import javax.persistence.OneToOne;
// import javax.persistence.Table;
// import javax.validation.constraints.NotNull;

// import com.fasterxml.jackson.annotation.JsonIgnore;

// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;


// @AllArgsConstructor
// @Getter
// @Setter
// @Entity
// @Table(name = "uncrawled")
// public class DatabaseManager {
	
// 	@Id
// 	@GeneratedValue(strategy = GenerationType.IDENTITY)
// 	private int id;
	
// 	@NotNull
// 	private String partyType;
	
// 	@OneToOne(fetch = FetchType.LAZY, mappedBy = "party")
// 	@JsonIgnore
// 	private Customer customer;
	
// 	@OneToOne(fetch = FetchType.LAZY, mappedBy = "party")
// 	@JsonIgnore
// 	private Supplier supplier;
	
// 	@OneToOne(fetch = FetchType.LAZY, mappedBy = "party")
// 	@JsonIgnore
// 	private Engineer engineer;
	
// 	@OneToMany(cascade = CascadeType.ALL,
//             fetch = FetchType.LAZY,
//             mappedBy = "party")
// 	private Set<Contacts> contacts;
	
// 	public Party(String partyType, Set<Contacts> contacts) {
		
// 		this.partyType = partyType;
// 		this.contacts = contacts;
// 	}

// 	public Party() {
// 		super();
// 		// TODO Auto-generated constructor stub
// 	}

// 	public int getId() {
// 		return id;
// 	}

// 	public void setId(int id) {
// 		this.id = id;
// 	}

// 	public String getPartyType() {
// 		return partyType;
// 	}

// 	public void setPartyType(String partyType) {
// 		this.partyType = partyType;
// 	}

// 	public Customer getCustomer() {
// 		return customer;
// 	}

// 	public void setCustomer(Customer customer) {
// 		this.customer = customer;
// 	}

// 	public Supplier getSupplier() {
// 		return supplier;
// 	}

// 	public void setSupplier(Supplier supplier) {
// 		this.supplier = supplier;
// 	}

// 	public Engineer getEngineer() {
// 		return engineer;
// 	}

// 	public void setEngineer(Engineer engineer) {
// 		this.engineer = engineer;
// 	}

// 	public Set<Contacts> getContacts() {
// 		return contacts;
// 	}

// 	public void setContacts(Set<Contacts> contacts) {
// 		this.contacts = contacts;
// 	}
	
// }