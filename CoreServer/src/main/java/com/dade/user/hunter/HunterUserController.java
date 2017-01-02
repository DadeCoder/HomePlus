package com.dade.user.hunter;

import com.dade.commons.utils.ImageUtil;
import com.dade.commons.utils.LogUtil;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Dade on 2017/1/2.
 */
@RestController
@RequestMapping("/api/user")
public class HunterUserController {

    @Autowired
    HunterUserService hunterUserService;

    /**
     * 用户更换头像
     * @param src
     * @param data
     * @param file
     * @return
     */
    @RequestMapping(value = "/image_head", method = RequestMethod.POST)
    Map imageHead(@RequestParam("avatar_src") String src,
                  @RequestParam("avatar_data") String data,
                  @RequestParam("avatar_file") MultipartFile file){

        //判断文件的MIMEtype
        String type = file.getContentType();

        String imageHeadUrl = hunterUserService.imageHead(src, data, file);
        if(type==null || !type.toLowerCase().startsWith("image/") || imageHeadUrl == null)
            return  new HashMap<String, Object>(){
                {
                    put("state", 403);
                }
            };
        else
            return  new HashMap<String, Object>(){
                {
                    put("state", 200);
                    put("message","message");
                    put("result", imageHeadUrl);
                }
        };

    }

}
