package com.songyang.api.ImgPropoties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImgType {



    public static  String userImagesPath;

    public  static String bookImagesPath;

    public static String getUserImagesPath() {
        return userImagesPath;
    }
    @Value(value = "${images.type.userImage.path}")
    public void setUserImagesPath(String userimages) {
        ImgType.userImagesPath = userimages;
    }

    public static String getBookImagesPath() {
        return bookImagesPath;
    }
    @Value("${images.type.bookImage.path}")
    public void setBookImagesPath(String path) {
        ImgType.bookImagesPath = path;
    }

}
