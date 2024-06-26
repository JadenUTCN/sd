package com.utcn.demo.service.impl.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.utcn.demo.model.Account;
import com.utcn.demo.model.Image;
import com.utcn.demo.model.Role;
import com.utcn.demo.repository.AccountRepository;
import com.utcn.demo.service.AccountService;
import com.utcn.demo.service.ImageService;

import jakarta.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;
    private final ImageService imageService;

    @Override
    @Transactional(readOnly = true)
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Account> findByEmail(String email) {
        return accountRepository.findOneByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> findByName(String name) {
        return accountRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    public Image getAvatar(int avatarSize, String userEmail) {
        Image avatar = new Image();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(userEmail.getBytes());
            byte[] digest = md.digest();
            String hash = DatatypeConverter.printHexBinary(digest).toLowerCase();
            String avatarSource = "https://www.gravatar.com/avatar/%s?d=identicon&s=%d";
            String avatarUrl = String.format(avatarSource, hash, avatarSize);

            try(InputStream is = new URL(avatarUrl).openStream()) {
                String avatarData = Base64.getEncoder().encodeToString(is.readAllBytes());
                avatar.setData(avatarData);
            } catch (MalformedURLException ex) {
                throw new RuntimeException("There is a problem while downloading image", ex);
            } catch (IOException ioEx) {
                throw new RuntimeException("Problem with saving image", ioEx);
            }
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
        return avatar;
    }

    @Override
    public Account save(Account account) {
        if (account.getId() != null) {
            Account accountToPut = accountRepository.findById(account.getId()).orElseThrow();
            accountToPut.setEmail(account.getEmail());
            accountToPut.setName(account.getName());
            accountToPut.setRoles(account.getRoles());
            return accountRepository.save(accountToPut);
        }
        int avatarSize = 164;
        Image avatar = getAvatar(avatarSize, account.getEmail());
        account.setAvatar(avatar);
        account.addRole(Role.USER);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    @Override
    public void deleteById(Long id) {
        try {
            accountRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            log.info("Delete non existing entity with id=" + id, ex);
        }
    }
}
