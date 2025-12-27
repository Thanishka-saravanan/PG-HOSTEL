package com.examly.springapp.service;

import com.examly.springapp.model.Room;
import com.examly.springapp.repository.RoomRepo;
import com.examly.springapp.repository.RoomCategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepo roomRepo;
    private final RoomCategoryRepo categoryRepo;

    public RoomServiceImpl(RoomRepo roomRepo, RoomCategoryRepo categoryRepo) {
        this.roomRepo = roomRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Room addRoom(Room room) {
        if (room.getRoomCategory() != null) {
            room.setRoomCategory(
                categoryRepo.findById(
                    room.getRoomCategory().getCategoryId()
                ).orElse(null)
            );
        }
        return roomRepo.save(room);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }

    @Override
    public Room getRoomById(Integer id) {
        return roomRepo.findById(id).orElse(null);
    }

    @Override
    public Room updateRoom(Integer id, Room updatedRoom) {

        Room room = roomRepo.findById(id).orElse(null);
        if (room == null) return null;

        room.setRoomNumber(updatedRoom.getRoomNumber());
        room.setPricePerNight(updatedRoom.getPricePerNight());
        room.setAvailable(updatedRoom.isAvailable());

        if (updatedRoom.getRoomCategory() != null) {
            room.setRoomCategory(
                    categoryRepo.findById(
                        updatedRoom.getRoomCategory().getCategoryId()
                    ).orElse(null)
            );
        }

        return roomRepo.save(room);
    }

    public List<Room> getRoomsByNumber(String roomNumber){
        return roomRepo.findByRoomNumber(roomNumber);
    }

    @Override
    public List<Room> getRoomsByCategoryName(String categoryName) {
        return roomRepo.findByRoomCategory_CategoryName(categoryName);
    }

}
