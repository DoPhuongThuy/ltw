package vn.atstar.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.atstar.entity.Video;
import vn.atstar.service.IVideoService;
import vn.atstar.service.impl.VideoServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig()
@WebServlet(
        urlPatterns = {"/admin/videos",
                "/admin/video/add",
                "/admin/video/edit",
                "/admin/video/insert", "/admin/video/delete"}
)
public class VideoController extends HttpServlet {
    private final IVideoService videoService = new VideoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String url = req.getRequestURI();

        if (url.contains("/admin/video/add")) {
            req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
        } else if (url.contains("/admin/videos")) {
            List<Video> videos = videoService.findAll();
            req.setAttribute("videoList", videos);
            req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
        } else if (url.contains("/admin/video/delete")) {
             String id = req.getParameter("id");
            try {
                videoService.delete(id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        } else if (url.contains("/admin/video/edit")) {
            String id = req.getParameter("id");
            Video video = videoService.findById(id);
            req.setAttribute("video", video);
            req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String url = req.getRequestURI();
        String fname = "";
        String uploadPath = req.getServletContext().getRealPath("/uploads");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        if (url.contains("/admin/video/insert")) {
            // Get data from view
            String title = req.getParameter("title");
            int active = Integer.parseInt(req.getParameter("active"));
            int views = Integer.parseInt(req.getParameter("views"));
            String description = req.getParameter("description");
            String imageLink = req.getParameter("imageLink");

            // Create Video instance
            Video videoModel = new Video();
            videoModel.setTitle(title);
            videoModel.setActive(active);
            videoModel.setViews(views);
            videoModel.setDescription(description);

            // Handle file upload
            try {
                Part part = req.getPart("poster");
                if (part.getSize() > 0) {
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
                    fname = System.currentTimeMillis() + "." + ext;
                    part.write(uploadPath + "/" + fname);
                    videoModel.setPoster(fname);
                } else if (imageLink != null) {
                    videoModel.setPoster(imageLink);
                } else {
                    videoModel.setPoster("default.png");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Service
            videoService.insert(videoModel);
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        } else if (url.contains("/admin/video/edit")) {
            // Get data from view
            String videoId = req.getParameter("videoId");
            String title = req.getParameter("title");
            int active = Integer.parseInt(req.getParameter("active"));
            int views = Integer.parseInt(req.getParameter("views"));
            String description = req.getParameter("description");
            String imageLink = req.getParameter("imageLink");

            // Create Video instance
            Video videoModel = new Video();
            videoModel.setVideoId(videoId);
            videoModel.setTitle(title);
            videoModel.setActive(active);
            videoModel.setViews(views);
            videoModel.setDescription(description);

            // Handle file upload
            try {
                Part part = req.getPart("poster");
                if (part.getSize() > 0) {
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
                    fname = System.currentTimeMillis() + "." + ext;
                    part.write(uploadPath + "/" + fname);
                    videoModel.setPoster(fname);
                } else if (imageLink != null) {
                    videoModel.setPoster(imageLink);
                } else {
                    videoModel.setPoster("default.mp4");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Service
            videoService.update(videoModel);
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }
    }
}
