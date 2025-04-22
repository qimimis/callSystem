package com.yuanhao.utils;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
    private int currentPage;//页码
    private int pageSize;//每页大小
    private int count;//查询总数
    private int totalPages;//总页数
    private List<T> list = new ArrayList<T>();

    public Page() {
    }

    public Page(int currentPage, int pageSize, int count, List<T> list) {
        super();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.count = count;
        this.list = list;
    }

    /**
     * 获取
     * @return currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * 设置
     * @param currentPage
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 获取
     * @return pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * 设置
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 获取
     * @return totalPages
     */
    public int getTotalPages() {
        totalPages = count/pageSize;
        if(count%pageSize!=0){
            totalPages  = totalPages +1;
        }
        return totalPages;
    }

    /**
     * 获取
     * @return list
     */
    public List<T> getList() {
        return list;
    }

    /**
     * 设置
     * @param list
     */
    public void setList(List<T> list) {
        this.list = list;
    }

    public String toString() {
        return "Page{currentPage = " + currentPage + ", pageSize = " + pageSize + ", count = " + count + ", totalPages = " + totalPages + ", list = " + list + "}";
    }
}
