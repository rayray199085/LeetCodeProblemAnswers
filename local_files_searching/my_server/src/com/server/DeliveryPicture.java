package com.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class DeliveryPicture extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("UTF-8");
        String filePath = req.getParameter("path");
        File file = new File(filePath);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(resp.getOutputStream());
        int len = 0;
        byte[] buffer = new byte[1024];
        while((len=bufferedInputStream.read(buffer))!=-1){
            bufferedOutputStream.write(buffer,0,len);
        }
        bufferedInputStream.close();
        bufferedOutputStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
