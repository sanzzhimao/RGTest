package com.szm.controller;

import com.szm.service.IPictureService;
import com.szm.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
public class PictureController {
    @Autowired
    private IPictureService pictureService;

    /**
     * 图片上传
     * @param uploadFile
     * @return
     */
    @RequestMapping("/pic/upload")
    @ResponseBody
    public String uploadFile(MultipartFile uploadFile){
        Map resultMap=pictureService.uploadFile(uploadFile);
        String json= JsonUtils.objectToJson(resultMap);
        return json;
    }
}
