package com.songyang.api.controller.user;


import com.songyang.api.config.GetUserDetail;
import com.songyang.common.StandardResponse;
import com.songyang.pojo.Comment;
import com.songyang.pojo.UserDetils;
import com.songyang.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/book/{bookId}")
    public StandardResponse addComment(@PathVariable(value = "bookId") int bookId , Comment comment){
       UserDetils userDetils = GetUserDetail.getUserDetailsBySecurity();
       return commentService.addComment( bookId,userDetils.getId(),comment);
    }

}
