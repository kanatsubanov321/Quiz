package kg.itacademy.service;

import kg.itacademy.dao.QuizDao;
import kg.itacademy.model.Quiz;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("quiz")
public class QuizService {
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Quiz> getQuiz_JSON() {
        QuizDao quizDao = new QuizDao();
        return quizDao.getAllQuiz();
    }

    @GET
    @Path("/{quizId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Quiz getQuiz(@PathParam("quizId") Integer quizId) {
        QuizDao quizDao = new QuizDao();
        return quizDao.getQuiz(quizId);
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addQuiz(Quiz quiz) {
        QuizDao db = new QuizDao();
            db.addQuiz(quiz);
            return "Quiz is added";
    }

    @DELETE
    @Path("/{quizId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteQuiz(@PathParam("quizId") Integer quizId) {
        QuizDao quizDao = new QuizDao();
            quizDao.deleteQuiz(quizId);
        }
    }

