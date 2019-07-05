package com.szm.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class FtpUtil {

    @Value("${ftp.host}")
    private String host;
    @Value("${ftp.port}")
    private int port;
    @Value("${ftp.username}")
    private String username;
    @Value("${ftp.password}")
    private String password;
    @Value("${ftp.basePath}")
    private String basePath;

    public boolean fileUpload(String path, String filename, InputStream inputStream){

        return false;

    }
}
