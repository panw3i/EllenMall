package com.ellenmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by zhengying on 2017/10/10.
 */
public interface IFileService {

    String upload(MultipartFile file, String path);
}
