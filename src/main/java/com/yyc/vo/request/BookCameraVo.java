package com.yyc.vo.request;

/**
 * @program: SSM
 * @description: 扫码添加图书VO
 * @author: yyc
 * @create: 2020-05-02 09:59
 **/
public class BookCameraVo {
    private Integer id;
    private String isbn;
    private String bookName;
    private Double bookPrice;
    private String bookAuthor;
    private Integer bookRepertorySize;
    private String bookPub;
    private String bookCategory;


    /**
     * 获取
     *
     * @return id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     *
     * @return isbn
     */
    public String getIsbn() {
        return this.isbn;
    }

    /**
     * 设置
     *
     * @param isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * 获取
     *
     * @return bookName
     */
    public String getBookName() {
        return this.bookName;
    }

    /**
     * 设置
     *
     * @param bookName
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * 获取
     *
     * @return bookPrice
     */
    public Double getBookPrice() {
        return this.bookPrice;
    }

    /**
     * 设置
     *
     * @param bookPrice
     */
    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    /**
     * 获取
     *
     * @return bookAuthor
     */
    public String getBookAuthor() {
        return this.bookAuthor;
    }

    /**
     * 设置
     *
     * @param bookAuthor
     */
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    /**
     * 获取
     *
     * @return bookRepertorySize
     */
    public Integer getBookRepertorySize() {
        return this.bookRepertorySize;
    }

    /**
     * 设置
     *
     * @param bookRepertorySize
     */
    public void setBookRepertorySize(Integer bookRepertorySize) {
        this.bookRepertorySize = bookRepertorySize;
    }

    /**
     * 获取
     *
     * @return bookPub
     */
    public String getBookPub() {
        return this.bookPub;
    }

    /**
     * 设置
     *
     * @param bookPub
     */
    public void setBookPub(String bookPub) {
        this.bookPub = bookPub;
    }

    /**
     * 获取
     *
     * @return bookCategory
     */
    public String getBookCategory() {
        return this.bookCategory;
    }

    /**
     * 设置
     *
     * @param bookCategory
     */
    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    @Override
    public String toString() {
        return "BookCameraVo{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", bookName='" + bookName + '\'' +
                ", bookPrice=" + bookPrice +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookRepertorySize=" + bookRepertorySize +
                ", bookPub='" + bookPub + '\'' +
                ", bookCategory='" + bookCategory + '\'' +
                '}';
    }
}
