package com.szm.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface IPictureService {


	public Map uploadFile(MultipartFile uploadFile);
}
