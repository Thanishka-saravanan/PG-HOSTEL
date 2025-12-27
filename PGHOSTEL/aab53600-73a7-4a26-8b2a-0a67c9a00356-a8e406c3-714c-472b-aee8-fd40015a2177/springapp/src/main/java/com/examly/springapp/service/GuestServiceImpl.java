package com.examly.springapp.service;

import com.examly.springapp.model.Guest;
import com.examly.springapp.repository.GuestRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepo guestRepo;

    public GuestServiceImpl(GuestRepo guestRepo) {
        this.guestRepo = guestRepo;
    }

    @Override
    public Guest addGuest(Guest guest) {
        return guestRepo.save(guest);
    }

    @Override
    public List<Guest> getAllGuests() {
        return guestRepo.findAll();
    }

    @Override
    public Guest getGuestById(Integer id) {
        return guestRepo.findById(id).orElse(null);
    }

    @Override
    public Guest updateGuest(Integer id, Guest updatedGuest) {
        Guest guest = guestRepo.findById(id).orElse(null);
        if (guest == null || updatedGuest == null) return null;

        guest.setName(updatedGuest.getName());
        guest.setPhone(updatedGuest.getPhone());
        guest.setEmail(updatedGuest.getEmail());

        return guestRepo.save(guest);
    }

    @Override
    public List<Guest> getGuestsByPhone(String phone) {
        return guestRepo.findByPhone(phone);
    }

    @Override
    public List<Guest> getGuestByEmail(String email) {
    return guestRepo.findByEmail(email);
    }

    @Override
    public void deleteGuestById(Integer id) {
        if (guestRepo.existsById(id)) {
            guestRepo.deleteById(id);
        }
    }


}
