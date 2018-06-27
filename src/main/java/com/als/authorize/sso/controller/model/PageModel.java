package com.als.authorize.sso.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

@ApiModel("分页")
public class PageModel<T> extends BasicModel {
    @ApiModelProperty("当前页码")
    private int number;
    @ApiModelProperty("分页大小")
    private int size;
    @ApiModelProperty("总页数")
    private long totalPage;
    @ApiModelProperty("总记录数")
    private long totalElements;
    @ApiModelProperty("数据内容")
    private List<T> content = new ArrayList<>();

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
