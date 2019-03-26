package kg.itacademy.service;

import kg.itacademy.dao.QuestionDao;
import kg.itacademy.model.Question;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("questions")
public class QuestionService {
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Question> getQuestions_JSON() {
        QuestionDao questionDao = new QuestionDao();
        return questionDao.getAllQuestions();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addQuestion(Question question) {
        QuestionDao db = new QuestionDao();
        if (db.addQuestion(question)) {
            db.addQuestion(question);
            return "Question is successfully added";
        }
        return "Question is not added";
    }


    @PUT
    @Path("/updateQuestion")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON})
    public String updateQuestion(Question question) {
        QuestionDao db = new QuestionDao();
        db.updateQuestionText(question);
        return question.getText();
    }

    @GET
    @Path("/{questionId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Question getQuestion(@PathParam("questionId") Integer questionId) {
        QuestionDao questionDao = new QuestionDao();
        return questionDao.getQuestion(questionId);
    }

    @DELETE
    @Path("/{questionId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteQuestion(@PathParam("questionId") Integer questionId) {
        QuestionDao questionDao = new QuestionDao();
        if (questionDao.deleteQuestion(questionId)) {
            questionDao.deleteQuestion(questionId);
        }
    }
}
