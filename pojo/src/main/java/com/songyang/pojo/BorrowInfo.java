package com.songyang.pojo;

import java.io.Serializable;
import java.util.Date;

public class BorrowInfo implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column borrow_info.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column borrow_info.book_id
     *
     * @mbggenerated
     */
    private Integer bookId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column borrow_info.card_id
     *
     * @mbggenerated
     */
    private Integer cardId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column borrow_info.begin_time
     *
     * @mbggenerated
     */
    private Date beginTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column borrow_info.return_time
     *
     * @mbggenerated
     */
    private Date returnTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column borrow_info.is_return
     *
     * @mbggenerated
     */
    private Boolean isReturn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table borrow_info
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column borrow_info.id
     *
     * @return the value of borrow_info.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column borrow_info.id
     *
     * @param id the value for borrow_info.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column borrow_info.book_id
     *
     * @return the value of borrow_info.book_id
     *
     * @mbggenerated
     */
    public Integer getBookId() {
        return bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column borrow_info.book_id
     *
     * @param bookId the value for borrow_info.book_id
     *
     * @mbggenerated
     */
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column borrow_info.card_id
     *
     * @return the value of borrow_info.card_id
     *
     * @mbggenerated
     */
    public Integer getCardId() {
        return cardId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column borrow_info.card_id
     *
     * @param cardId the value for borrow_info.card_id
     *
     * @mbggenerated
     */
    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column borrow_info.begin_time
     *
     * @return the value of borrow_info.begin_time
     *
     * @mbggenerated
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column borrow_info.begin_time
     *
     * @param beginTime the value for borrow_info.begin_time
     *
     * @mbggenerated
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column borrow_info.return_time
     *
     * @return the value of borrow_info.return_time
     *
     * @mbggenerated
     */
    public Date getReturnTime() {
        return returnTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column borrow_info.return_time
     *
     * @param returnTime the value for borrow_info.return_time
     *
     * @mbggenerated
     */
    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column borrow_info.is_return
     *
     * @return the value of borrow_info.is_return
     *
     * @mbggenerated
     */
    public Boolean getIsReturn() {
        return isReturn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column borrow_info.is_return
     *
     * @param isReturn the value for borrow_info.is_return
     *
     * @mbggenerated
     */
    public void setIsReturn(Boolean isReturn) {
        this.isReturn = isReturn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table borrow_info
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
        sb.append(", cardId=").append(cardId);
        sb.append(", beginTime=").append(beginTime);
        sb.append(", returnTime=").append(returnTime);
        sb.append(", isReturn=").append(isReturn);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}