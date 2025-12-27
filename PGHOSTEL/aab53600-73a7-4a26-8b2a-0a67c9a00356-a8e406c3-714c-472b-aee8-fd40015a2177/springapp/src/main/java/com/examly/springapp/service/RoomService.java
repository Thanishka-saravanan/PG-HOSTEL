package com.examly.springapp.service;

import com.examly.springapp.model.Room;
import java.util.List;

public interface RoomService {

    Room addRoom(Room room);

    List<Room> getAllRooms();

    Room getRoomById(Integer id);

    Room updateRoom(Integer id, Room room);

    List<Room> getRoomsByNumber(String roomNumber);

    List<Room> getRoomsByCategoryName(String categoryName);
}
