package com.yyc.entity;

public class Book {
    private Integer id;
    private String bookIsbn;
    private String bookName;
    private Double bookPrice;
    private String bookAuthor;
    private Integer bookRepertorySize;
    private Integer bookPub;
    private Integer bookCategory;

    public Book() {
    }

    public Book(String bookIsbn, String bookName, Double bookPrice, String bookAuthor, Integer bookRepertorySize, Integer bookPub, Integer bookCategory) {
        this.bookIsbn = bookIsbn;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.bookAuthor = bookAuthor;
        this.bookRepertorySize = bookRepertorySize;
        this.bookPub = bookPub;
        this.bookCategory = bookCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookIsbn='" + bookIsbn + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookPrice=" + bookPrice +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookRepertorySize=" + bookRepertorySize +
                ", bookPub=" + bookPub +
                ", bookCategory=" + bookCategory +
                '}';
    }
}
