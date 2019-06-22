package com.qf.v13searchservice;

import com.qf.api.SearchService;
import com.qf.common.pojo.ResponseBean;
import lombok.Data;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V13SearchServiceApplicationTests {

    @Autowired
    private SolrClient solrClient;

    @Autowired
    private SearchService searchService;
    @Test
    public void contextLoads() {
    }

    @Test
    public void add() throws IOException, SolrServerException {

        SolrInputDocument document=new SolrInputDocument();
        document.setField("id",3);
        document.setField("product_name","华为手机棒");
        document.setField("product_sale_point","和火箭一样超级快");
        document.setField("product_price",500);
        document.setField("product_images","ssss");
        solrClient.add(document);
        solrClient.commit();
        System.out.println("添加索引库成功");
    }

    @Test
    public void query() throws IOException, SolrServerException {
        SolrQuery query=new SolrQuery();
        query.setQuery("product_name:华为厉害了");

        QueryResponse query1 = solrClient.query(query);

        SolrDocumentList results = query1.getResults();
        for (SolrDocument result : results) {
            System.out.println(result.get("product_name"));

        }


    }

    @Test
    public void testAsycAllData(){
        ResponseBean responseBean = searchService.syncAllData();
        System.out.println(responseBean.getStatusCode());

    }

    //删除所有
    @Test
    public void deeteAll() throws IOException, SolrServerException {
        solrClient.deleteByQuery("product_name:*");
        solrClient.commit();
    }

    @Test
    public void searchByKeywords(){
        ResponseBean responseBean = searchService.searchByKeywords("钊哥");
        System.out.println(responseBean.getData());
    }
}
