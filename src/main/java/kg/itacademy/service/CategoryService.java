package kg.itacademy.service;

import kg.itacademy.dao.CategoryDao;
import kg.itacademy.model.Category;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/categories")
public class CategoryService {
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Category> getCategory_JSON() {
        CategoryDao categoryDao = new CategoryDao();
        return categoryDao.getAllCategories();
    }
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
        return category.getName();
    }
    @DELETE
    @Path("/{categoryId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteCategory(@PathParam("categoryId") Integer categoryId) {
        CategoryDao categoryDao = new CategoryDao();
        if (categoryDao.deleteCategory(categoryId)) {
            categoryDao.deleteCategory(categoryId);
        }
    }
}

