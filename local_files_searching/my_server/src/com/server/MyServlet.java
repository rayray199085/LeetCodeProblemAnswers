package com.server;


import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


public class MyServlet extends HttpServlet {
    private ServletConfig config;
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("UTF-8");
        String keyword = req.getParameter("key");
        ServletContext servletContext = config.getServletContext();
        String fileType = (String) servletContext.getAttribute("type");
        SearchingLocalFiles searchingLocalFiles = new SearchingLocalFiles(keyword);
        File file = new File(searchingLocalFiles.PATH);
        List<MyPics> myPicsList = searchingLocalFiles.find(file.listFiles(),keyword,fileType);

        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream()));

        for(MyPics pic: myPicsList){
            bufferedWriter.write(pic.getPicPath());
            bufferedWriter.newLine();
            bufferedWriter.flush();

        }
        bufferedWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
