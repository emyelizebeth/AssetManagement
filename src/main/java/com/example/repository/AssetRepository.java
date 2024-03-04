package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Assets;


public interface AssetRepository extends JpaRepository<Assets, Long>{

}
