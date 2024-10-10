package vn.atstar.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.atstar.entity.Category;
import vn.atstar.service.ICategoryService;
import vn.atstar.service.impl.CategoryServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig()
@WebServlet(
        urlPatterns = {"/admin/categories",
                "/admin/category/add",
                "/admin/category/edit",
                "/admin/category/insert", "/admin/category/delete"}
)
public class CategoryController extends HttpServlet {
    private final ICategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String url = req.getRequestURI();

        if (url.contains("/admin/category/add")) {
            req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
        } else if (url.contains("/admin/categories")) {
            List<Category> categories = categoryService.findAll();
            req.setAttribute("categoryList", categories);
            req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
        } else if (url.contains("/admin/category/delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                categoryService.delete(id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        } else if (url.contains("/admin/category/edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            Category category = categoryService.findById(id);
            req.setAttribute("category", category);
            req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
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
        if (!uploadDir.exists())
            uploadDir.mkdir();

        if (url.contains("/admin/category/insert")) {
            // Get data from view
            String categoryName = req.getParameter("categoryName");
            int status = Integer.parseInt(req.getParameter("status"));
            String imageLink = req.getParameter("imageLink");

            // Create Category instance without Lombok
            Category categoryModel = new Category();
            categoryModel.setCategoryName(categoryName);
            categoryModel.setStatus(status);

            // Handle upload file
            try {
                Part part = req.getPart("image");
                if (part.getSize() > 0) {
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
                    fname = System.currentTimeMillis() + "." + ext;
                    // Upload
                    part.write(uploadPath + "/" + fname);
                    categoryModel.setImage(fname);
                } else if (imageLink != null) {
                    categoryModel.setImage(imageLink);
                } else {
                    categoryModel.setImage("avatar.png");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Service
            categoryService.insert(categoryModel);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        } else if (url.contains("/admin/category/edit")) {
            // Get data from view
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            String categoryName = req.getParameter("categoryName");
            int status = Integer.parseInt(req.getParameter("status"));
            String imageLink = req.getParameter("imageLink");

            // Create Category instance without Lombok
            Category categoryModel = new Category();
            categoryModel.setCategoryId(categoryId);
            categoryModel.setCategoryName(categoryName);
            categoryModel.setStatus(status);

            // Handle upload file
            try {
                Part part = req.getPart("image");
                if (part.getSize() > 0) {
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
                    fname = System.currentTimeMillis() + "." + ext;
                    // Upload
                    part.write(uploadPath + "/" + fname);
                    categoryModel.setImage(fname);
                } else if (imageLink != null) {
                    categoryModel.setImage(imageLink);
                } else {
                    categoryModel.setImage("avatar.png");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Service
            categoryService.update(categoryModel);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }
    }
}
