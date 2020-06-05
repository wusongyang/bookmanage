package com.songyang.dao;

import com.songyang.pojo.UserPic;

public interface UserPicMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_pic
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_pic
     *
     * @mbggenerated
     */
    int insert(UserPic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_pic
     *
     * @mbggenerated
     */
    int insertSelective(UserPic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_pic
     *
     * @mbggenerated
     */
    UserPic selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_pic
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(UserPic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_pic
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(UserPic record);


    int insertUserPicReturnId(UserPic userPic);

    UserPic selectImageByUserId(int userid);

}