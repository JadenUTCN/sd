package com.utcn.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.utcn.demo.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Optional<Account> findById(Long id);

    Optional<Account> findByEmail(String email);

    List<Account> findByName(String name);

    List<Account> findAll();

    Page<Account> findAll(Pageable pageable);

    Account save(Account account);

    void deleteById(Long id);

}
