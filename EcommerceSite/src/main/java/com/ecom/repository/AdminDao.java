package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.model.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer> {
	public Admin findByAdminUsername(String username);

}
