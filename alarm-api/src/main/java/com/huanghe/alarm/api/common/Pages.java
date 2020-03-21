package com.huanghe.alarm.api.common;

public class Pages {

    private int pageSize;

    private int pageCount;

    private int pageNum;

    private int dataCount;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    public void countPageCount() {
        if(this.pageSize > 0 && this.dataCount >0) {
            int pageCounts = this.dataCount/this.pageSize;
            if(dataCount%this.pageSize == 0)
                this.pageCount = pageCounts;
            else
                this.pageCount = pageCounts + 1;
        }
    }
}
