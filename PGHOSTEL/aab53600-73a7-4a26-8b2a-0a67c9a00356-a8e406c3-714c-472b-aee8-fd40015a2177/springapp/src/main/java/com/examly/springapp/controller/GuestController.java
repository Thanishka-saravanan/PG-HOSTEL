package com.examly.springapp.controller;

import com.examly.springapp.model.Guest;
import com.examly.springapp.service.GuestService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping
    public ResponseEntity<Guest> addGuest(@RequestBody Guest guest) {
        return new ResponseEntity<>(guestService.addGuest(guest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Guest>> getAllGuests() {
        return ResponseEntity.ok(guestService.getAllGuests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guest> getGuestById(@PathVariable Integer id) {
        Guest guest = guestService.getGuestById(id);
        if (guest == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(guest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Guest> updateGuest(
            @PathVariable Integer id,
            @RequestBody Guest guest) {

        Guest updated = guestService.updateGuest(id, guest);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<?> getGuestsByPhone(@PathVariable String phone) {
    List<Guest> guests = guestService.getGuestsByPhone(phone);

    if (guests.isEmpty()) {
        // Return 204 No Content with custom message
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("No guest found with phone: " + phone);
    }

    // Return JSON array when guests exist
    return ResponseEntity.ok(guests);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<Guest>> getGuestByEmail(@PathVariable String email) {
    List<Guest> guests = guestService.getGuestByEmail(email); // return List<Guest>
    if (guests.isEmpty()) return ResponseEntity.notFound().build();
    return ResponseEntity.ok(guests);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable Integer id) {
    guestService.deleteGuestById(id);
    return ResponseEntity.noContent().build();
}


}
