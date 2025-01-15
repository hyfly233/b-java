package com.hyfly.dolphinscheduler.sdk.common;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class PageInfo<T> {

    /**
     * totalList
     */
    @JSONField(name = "totalList")
    private List<T> totalList;

    /**
     * total
     */
    @JSONField(name = "total")
    private Integer total = 0;

    /**
     * total Page
     */
    @JSONField(name = "totalPage")
    private Integer totalPage;

    /**
     * page size
     */
    @JSONField(name = "pageSize")
    private Integer pageSize = 20;

    /**
     * current page
     */
    @JSONField(name = "currentPage")
    private Integer currentPage = 0;

    /**
     * pageNo dolphin 返回值是从0开始的
     */
    @JSONField(name = "pageNo")
    private Integer pageNo;

    public PageInfo() {
    }

    public PageInfo(Integer currentPage, Integer pageSize) {
        if (currentPage == null) {
            currentPage = 1;
        }
        this.pageNo = (currentPage - 1) * pageSize;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public Integer getTotal() {
        if (total == null) {
            total = 0;
        }
        return total;
    }

    public Integer getTotalPage() {
        if (pageSize == null || pageSize == 0) {
            pageSize = 7;
        }
        this.totalPage =
                (this.total % this.pageSize) == 0
                        ? ((this.total / this.pageSize) == 0 ? 1 : (this.total / this.pageSize))
                        : (this.total / this.pageSize + 1);
        return this.totalPage;
    }

    public Integer getPageSize() {
        if (pageSize == null || pageSize == 0) {
            pageSize = 7;
        }
        return pageSize;
    }

    public Integer getCurrentPage() {
        if (currentPage == null || currentPage <= 0) {
            this.currentPage = 1;
        }
        return currentPage;
    }

    /**
     * dolphin 返回值是从0开始的
     *
     * @param pageNo dolphin 返回值 pageNo
     */
    public void setPageNo(Integer pageNo) {
        if (pageNo == null) {
            pageNo = 0;
        }
        this.pageNo = pageNo + 1;
    }
}
