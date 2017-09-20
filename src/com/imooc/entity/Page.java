package com.imooc.entity;

/**
 * 分页对象
 */
public class Page {
    //总条数
    private int totalNumber;
    //当前是第几页
    private int currentPage;
    //总页数
    private int totalPage;
    //每页显示的条数
    private int pageNumber = 5;
    //数据库中开始index
    private int dbIndex;
    //数据库中一共取多少条数据
    private int dbNumber;

    /**
     * 计算page对象中所有对应的属性值
     */
    public void count() {
        //计算总页数
        int totalPageTemp = totalNumber / pageNumber;
        int plus = (totalNumber / pageNumber) == 0 ? 0 : 1;
        totalPageTemp =  totalPageTemp + plus;
        if(totalPageTemp == 0) {
            totalPageTemp = 1;
        }
        totalPage = totalPageTemp;

        //设置当前页数
        //当前页数大于总页数时，应该显示总页数
        if(currentPage > totalPage) {
            currentPage = totalPage;
        }
        //当前页数小于1时，将当前页数设为1
        if(currentPage < 1) {
            currentPage = 1;
        }

        //设置sql参数
        dbIndex = (currentPage - 1)*pageNumber;
        dbNumber = pageNumber;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(int dbIndex) {
        this.dbIndex = dbIndex;
    }

    public int getDbNumber() {
        return dbNumber;
    }

    public void setDbNumber(int dbNumber) {
        this.dbNumber = dbNumber;
    }
}
