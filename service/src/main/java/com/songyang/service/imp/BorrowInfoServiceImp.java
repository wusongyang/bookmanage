package com.songyang.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.songyang.common.StandardResponse;
import com.songyang.dao.BookMapper;
import com.songyang.dao.BorrowInfoMapper;
import com.songyang.dao.LibraryCardMapper;
import com.songyang.pojo.Book;
import com.songyang.pojo.BorrowInfo;
import com.songyang.pojo.LibraryCard;
import com.songyang.service.BorrowInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BorrowInfoServiceImp implements BorrowInfoService {

    @Autowired
    private BorrowInfoMapper borrowInfoMapper;
    @Autowired
    private  BookMapper bookMapper;
    @Autowired
    private LibraryCardMapper libraryCardMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public StandardResponse createBorrowInfo(int bookId, int userId,int day) {
        Book book=bookMapper.selectBookByPrimarySynchronization(bookId);
        if (book.getCount()==0){
            return StandardResponse.ErrorResponseMessage("没有剩余图书");
        }else {
            LibraryCard libraryCard = libraryCardMapper.selectByUserId2(userId);
            if (libraryCard!=null){
                BorrowInfo borrowInfo =new BorrowInfo();
                int newConut=book.getCount()-1;
                book.setCount(newConut);
                book.setBorrowCount(book.getBorrowCount()+1);
                bookMapper.updateByPrimaryKeySelective(book);

                Long time =System.currentTimeMillis();
                borrowInfo.setBookId(bookId);
                borrowInfo.setBeginTime(new Date(time));
                borrowInfo.setReturnTime(new Date(time+day*24*60*60*1000));
                borrowInfo.setCardId(libraryCard.getId());
                borrowInfo.setIsReturn(false);
                int count=borrowInfoMapper.insertSelective(borrowInfo);
                return count>0?StandardResponse.SuccessResponse("借书成功",borrowInfo):StandardResponse.ErrorResponseMessage("借书失败");
            }else {
                return StandardResponse.ErrorResponseMessage("此用户没有借书卡");
            }


        }

    }

    @Override
    public StandardResponse getBorrowInfoList(int userId, int pageNum, int pageSize) {
        LibraryCard libraryCard  =libraryCardMapper.selectByUserId2(userId);
        PageHelper.startPage(pageNum,pageSize);
        List<BorrowInfo> list=borrowInfoMapper.selectListByCardId(libraryCard.getId());
        PageInfo pageInfo =new PageInfo();
        pageInfo.setList(list);
        return StandardResponse.SuccessResponse("success",pageInfo);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public StandardResponse returnBook(int infoId, BorrowInfo borrowInfo) {
        Book book=bookMapper.selectBookByPrimarySynchronization(borrowInfo.getBookId());
        book.setCount(book.getCount()+1);
        int count=bookMapper.updateByPrimaryKeySelective(book);
        if (count>0){
            borrowInfo.setIsReturn(true);
            Date date =new Date();
            if(borrowInfo.getReturnTime().before(date)){
                return StandardResponse.ErrorResponseMessage("需要付款");
            }
            borrowInfo.setReturnTime(null);
            borrowInfo.setId(infoId);
           int conut = borrowInfoMapper.updateByPrimaryKeySelective(borrowInfo);
           return conut>0?StandardResponse.SuccessResponseMessage("归还成功"):StandardResponse.ErrorResponseMessage("归还失败");
        }else return StandardResponse.ErrorResponseMessage("归还失败");
    }


}
