package com.songyang.api.controller.user;


import com.songyang.common.StandardResponse;
import com.songyang.service.BorrowInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/getInfo")
public class BorrowInfoController {
    @Autowired
    private BorrowInfoService borrowInfoService;

    @PostMapping("/{bookid}")
    public StandardResponse createBorrowInfo(@PathVariable(value = "bookid") int bookId){

        return  null;


    }
}
