package com.songyang.dao;

import com.songyang.pojo.LibraryCard;

public interface LibraryCardMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table library_card
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table library_card
     *
     * @mbggenerated
     */
    int insert(LibraryCard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table library_card
     *
     * @mbggenerated
     */
    int insertSelective(LibraryCard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table library_card
     *
     * @mbggenerated
     */
    LibraryCard selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table library_card
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(LibraryCard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table library_card
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(LibraryCard record);

    int selectByUserId(int userId);
    LibraryCard selectByUserId2(int userId);
}