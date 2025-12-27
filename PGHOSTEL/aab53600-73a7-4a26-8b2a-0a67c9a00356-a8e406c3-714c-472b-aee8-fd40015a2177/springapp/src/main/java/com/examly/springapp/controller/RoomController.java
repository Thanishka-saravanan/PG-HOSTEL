package com.examly.springapp.controller;

import com.examly.springapp.model.Room;
import com.examly.springapp.model.RoomCategory;
import com.examly.springapp.repository.RoomRepo;
import com.examly.springapp.repository.RoomCategoryRepo;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomRepo roomRepo;
    private final RoomCategoryRepo categoryRepo;

    public RoomController(RoomRepo roomRepo, RoomCategoryRepo categoryRepo) {
        this.roomRepo = roomRepo;
        this.categoryRepo = categoryRepo;
    }

    // DAY 10 - Add Room
    @PostMapping
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        room.setRoomCategory(
            categoryRepo.findById(
                room.getRoomCategory().getCategoryId()
                ).orElse(null)
        );
        return new ResponseEntity<>(roomRepo.save(room), HttpStatus.CREATED);
    }

    // DAY 10 - Get All Rooms
    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        return ResponseEntity.ok(roomRepo.findAll());
    }

    // DAY 10 - Get Room By ID
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Integer id) {
        return roomRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DAY 10 - Update Room
    
    // Update Room
    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(
            @PathVariable Integer id,
            @RequestBody Room updatedRoom) {

        return roomRepo.findById(id).map(room -> {
            room.setRoomNumber(updatedRoom.getRoomNumber());
            room.setPricePerNight(updatedRoom.getPricePerNight());
            room.setAvailable(updatedRoom.isAvailable());

            // Fetch RoomCategory from DB
            RoomCategory category = categoryRepo.findById(
                    updatedRoom.getRoomCategory().getCategoryId()
            ).orElseThrow(() -> new RuntimeException("Category not found"));
            room.setRoomCategory(category);

            Room savedRoom = roomRepo.save(room);
            return ResponseEntity.ok(savedRoom);
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/number/{roomNumber}")
    public ResponseEntity<?> getRoomByNumber(@PathVariable String roomNumber) {
    List<Room> rooms = roomRepo.findByRoomNumber(roomNumber);
    if (rooms.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No room found with number: " + roomNumber);
    }
    return ResponseEntity.ok(rooms);
    }

    @GetMapping("/category/{categoryName}")
public ResponseEntity<?> getRoomsByCategoryName(@PathVariable String categoryName) {

    List<Room> rooms =
            roomRepo.findByRoomCategory_CategoryName(categoryName);

    if (rooms.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No rooms found for category: " + categoryName);
    }

    return ResponseEntity.ok(rooms);
}




}
