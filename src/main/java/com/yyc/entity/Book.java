package com.yyc.entity;

public class Book {
    private Integer id;
    private String bookName;
    private Double bookPrice;
    private String bookAuthor;
    private Integer bookRepertorySize;
    private String bookPub;
    private String bookCategory;

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

    public String getBookPub() {
        return bookPub;
    }

    public void setBookPub(String bookPub) {
        this.bookPub = bookPub;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", bookPrice=" + bookPrice +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookRepertorySize=" + bookRepertorySize +
                ", bookPub='" + bookPub + '\'' +
                ", bookCategory='" + bookCategory + '\'' +
                '}';
    }
}
