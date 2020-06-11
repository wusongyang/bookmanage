package com.songyang.service;

import com.songyang.common.StandardResponse;
import com.songyang.pojo.Comment;

public interface CommentService {

    StandardResponse addComment(int bookId, int userId, Comment comment);
}
