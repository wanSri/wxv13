package com.qf.v13searchservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.api.SearchService;
import com.qf.common.pojo.ResponseBean;
import com.qf.entity.TProduct;
import com.qf.mapper.TProductMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: WangXi
 * @Date: 2019/6/17
 */

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SolrClient client;
    
    @Autowired
    private TProductMapper productMapper;




    @Override
    public ResponseBean searchByKeywords(String keyword) {

        //创建查询条件
        SolrQuery condition = new SolrQuery();
        if (StringUtils.isAnyEmpty(keyword)) {
            condition.setQuery("product_keywords:*");
        } else {

            condition.setQuery("product_keywords:" + keyword);
        }

        //设置高亮信息
        condition.setHighlight(true);
        condition.addHighlightField("product_name");
        condition.setHighlightSimplePre("<font color='red'>");
        condition.setHighlightSimplePost("</font>");

        ArrayList<TProduct> list=null;
        try {
            QueryResponse query = client.query(condition);
            SolrDocumentList results = query.getResults();
            Map<String, Map<String, List<String>>> highlighting = query.getHighlighting();
            list=new ArrayList<>(results.size());
            for (SolrDocument result : results) {
                TProduct product=new TProduct();
                product.setId(Long.parseLong(result.getFieldValue("id").toString()));
               // product.setName(result.getFieldValue("product_name").toString());
                product.setSalePoint(result.getFieldValue("product_sale_point").toString());
                product.setPrice(Long.parseLong(result.getFieldValue("product_price").toString()));
                product.setImages(result.getFieldValue("product_images").toString());
                Map<String, List<String>> map = highlighting.get(result.getFieldValue("id"));
                List<String> product_name = map.get("product_name");
                if(product_name!=null){
                    product.setName(product_name.get(0));
                }else {
                    product.setName(result.getFieldValue("product_name").toString());
                }

                list.add(product);
            }
            return new ResponseBean("200",list);
        } catch (SolrServerException | IOException e) {
            e.printStackTrace();
            return new ResponseBean("404","查询失败");
        }
    }

    @Override
    public ResponseBean syncAllData() {
        List<TProduct> products = productMapper.queryAll();
        for (TProduct product : products) {
            SolrInputDocument document = new SolrInputDocument();
            document.setField("id", product.getId());
            document.setField("product_name", product.getName());
            document.setField("product_sale_point", product.getSalePoint());
            document.setField("product_price", product.getPrice());
            document.setField("product_images", product.getImages());
            try {
                client.add(document);
            } catch (SolrServerException | IOException e) {
                e.printStackTrace();
                return new ResponseBean("500", "数据同步失败");
            }

            try {
                client.commit();
            } catch (SolrServerException | IOException ex) {
                ex.printStackTrace();
                return new ResponseBean("500", "数据同步失败");
            }
        }
        return new ResponseBean("200","数据同步成功");
    }

    @Override
    public ResponseBean updateById(Long id) {
        TProduct product = productMapper.selectByPrimaryKey(id);
        SolrInputDocument document=new SolrInputDocument();
        document.setField("id",product.getId());
        document.setField("product_name",product.getName());
        document.setField("product_sale_point",product.getSalePoint());
        document.setField("product_price",product.getPrice());
        document.setField("product_images",product.getImages());
        try {
            client.add(document);
        } catch (SolrServerException |IOException e) {
            e.printStackTrace();
            return new ResponseBean("500","数据库同步失败");
        }
        return new ResponseBean("200","数据库同步success");
    }
}
