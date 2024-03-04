package com.example.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "assets")
public class Assets {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "serialno", length = 50, nullable = false, unique = true)
	private Long serialNo;
	
	@Column(name="asset_name", length = 50, nullable = false)
	private String name;
	
	@Column(name="asset_description", length = 250, nullable = false)
	private String description;
	
	@Column(name = "expiration_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate expirationDate;
	
	private String status;
	
	
	@Column(name = "created_user")
	private String createdUser;
	
	@Column(name = "updated_user")
	private String updatedUser;
	
	@Column(name = "created_date")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime createdDate;
	
	@Column(name = "updated_date")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime updatedDate;
	
	
	@PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = LocalDateTime.now();
    }
	
	
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "assets_categories",
	joinColumns = {@JoinColumn(name = "asset_id")},
	inverseJoinColumns = {@JoinColumn(name = "category_id")})
	private Set<AssetCategory> category_nonOwning = new HashSet<AssetCategory>();
	
	 public void removeCategory(AssetCategory category) {
		 this.category_nonOwning.remove(category);
		 category.getAssets().remove(this);
		 }
	 
	 public void addCategory(AssetCategory category) {
		 this.category_nonOwning.add(category);
		 category.getAssets().add(this);
	 }
	
	
}
