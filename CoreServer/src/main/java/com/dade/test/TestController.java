package com.dade.test;

import com.dade.commons.utils.LogUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Dade on 2016/12/24.
 */
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    TestUserMongoDao dao;

    @RequestMapping("/spring_boot")
    String springBoot(){
        LogUtil.info("test for spring boot!");
        return "sping boot!";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    TestUser user(@RequestBody TestUser user){
        LogUtil.info("newUser: " + user.toString());
        TestUser dbUser = dao.insert(user);
        LogUtil.info("dbUser: " + dbUser);
        return dbUser;
    }

//    @RequestMapping(value = "/head", method = RequestMethod.POST, produces = {"text/html;charset=UTF-8"})
    @RequestMapping(value = "/head", method = RequestMethod.POST)
    ResponseEntity head(@RequestParam("file") MultipartFile file){

//        Map<String, Object> res = new HashMap<>();
//        res.put("state", 200);
//        res.put("message","message");
//        res.put("result", file);

        String name = file.getName();
        LogUtil.info(name);

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File("D://uploaded/" + name)));
                stream.write(bytes);
                stream.close();
                return new ResponseEntity(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity(HttpStatus.OK);
            }
        } else {
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @RequestMapping("/icon")
    Map<String, Object> icon(HttpServletRequest request,
                             HttpServletResponse response,
                             @RequestParam("myFile") MultipartFile myFile){
        Map<String, Object> json = new HashMap<String, Object>();
        try {
            //输出文件后缀名称
            LogUtil.info(myFile.getOriginalFilename());
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            //图片名称
            String name = df.format(new Date());

            Random r = new Random();
            for(int i = 0 ;i<3 ;i++){
                name += r.nextInt(10);
            }
            //
            String ext = FilenameUtils.getExtension(myFile.getOriginalFilename());
            //保存图片       File位置 （全路径）   /upload/fileName.jpg
            String url = request.getSession().getServletContext().getRealPath("/static/img/upload/phono/");
            //相对路径
            String path = "/"+name + "." + ext;
            File file = new File(url);
            if(!file.exists()){
                file.mkdirs();
            }
            myFile.transferTo(new File(url+path));
            json.put("success", "/static/img/upload/phono/"+path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json ;

    }


//    @RequestMapping(value = "/bootstrap", method = RequestMethod.POST, produces = {"text/html;charset=UTF-8"})
    @RequestMapping(value = "/bootstrap", method = RequestMethod.POST)
    Map bootstrap(@RequestParam("avatar_src") String src,
                             @RequestParam("avatar_data") String data,
                             @RequestParam("avatar_file") MultipartFile file){

        LogUtil.info("test");
        //LogUtil.info(src);
        LogUtil.info(data);

        Map<String, Object> res = new HashMap<>();
        res.put("state", 200);
        res.put("message","message");
        res.put("result", ".\\img\\featured-SpringIO.png");

        LogUtil.info("name: " + file.getOriginalFilename());
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        LogUtil.info("ext: " + ext);

        return res;
//        return new ResponseEntity(res, HttpStatus.OK);
//        return new ResponseEntity(HttpStatus.OK);

    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    Map upload(@RequestParam("avatar_src") String src,
                  @RequestParam("avatar_data") String data,
                  @RequestParam("avatar_file") MultipartFile file){

        LogUtil.info(data);

        LogUtil.info("name: " + file.getOriginalFilename());
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        LogUtil.info("ext: " + ext);

        String resurl = "";

        try {
            //输出文件后缀名称
//            LogUtil.info(file.getOriginalFilename());
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            //图片名称
            String name = df.format(new Date());

            Random r = new Random();
            for(int i = 0 ;i<3 ;i++){
                name += r.nextInt(10);
            }
            //保存图片       File位置 （全路径）   /upload/fileName.jpg
//            String url = "E:/software/Idea/HomePlus/CoreServer/src/main/resources/static/upload";
            String url = "E:/foreground/homeplus/angular-seed/app/upload/";
            //相对路径
            String path = name + "." + ext;
            File pic = new File(url);
            if(!pic.exists()){
                pic.mkdirs();
            }
//            resurl = url + path;
            resurl = ".\\upload\\" + path;
            LogUtil.info(url + path);
            file.transferTo(new File(url+path));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Object> res = new HashMap<>();
        res.put("state", 200);
        res.put("message","message");
        res.put("result", resurl);

        return res;

    }

    @RequestMapping(value = "/cut", method = RequestMethod.POST)
    Map cut(@RequestParam("avatar_src") String src,
               @RequestParam("avatar_data") String data,
               @RequestParam("avatar_file") MultipartFile file){

        //判断文件的MIMEtype
        String type = file.getContentType();
        if(type==null || !type.toLowerCase().startsWith("image/"))
            return  new HashMap<String, Object>(){
                {
                    put("state", 403);
                }
            };
        LogUtil.info("file type: "+type);


        JSONObject joData = new JSONObject(data);
        // 用户经过剪辑后的图片的大小
        double x = joData.getDouble("x");
        double y = joData.getDouble("y");
        double w =  joData.getDouble("width");
        double h =  joData.getDouble("height");

        LogUtil.info("x: " + x);
        LogUtil.info("y: " + y);
        LogUtil.info("w: " + w);
        LogUtil.info("h: " + h);

        LogUtil.info(data);

        LogUtil.info("name: " + file.getOriginalFilename());
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        LogUtil.info("ext: " + ext);

        String resurl = "";

        try {
            //输出文件后缀名称
//            LogUtil.info(file.getOriginalFilename());
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            //图片名称
            String name = df.format(new Date());

            Random r = new Random();
            for(int i = 0 ;i<3 ;i++){
                name += r.nextInt(10);
            }
            //保存图片       File位置 （全路径）   /upload/fileName.jpg
//            String url = "E:/software/Idea/HomePlus/CoreServer/src/main/resources/static/upload";
            String url = "E:/foreground/homeplus/angular-seed/app/upload/";
            //相对路径
            String path = name + "." + ext;
            File pic = new File(url, path);
            if(!pic.exists()){
                pic.mkdirs();

                InputStream is = file.getInputStream();
                ImageCut.cut(is, pic, (int)x,(int)y,(int)w,(int)h);

                //FileUtil.copyFile(url+path,url+path + "s");

                is.close();

            }
//            resurl = url + path;
            resurl = ".\\upload\\" + path;
            LogUtil.info(url + path);
//            file.transferTo(new File(url+path));
//            file.transferTo(new File(url+path));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Object> res = new HashMap<>();
        res.put("state", 200);
        res.put("message","message");
        res.put("result", resurl);

        return res;

    }


}
