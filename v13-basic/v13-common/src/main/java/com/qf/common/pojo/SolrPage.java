package com.qf.common.pojo;

import java.util.List;

/**
 * @author: WangXi
 * @Date: 2019/6/17
 */
public class SolrPage<T> {

    private Integer currentPage;

    private Integer pageSize;

    private List<T> list;

    private Integer totalPage;


}
