package com.songyang.service;

import com.songyang.common.StandardResponse;
import com.songyang.pojo.BorrowInfo;

public interface BorrowInfoService {
    StandardResponse createBorrowInfo(int bookId,int userId,int day);
    StandardResponse getBorrowInfoList(int userId,int pageNum,int pageSize);
    StandardResponse returnBook(int infoId, BorrowInfo borrowInfo);
}
