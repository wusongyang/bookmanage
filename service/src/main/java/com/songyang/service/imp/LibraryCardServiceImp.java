package com.songyang.service.imp;

import com.songyang.common.StandardResponse;
import com.songyang.dao.LibraryCardMapper;
import com.songyang.pojo.LibraryCard;
import com.songyang.service.LibraryCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class LibraryCardServiceImp implements LibraryCardService {

    @Autowired
    private LibraryCardMapper libraryCardMapper;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public StandardResponse addLibraryCard(LibraryCard libraryCard) {

        String LCName= System.currentTimeMillis()+libraryCard.getUserId().toString();
        Boolean b =this.verifyUserid(libraryCard.getUserId());
        if (b!=true){
            return StandardResponse.ErrorResponseMessage("已经存在卡号");
        }
        libraryCard.setCardName(LCName);
        int conut=libraryCardMapper.insertSelective(libraryCard);
        if (conut>0){
            return StandardResponse.SuccessResponseMessage("插入成功");
        }else {
            return StandardResponse.ErrorResponseMessage("插入失败");
        }
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public StandardResponse deleteLibraryCard(int id) {
        LibraryCard libraryCard =new LibraryCard();
        libraryCard.setIsDelete(true);
        libraryCard.setId(id);
        int conut=libraryCardMapper.updateByPrimaryKeySelective(libraryCard);
        return conut>0?StandardResponse.SuccessResponseMessage("删除成功"):StandardResponse.ErrorResponseMessage("删除失败");
    }

    @Override
    public StandardResponse selectLibraryCardByUserId(int userId) {
      LibraryCard libraryCard=  libraryCardMapper.selectByUserId2(userId);
        return StandardResponse.SuccessResponse("success",libraryCard);
    }

    //校验
    private  Boolean verifyUserid(int userId){
       int conut= libraryCardMapper.selectByUserId(userId);
       return  conut>0?false:true;
    }
}
