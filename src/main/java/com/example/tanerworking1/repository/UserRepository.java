package com.example.tanerworking1.repository;

import com.example.tanerworking1.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    //Username sistem içinde varsa usr nesnesini çekecek
    //JWT de nparse ettiğimiz verileri aldıktan sonra username göre sorgulayacaz
    User findByUsername(String username);
}
