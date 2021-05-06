package com.wzl.servlet;

import com.wzl.service.MovieService;
import com.wzl.service.MovieServiceImpl;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
        System.out.println("get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        System.out.println(method);
        if (method != null && method.equals("deleteMovie")){
            deleteMovie(req, resp);
        }else if (method != null && method.equals("addMovie")){
            try {
                System.out.println("add");
                addMovie(req, resp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if (method != null && method.equals("updateMovie")){
            try {
                updateMovie(req, resp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
    public void deleteMovie(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        MovieService movieService = new MovieServiceImpl();
        int updatedRow = movieService.deleteMovie(Integer.parseInt(id));
        System.out.println("删除了" + updatedRow + "行");
        resp.sendRedirect("/movies");
    }
    public void addMovie(HttpServletRequest req, HttpServletResponse resp) throws IOException, ParseException {

        if (ServletFileUpload.isMultipartContent(req)){
            return;
        }

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String director = req.getParameter("director");
        String location = req.getParameter("location");
        String actor = req.getParameter("actor");
        String filmedTime = req.getParameter("filmedTime");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        MovieService movieService = new MovieServiceImpl();
        int updatedRow = movieService.addMovie(Integer.parseInt(id), name, director, location, actor, formatter.parse(filmedTime));
        System.out.println("增加了" + updatedRow + "行");


        try {
            String uploadPath = this.getServletContext().getRealPath("/WEB-INF/upload");
            System.out.println(uploadPath);
            File uploadFile = new File(uploadPath);
            if (!uploadFile.exists()){
                uploadFile.mkdir();
            }

            String tmpPath = this.getServletContext().getRealPath("/WEB-INF/tmp");
            File file = new File(tmpPath);
            if (!file.exists()){
                file.mkdir();
            }

            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024*1024);
            factory.setRepository(file);

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            upload.setFileSizeMax(1024*1024*10);

            List<FileItem> fileItems = upload.parseRequest(req);



            for (FileItem fileItem : fileItems) {
                if (!fileItem.isFormField()){
                    String uploadFileName = fileItem.getName();
                    if (uploadFileName.trim().equals("") || uploadFileName==null){
                        continue;
                    }
                    String subName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
                    String realPath = uploadPath + "/" + id;
                    File realPathFile = new File(realPath);
                    if (!realPathFile.exists()){
                        realPathFile.mkdir();
                    }

                    InputStream inputStream = fileItem.getInputStream();
                    FileOutputStream fileOutputStream = new FileOutputStream(realPath + "/" + id + "." + subName);
                    byte[] buffer = new byte[1024*1024];
                    int len = 0;
                    while((len=inputStream.read(buffer)) > 0){
                        fileOutputStream.write(buffer, 0, len);
                    }
                    fileOutputStream.close();
                    inputStream.close();
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/movies");
    }
    public void updateMovie(HttpServletRequest req, HttpServletResponse resp) throws IOException, ParseException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String director = req.getParameter("director");
        String location = req.getParameter("location");
        String actor = req.getParameter("actor");
        String filmedTime = req.getParameter("filmedTime");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        MovieService movieService = new MovieServiceImpl();
        int updatedRow = movieService.updateMovie(Integer.parseInt(id), name, director, location, actor, formatter.parse(filmedTime));
        System.out.println("修改了" + updatedRow + "行");
        resp.sendRedirect("/movies");
    }
}
