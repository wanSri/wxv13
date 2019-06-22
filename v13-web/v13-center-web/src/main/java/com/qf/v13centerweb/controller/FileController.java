package com.qf.v13centerweb.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.qf.common.pojo.ResponseBean;
import com.qf.common.pojo.WangEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author: WangXi
 * @Date: 2019/6/13
 */

@RestController
@RequestMapping("file")
public class FileController {

    @Value("${images.server}")
    private String imagerServer;
    @Autowired
    private FastFileStorageClient client;

    @RequestMapping("upload")
    public ResponseBean uploadFile(MultipartFile file) {

        String s = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

        ResponseBean responseBean = new ResponseBean();
        try {
            StorePath storePath = client.uploadFile(file.getInputStream(), file.getSize(), s, null);
            System.out.println(storePath.getFullPath());
            StringBuffer stringBuffer = new StringBuffer(imagerServer);
            responseBean.setStatusCode("200");
            responseBean.setData(stringBuffer.append(storePath.getFullPath()));
            return responseBean;
        } catch (IOException e) {
            e.printStackTrace();
        }

        responseBean.setStatusCode("404");
        responseBean.setData("网络异常，请稍后再试");
        return responseBean;
    }

    @RequestMapping("mulUpload")
    public WangEditor mulUpload(MultipartFile[] files) {
        WangEditor wangEditor = new WangEditor();
        String[] paths = new String[files.length];
        try {
            for (int i = 0; i < files.length; i++) {
                String s =
                        files[i].getOriginalFilename().substring(files[i].getOriginalFilename().lastIndexOf(".") + 1);
                StorePath storePath = client.uploadFile(files[i].getInputStream(), files[i].getSize(), s, null);

                StringBuffer stringBuffer = new StringBuffer(imagerServer);
                paths[i] = stringBuffer.append(storePath.getFullPath()).toString();
            }
            wangEditor.setErrno("0");
            wangEditor.setData(paths);
            return wangEditor;
        } catch (IOException e) {
            e.printStackTrace();
        }
        wangEditor.setErrno("1");
        wangEditor.setData(null);
        return wangEditor;
    }
}
