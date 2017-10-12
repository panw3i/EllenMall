package com.ellenmall.service.impl;

import com.ellenmall.service.IFileService;
import com.ellenmall.util.FTPUtil;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by zhengying on 2017/10/10.
 */
@Service("iFileService")
public class IFileServiceImpl implements IFileService {

    private Logger logger = LoggerFactory.getLogger(IFileServiceImpl.class);

    /**
     * 上传图片到FTP服务器
     * @param file
     * @param path
     * @return
     */
    public String upload(MultipartFile file,String path){
        String fileName = file.getOriginalFilename();
        //获取扩展名
        String fileExtentsionName = fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtentsionName;
        logger.info("开始上传文件,上传文件的文件名:{},上传的路径{},新文件名:{}",fileName,path,uploadFileName);
        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);//可写权限
            fileDir.mkdirs();
        }
        File targetFile = new File(path,uploadFileName);
        try {
            file.transferTo(targetFile);
            //上传文件到FTP服务器
            boolean upload = FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //上传完成后 删除upload 下面的文件
            targetFile.delete();
            if(!upload){
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("上传文件异常",e);
            return null;
        }
        return targetFile.getName();
    }
}
