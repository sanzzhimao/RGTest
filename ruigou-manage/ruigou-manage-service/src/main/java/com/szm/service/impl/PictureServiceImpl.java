package com.szm.service.impl;

import java.io.IOException;
import java.util.*;

import com.szm.util.FtpUtil;
import com.szm.util.IDUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.szm.service.IPictureService;
@Service
public class PictureServiceImpl implements IPictureService {
	//加载resource.properties里面的Ftp上传文件的参数
	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;
	@Value("${FTP_PORT}")
	private Integer FTP_PORT;
	@Value("${FTP_USERNAME}")
	private String FTP_USERNAME;
	@Value("${FTP_PASSWORD}")
	private String FTP_PASSWORD;
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	@Value("${IMAGE_BASE_PATH}")
	private String IMAGE_BASE_PATH;
	

	@Override
	public Map uploadFile(MultipartFile uploadFile) {
		Map<String,Object> resultMap=new HashMap();
		try {
			//获取上传的文件名
			String oldName=uploadFile.getOriginalFilename();
			//截取出文件名的扩展名
			String extendName=oldName.substring(oldName.indexOf("."));
			//生成一个不重复文件名
			String fileName= IDUtils.genImageName();
			//加上扩展名
			String newFileName=fileName+extendName;
			//生成当前年月日路径 
			String imgPath=new DateTime().toString("/yyyy/MM/dd");
			//上传图片
			boolean result= FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USERNAME,
					FTP_PASSWORD, FTP_BASE_PATH, imgPath, newFileName, 
					uploadFile.getInputStream());
			System.out.println(result);
			//上传失败
			if(!result){
				resultMap.put("error", 1);
				resultMap.put("message","上传失败");
			}else{
				resultMap.put("error", 0);
				String url=IMAGE_BASE_PATH+imgPath+"/"+newFileName;
				resultMap.put("url", url);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			return resultMap;
	}
}
