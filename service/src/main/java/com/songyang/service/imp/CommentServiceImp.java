package com.songyang.service.imp;

import com.songyang.common.StandardResponse;
import com.songyang.dao.CommentMapper;
import com.songyang.pojo.Comment;
import com.songyang.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public StandardResponse addComment(int bookId, int userId, Comment comment) {

        comment.setBookId(bookId);
        comment.setUseId(userId);
        int count=commentMapper.insertSelective(comment);
        return count>0?StandardResponse.SuccessResponse("添加成功",comment):StandardResponse.ErrorResponseMessage("添加失败");
    }
}
