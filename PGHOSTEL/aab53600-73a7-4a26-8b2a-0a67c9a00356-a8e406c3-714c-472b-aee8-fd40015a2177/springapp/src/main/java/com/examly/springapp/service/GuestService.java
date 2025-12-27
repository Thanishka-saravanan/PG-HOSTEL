package com.examly.springapp.service;

import com.examly.springapp.model.Guest;

import java.util.List;

public interface GuestService {

    Guest addGuest(Guest guest);

    List<Guest> getAllGuests();

    Guest getGuestById(Integer id);

    Guest updateGuest(Integer id, Guest guest);

    List<Guest> getGuestsByPhone(String phone);

    List<Guest> getGuestByEmail(String email);

    void deleteGuestById(Integer id);

}
