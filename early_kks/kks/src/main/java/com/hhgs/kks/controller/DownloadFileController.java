package com.hhgs.kks.controller;

import com.hhgs.kks.pojo.Message;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DownloadFileController {

    @Value("${outputPath}")
    private String filePath;


    @ApiOperation(value = "下载nari新增加的数据",notes = "下载nari新增加的数据")
    @GetMapping(value = "/downloaddata")
    public String download(@ApiParam(name = "fileName",value = "文件名称",required = true)@RequestParam String fileName ,
                           @ApiParam(required = false) HttpServletRequest request,
                           @ApiParam(required = false) HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=utf-8");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        java.io.BufferedInputStream bis = null;
        java.io.BufferedOutputStream bos = null;

        System.out.println(fileName);

        //String YC_downLoadPath = filePath+"YC"+fileName+".xlsx";  //注意不同系统的分隔符
        //String YX_downLoadPath = filePath+"YX"+fileName+".xlsx";  //注意不同系统的分隔符

        //	String downLoadPath =filePath.replaceAll("/", "\\\\\\\\");   //replace replaceAll区别 *****
        //System.out.println(YC_downLoadPath);

        //List<String> downLoadPathList = new ArrayList<>();
        //downLoadPathList.add(YC_downLoadPath);
        //downLoadPathList.add(YX_downLoadPath);

        if(fileName==null || fileName== "") {
           // JSONObject responseJSONObject = JSONObject.fromObject(new Message("请将参数输入完整"));
           /* response.setContentType("application/json; charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = null;
            try {
                out = response.getWriter();
                out.append(responseJSONObject.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (out != null) {
                    out.close();
                }
            }*/
           /* String data = "请将参数输入完整";
            OutputStream out = response.getOutputStream();
            //html: <meta>标签也能模拟响应头
            out.write("<meta http-equiv='content-type' content='text/html;charset=utf-8'>".getBytes());
            out.write(data.getBytes("utf-8"));
            out.close();
            return  "download";*/


        }
        String downLoadPath = filePath+fileName+".zip";

        try {


            String newFileName = downLoadPath.split("\\\\")[5];
            long fileLength = new File(downLoadPath).length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + new String((newFileName).getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));
            OutputStream out = response.getOutputStream();
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null)
                try {
                    bis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();

                }
            if (bos != null)
                try {
                    bos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }

        return null;
    }

}
