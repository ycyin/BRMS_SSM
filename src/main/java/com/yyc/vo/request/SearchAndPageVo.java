package com.yyc.vo.request;


public class SearchAndPageVo{
    private String searchItem;
    private String searchValue;
    private Integer currentPage;
    private Integer pageSize;

    public SearchAndPageVo() {
    }

    public SearchAndPageVo(String searchItem, String searchValue, Integer currentPage, Integer pageSize) {
        this.searchItem = searchItem;
        this.searchValue = searchValue;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchItem() {
        return searchItem;
    }

    public void setSearchItem(String searchItem) {
        this.searchItem = searchItem;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    @Override
    public String toString() {
        return "SearchAndPageVo{" +
                "searchItem='" + searchItem + '\'' +
                ", searchValue='" + searchValue + '\'' +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }
}
