package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.examly.springapp.model.Guest;

import java.util.List;

@Repository
public interface GuestRepo extends JpaRepository<Guest, Integer> {

    List<Guest> findByPhone(String phone);

    List<Guest> findByEmail(String email);
}
