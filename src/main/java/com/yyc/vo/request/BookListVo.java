package com.yyc.vo.request;

import java.util.List;

public class BookListVo{
    private List<String>  bookList;

    public List<String> getBookList() {
        return bookList;
    }

    public void setBookList(List<String> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "BookListVo{" +
                "bookList=" + bookList +
                '}';
    }
}
