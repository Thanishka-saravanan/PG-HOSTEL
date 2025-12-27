package com.examly.springapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.RoomCategory;
import com.examly.springapp.service.RoomCategoryService;

@RestController
@RequestMapping("/api/room-categories")
public class RoomCategoryController {

    private final RoomCategoryService rService;

    public RoomCategoryController(RoomCategoryService rService) {
        this.rService = rService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<RoomCategory> createRoomCategory(@RequestBody(required = false) RoomCategory roomcategory){
        if(roomcategory==null){
            return ResponseEntity.badRequest().build();
        }
        RoomCategory savedRoomCategory=rService.createRoomCategory(roomcategory);
        return new ResponseEntity<>(savedRoomCategory,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoomCategory>> getAllRoomCategory(){
        List<RoomCategory> roomCategories=rService.getAllRoomCategory();
        if(roomCategories==null || roomCategories.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roomCategories,HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getRoomCategoryById(@PathVariable int id){
        Optional<RoomCategory> category=rService.getRoomCategoryById(id);
        if(category.isEmpty()){
            return new ResponseEntity<>("Room category not found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category.get(),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomCategory> updateRoomCategory(@PathVariable int id,@RequestBody RoomCategory roomCategory){
        RoomCategory updatedRoomCategory=rService.updateRoomCategory(id,roomCategory);
        if(updatedRoomCategory==null){
            return new ResponseEntity<>(updatedRoomCategory,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedRoomCategory,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteRoomCategory(@PathVariable int id){
        boolean deletedRoomCategory=rService.deleteRoomCategory(id);
        if(!deletedRoomCategory){
            return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(true,HttpStatus.OK);
    }

    @GetMapping("/page/{pageNumber}/{pageSize}")
    public Page<RoomCategory> getPaginatedRoomCategory(@PathVariable int pageNumber,@PathVariable int pageSize){
        Pageable pageable=PageRequest.of(pageNumber, pageSize,Sort.by("categoryId").ascending());
        return rService.getAllRoomCategory(pageable);
    }
}
