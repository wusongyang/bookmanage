package com.songyang.api.controller.user;


import com.songyang.common.StandardResponse;
import com.songyang.pojo.BorrowInfo;
import com.songyang.pojo.UserDetils;
import com.songyang.service.BorrowInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/borrowinfo")
public class BorrowInfoController {
    @Autowired
    private BorrowInfoService borrowInfoService;

    @PostMapping("/{bookid}")
    public StandardResponse createBorrowInfo(@PathVariable(value = "bookid") int bookId,int day){
       UserDetils userDetils = (UserDetils) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       return borrowInfoService.createBorrowInfo(bookId,userDetils.getId(),day);
    }
    @GetMapping("")
    public StandardResponse getBorrowInfoList(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "10") int pageSize){
        UserDetils userDetils = (UserDetils) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       return borrowInfoService.getBorrowInfoList(userDetils.getId(),pageNum,pageSize);
    }
    @GetMapping("/{infoId}")
    public  StandardResponse returnBook(@PathVariable(value = "infoId") int infoId, BorrowInfo borrowInfo){
        return  borrowInfoService.returnBook(infoId,borrowInfo);
    }
}
