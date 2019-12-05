package com.yyc.dto;

/**
 * @program: SSM
 * @description: 图书信息对应的DTO
 * @author: yyc
 * @create: 2019-12-03 14:44
 **/
public class BookDTO {
    private Integer id;
    private String bookName;
    private Double bookPrice;
    private String bookAuthor;
    private Integer bookRepertorySize;
    private String pubName;
    private String categoryName;
    private Integer bookPub;
    private Integer bookCategory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Integer getBookRepertorySize() {
        return bookRepertorySize;
    }

    public void setBookRepertorySize(Integer bookRepertorySize) {
        this.bookRepertorySize = bookRepertorySize;
    }

    public String getPubName() {
        return pubName;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getBookPub() {
        return bookPub;
    }

    public void setBookPub(Integer bookPub) {
        this.bookPub = bookPub;
    }

    public Integer getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(Integer bookCategory) {
        this.bookCategory = bookCategory;
    }
}
