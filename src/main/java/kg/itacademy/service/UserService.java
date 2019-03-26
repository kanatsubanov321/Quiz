package kg.itacademy.service;

import kg.itacademy.dao.UserDao;
import kg.itacademy.model.User;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("users")
public class UserService {
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<User> getUsers_JSON() {
        UserDao userDao = new UserDao();
        return userDao.getAllUsers();
    }
    @GET
    @Path("/{userId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User getUser(@PathParam("userId") Integer userId) {
        UserDao userDao = new UserDao();
        return userDao.getUser(userId);
    }
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addUser(User user) {
        UserDao db = new UserDao();
        if (db.addUser(user)) {
            db.addUser(user);
            return "Registration is successfully";
        }
        return "Registration is failed";
    }
    @DELETE
    @Path("/{userId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteUser(@PathParam("userId") Integer userId) {
        UserDao userDao = new UserDao();
        if (userDao.deleteUser(userId)) {
            userDao.deleteUser(userId);
        }
    }
}
