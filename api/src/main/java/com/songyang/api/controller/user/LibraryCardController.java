package com.songyang.api.controller.user;

import com.songyang.common.StandardResponse;
import com.songyang.pojo.LibraryCard;
import com.songyang.pojo.UserDetils;
import com.songyang.service.LibraryCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user/librarycard")
public class LibraryCardController {

    @Autowired
    private LibraryCardService libraryCardService;

   @PostMapping("/{userid}")
    public StandardResponse addLibraryCard(LibraryCard libraryCard,@PathVariable(value = "userid" ) int userId){

        libraryCard.setUserId(userId);
        libraryCard.setIsDelete(false);
      return  libraryCardService.addLibraryCard(libraryCard);
    }
    @DeleteMapping("/{id}")
    public StandardResponse deleteLibraryCard(@PathVariable(value = "id")int id){
      return libraryCardService.deleteLibraryCard(id);
    }
    @GetMapping("/{userid}")
    public StandardResponse getLibraryCardId(@PathVariable(value = "userid")int userId){
      UserDetils userDetails= (UserDetils) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       if( userDetails.getId()!=userId){
           return StandardResponse.ErrorResponseMessage("不是当前用户");
       }
       return libraryCardService.selectLibraryCardByUserId(userId);
    }





}
