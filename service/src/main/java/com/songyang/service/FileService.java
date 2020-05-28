package com.songyang.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface FileService {
    String  upload(MultipartFile file , String path);
}
