package com.songyang.pojo;

import java.io.Serializable;

public class BookPic implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book_pic.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book_pic.book_id
     *
     * @mbggenerated
     */
    private Integer bookId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book_pic.pic_name
     *
     * @mbggenerated
     */
    private String picName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column book_pic.pic_host
     *
     * @mbggenerated
     */
    private String picHost;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table book_pic
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book_pic.id
     *
     * @return the value of book_pic.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column book_pic.id
     *
     * @param id the value for book_pic.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book_pic.book_id
     *
     * @return the value of book_pic.book_id
     *
     * @mbggenerated
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column book_pic.book_id
     *
     * @param bookId the value for book_pic.book_id
     *
     * @mbggenerated
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book_pic.pic_name
     *
     * @return the value of book_pic.pic_name
     *
     * @mbggenerated
     */
    public String getPicName() {
        return picName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column book_pic.pic_name
     *
     * @param picName the value for book_pic.pic_name
     *
     * @mbggenerated
     */
    public void setPicName(String picName) {
        this.picName = picName == null ? null : picName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column book_pic.pic_host
     *
     * @return the value of book_pic.pic_host
     *
     * @mbggenerated
     */
    public String getPicHost() {
        return picHost;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column book_pic.pic_host
     *
     * @param picHost the value for book_pic.pic_host
     *
     * @mbggenerated
     */
    public void setPicHost(String picHost) {
        this.picHost = picHost == null ? null : picHost.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table book_pic
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", bookId=").append(bookId);
        sb.append(", picName=").append(picName);
        sb.append(", picHost=").append(picHost);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}