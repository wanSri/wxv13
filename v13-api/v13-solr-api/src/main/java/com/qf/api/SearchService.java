package com.qf.api;

import com.qf.common.pojo.ResponseBean;

/**
 * @author: WangXi
 * @Date: 2019/6/17
 */
public interface SearchService {

    /**
      * @Description: 搜索
      * @param keyword  搜索的关键字
      * @return: com.qf.common.pojo.ResponseBean
      * @Author: Mr.Wang
      * @Date: 2019/6/17 11:09
     */
    ResponseBean searchByKeywords(String keyword);

    /**
      * @Description:  同步数据到solr数据库
      * @param
      * @return: com.qf.common.pojo.ResponseBean
      * @Author: Mr.Wang
      * @Date: 2019/6/17 11:37
     */
    ResponseBean syncAllData();

    public ResponseBean updateById(Long id);

}
