package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Assets;
import com.example.repository.AssetRepository;

@Service
public class AssetService {

	@Autowired
	private AssetRepository repo;
	
	public List<Assets> listAll() {		
		return repo.findAll();
	}
	
	public Assets findAssetById(Long id) {
		Assets asset = repo.findById(id).orElseThrow(()-> new RuntimeException("Asset not found"));
		return asset;
	}
	
	public void save(Assets asset) {
		repo.save(asset);
	}
	
	public Assets get(Long id) {
		return repo.findById(id).get();
	}
	
	
	public void updateAsset(Assets asset) {
		repo.save(asset);
	}
	
	public void createAsset(Assets asset) {
		repo.save(asset);
	}
	
	public void removeAsset(Long id) {
		Assets asset = repo.findById(id).orElseThrow(()->new RuntimeException("Asset not found"));
		repo.deleteById(asset.getId());
	}
	
}
