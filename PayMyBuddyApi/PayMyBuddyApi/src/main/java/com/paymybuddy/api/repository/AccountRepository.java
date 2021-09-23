package com.paymybuddy.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.api.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

}
