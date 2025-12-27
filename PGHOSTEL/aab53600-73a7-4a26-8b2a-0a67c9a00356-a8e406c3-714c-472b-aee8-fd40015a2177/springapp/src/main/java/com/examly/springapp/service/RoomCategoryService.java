package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.RoomCategory;
import com.examly.springapp.repository.RoomCategoryRepo;

@Service
public class RoomCategoryService {
    private final RoomCategoryRepo rRepo;
    public RoomCategoryService(RoomCategoryRepo rRepo){
        this.rRepo = rRepo;
    }

    public RoomCategory createRoomCategory(RoomCategory roomCategory){
        return rRepo.save(roomCategory);
    }

    public List<RoomCategory> getAllRoomCategory(){
        return rRepo.findAll();
    }

    public Optional<RoomCategory> getRoomCategoryById(int id){
        return rRepo.findById(id);
    }

    public RoomCategory updateRoomCategory(int id,RoomCategory roomcategory){
        Optional<RoomCategory> existing=rRepo.findById(id);
        if(existing.isEmpty()){
            return null;
        }
        roomcategory.setCategoryId(id);
        return rRepo.save(roomcategory);
    }

    public boolean deleteRoomCategory(int id){
        Optional<RoomCategory> deletedCategory=rRepo.findById(id);
        if(deletedCategory.isEmpty()){
            return false;
        }
        rRepo.deleteById(id);
        return true;
    }

    public Page<RoomCategory> getAllRoomCategory(Pageable pageable){
        return rRepo.findAll(pageable);
    }
}
