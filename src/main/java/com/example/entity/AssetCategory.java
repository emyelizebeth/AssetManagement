package com.example.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.NoArgsConstructor; 
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "category")
public class AssetCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "category_name", length = 50, nullable = false, unique = true)
	private String name;
	
	@ManyToMany(mappedBy = "category_nonOwning", cascade = CascadeType.ALL)
	private Set<Assets> assets = new HashSet<Assets>();


	public AssetCategory(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public String toString() {
	    return name; 
	}
	
}
