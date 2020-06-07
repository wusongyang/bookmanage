package com.songyang.api.controller.employee;

import com.songyang.common.StandardResponse;
import com.songyang.pojo.BorrowInfo;
import com.songyang.service.BorrowInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee/borrowInfo")
public class ABorrowInfoController {
    @Autowired
    private BorrowInfoService borrowInfoService;
    @PutMapping("/{infoId}")
    public StandardResponse returnBook(@PathVariable(value = "infoId") int infoId, BorrowInfo borrowInfo){
        return borrowInfoService.returnBook(infoId,borrowInfo);
    }

}
