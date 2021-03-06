package kg.itacademy.service;

import kg.itacademy.dao.AnswerDao;
import kg.itacademy.dao.QuestionDao;
import kg.itacademy.model.Answer;
import kg.itacademy.model.Question;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("answers")
public class AnswerService {
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Answer> getAnswers_JSON() {
        AnswerDao answerDao = new AnswerDao();
        return answerDao.getAllAnswers();
    }
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addAnswers(Answer answer) {
        AnswerDao db = new AnswerDao();
            db.addAnswer(answer);
            return "Answer is added";
    }

    @GET
    @Path("/{answerId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Answer getAnswer(@PathParam("answerId") Integer answerId) {
        AnswerDao answerDao = new AnswerDao();
        QuestionDao questionDao = new QuestionDao();
        if(!answerDao.checkAnswer(answerDao.getAnswer(),questionDao.getQuestion())) {
            return null;
        }
        return answerDao.getAnswer(answerId);
    }
//    @GET
//    @Path("/{answerId}")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Answer getAnswer(@PathParam("answerId") Integer answerId) {
//        AnswerDao answerDao = new AnswerDao();
//        return answerDao.getAnswer(answerId);
//    }

    @PUT
    @Path("/updateAnswer")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON})
    public String updateAnswer(Answer answer) {
        AnswerDao db = new AnswerDao();
        db.updateAnswerText(answer);
        return answer.getText();
    }

    @DELETE
    @Path("/{answerId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteAnswer(@PathParam("answerId") Integer answerId) {
        AnswerDao answerDao = new AnswerDao();
        answerDao.deleteAnswer(answerId);
    }
}
