package com.dxj.controller;

import com.dxj.bo.ResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author dxj
 * @Description 文件上传(单文件 ）
 * @date 2023/2/26 20:30
 */
@RestController
@Slf4j
@RequestMapping("/file")
public class FileUploadController {
    @Value("${file.location}")
    private String uploadPath;

    @PostMapping("/upload")
    public ResponseInfo upload(@RequestParam("file") MultipartFile file) throws IOException {
        //1、判断文件是否存在
        if (file.isEmpty()) {
            return ResponseInfo.fail("文件不存在！上传失败...");
        }
        //2、设置文件上传路径
        String uploadPath = this.uploadPath;
        log.info("文件上传路径为${}", uploadPath);
        //3、获取文件原始名称
        String fileName = file.getOriginalFilename();
        //4、获取文件名后缀
        int idx = fileName.lastIndexOf(".");
        String extention = fileName.substring(idx);
        //5、文件重命名
        String uuidFileName = UUID.randomUUID().toString().replace("-", "") + extention;
        //6、创建一个文件对象，把文件存放路径和文件名放进去
        File filePath = new File(uploadPath, uuidFileName);
        if (!filePath.exists()) {
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }
        }
        //7、将上传文件保存到一个目标文件中
        file.transferTo(new File(uploadPath + File.separator + "" + uuidFileName));
        //8、返回文件名
        return ResponseInfo.success(uuidFileName);
    }
}
