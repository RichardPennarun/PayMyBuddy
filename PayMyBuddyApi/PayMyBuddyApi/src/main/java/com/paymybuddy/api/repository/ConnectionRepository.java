package com.paymybuddy.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.api.model.Connection;

@Repository
public interface ConnectionRepository extends CrudRepository<Connection, Integer> {

}
