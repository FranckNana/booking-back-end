package com.booking.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.booking.model.DAOUser;

@Repository
@Transactional
public interface UserDao extends CrudRepository<DAOUser, String>{
	DAOUser findByEmail(String email);

	void deleteByEmail(String email);

	List<DAOUser> findAll();
}
