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
import java.util.HashMap;
import java.util.List;

public class AddMovieServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!ServletFileUpload.isMultipartContent(req)){
            System.out.println("Post");
            return;
        }


        try {
            String uploadPath = this.getServletContext().getRealPath("/media/upload");
            System.out.println(uploadPath);
            File uploadFile = new File(uploadPath);
            if (!uploadFile.exists()){
                System.out.println(uploadFile.mkdirs());
            }

            String tmpPath = this.getServletContext().getRealPath("/media/tmp");
            File file = new File(tmpPath);
            if (!file.exists()){
                file.mkdirs();
            }

            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(1024*1024);
            factory.setRepository(file);

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setHeaderEncoding("UTF-8");
            upload.setFileSizeMax(1024*1024*10);

            List<FileItem> fileItems = upload.parseRequest(req);
            HashMap<String, String>Map = new HashMap<String, String>();


            for (FileItem fileItem : fileItems) {
                if (!fileItem.isFormField()){
                    String uploadFileName = fileItem.getName();
                    if (uploadFileName.trim().equals("") || uploadFileName==null){
                        continue;
                    }
                    String subName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
                    String id = Map.get("id");
                    String realPath = uploadPath + "/" + id;
                    File realPathFile = new File(realPath);
                    if (!realPathFile.exists()){
                        realPathFile.mkdirs();
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
                }else{
                    String nameField = fileItem.getFieldName();
                    String text = fileItem.getString("UTF-8");
                    Map.put(nameField, text);
                    System.out.println(nameField+":"+text);
                }
            }
            String id = Map.get("id");
            String director = Map.get("director");
            String location = Map.get("location");
            String actor = Map.get("actor");
            String name = Map.get("name");
            String filmedTime = Map.get("filmedTime");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            MovieService movieService = new MovieServiceImpl();
            int updatedRow = 0;
            try {
                updatedRow = movieService.addMovie(Integer.parseInt(id), name, director, location, actor, formatter.parse(filmedTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("增加了" + updatedRow + "行");
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("/movies");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
