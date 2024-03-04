package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.AssetCategory;

public interface CategoryRepository extends JpaRepository<AssetCategory, Long>{

}
