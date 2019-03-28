package kg.itacademy.service;

import kg.itacademy.dao.CategoryDao;
import kg.itacademy.dao.UserDao;
import kg.itacademy.model.Category;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/categories")
public class CategoryService {
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Category> getCategories_JSON(@HeaderParam("user-key") String login,
                                             @HeaderParam("password-key") String password) {
        UserDao userDao = new UserDao();
        if(!userDao.authorize(login, password)) {
            return null;
        }
        List<Category> categories = getCategoryByUser(login,password);
        return categories;
    }
//    @GET
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public List<Category> getCategory_JSON() {
//        CategoryDao categoryDao = new CategoryDao();
//        return categoryDao.getAllCategories();
//    }
    @GET
    @Path("/{categoryId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Category getCategory(@PathParam("categoryId") Integer categoryId) {
        CategoryDao categoryDao = new CategoryDao();
        return categoryDao.getCategory(categoryId);
    }
    @PUT
    @Path("/updateCategory")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON})
    public String updateCategory(Category category) {
        CategoryDao db = new CategoryDao();
        db.updateCategoryName(category);
        return category.getName();
    }
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addCategory(Category category) {
        CategoryDao db = new CategoryDao();
            db.addCategory(category);
            return "Category is added";
    }

    @DELETE
    @Path("/{categoryId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteCategory(@PathParam("categoryId") Integer categoryId) {
        CategoryDao categoryDao = new CategoryDao();
            categoryDao.deleteCategory(categoryId);
    }
    public List<Category> getCategoryByUser(String login, String password) {
      UserDao userDao = new UserDao();
        if (!userDao.authorize(login, password)) {
            return null;
        }
        List<Category> result = new ArrayList<>();
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categories = categoryDao.getAllCategories();
        for (Category cat : categories) {
            if (userDao.getCategoryByUser().getLogin().equals(login))
                result.add(cat);
        }
        return result;
    }
}

