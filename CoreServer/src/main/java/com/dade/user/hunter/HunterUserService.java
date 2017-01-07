package com.dade.user.hunter;

import com.dade.commons.utils.ImageUtil;
import com.dade.commons.utils.LogUtil;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Dade on 2017/1/2.
 */
@Component
public class HunterUserService {

    @Autowired
    HunterUserDao dao;

    /**
     * user change their headimage
     * TODO how to bind user id
     * @param src
     * @param data
     * @param file
     * @return
     */
    public String imageHead(String src, String data, MultipartFile file){

        JSONObject joData = new JSONObject(data);
        // 用户经过剪辑后的图片的大小
        double x = joData.getDouble("x");
        double y = joData.getDouble("y");
        double w =  joData.getDouble("width");
        double h =  joData.getDouble("height");

        String imageHeadUrl = null;

        try {
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String name = df.format(new Date());

            Random random = new Random();
            for(int i = 0 ;i<3 ;i++){
                name += random.nextInt(10);
            }

            // 文件后缀名称
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());
            String url = "E:/foreground/homeplus/angular-seed/app/upload/";

            String path = name + "." + ext;
            File pic = new File(url, path);
            if(!pic.exists()){
                pic.mkdirs();
                InputStream is = file.getInputStream();
                ImageUtil.cut(is, pic, (int)x, (int)y, (int)w, (int)h);
                is.close();
            }
            imageHeadUrl = ".\\upload\\" + path;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imageHeadUrl;
    }

    public HunterUser findHunterUser(String phoneNumber){

        HunterUser hunterUser = dao.findByPhoneNumber(phoneNumber);
        LogUtil.info(hunterUser.toString());
        return hunterUser;
    }

}
