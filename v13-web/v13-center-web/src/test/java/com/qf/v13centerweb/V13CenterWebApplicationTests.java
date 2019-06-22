package com.qf.v13centerweb;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class V13CenterWebApplicationTests {

    @Autowired
    private FastFileStorageClient client;
    @Test
    public void contextLoads() {
    }

    @Test
    public void uploadTest(){
        File file=new File("D:\\downloads\\wallhaven-j51ppw_1920x1080.png");
        try {
            StorePath storePath = client.uploadFile(new FileInputStream(file), file.length(), "png", null);
            System.out.println(storePath.getFullPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteFile(){
        client.deleteFile("group1/M00/00/00/wKjmgF0CjlSACxIIABG0jYnq8tY400.png");
        System.out.println("栓除成功");
    }

}
