package kg.itacademy.service;

import kg.itacademy.dao.QuestionAndAnswerDao;
import kg.itacademy.model.QuestionAndAnswer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("questionAndAnswer")
public class QuestionAndAnswerService {
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<QuestionAndAnswer> getQuestionAndAnswer_JSON() {
        QuestionAndAnswerDao questionAndAnswerDao = new QuestionAndAnswerDao();
        return questionAndAnswerDao.getAllQuestionAndAnwer();
    }

    @GET
    @Path("/{questionAndAnswerId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public QuestionAndAnswer getQuestionAndAnswer(@PathParam("questionAndAnswerId") Integer questionAndAnswerId) {
        QuestionAndAnswerDao questionAndAnswerDao = new QuestionAndAnswerDao();
        return questionAndAnswerDao.getQuestionAndAnswer(questionAndAnswerId);
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON})
    public String addQuestionAndAnswer(QuestionAndAnswer questionAndAnswer) {
        QuestionAndAnswerDao db = new QuestionAndAnswerDao();
        if (db.addQuestionAndAnswer(questionAndAnswer)) {
            db.addQuestionAndAnswer(questionAndAnswer);
            return "Question and Answer is successfully added";
        }
        return "Question and Answer is not added";
    }

    @DELETE
    @Path("/{questionAndAnswerId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void deleteQuestionAndAnswer(@PathParam("questionAndAnswerId") Integer questionAndAnswerId) {
        QuestionAndAnswerDao questionAndAnswerDao = new QuestionAndAnswerDao();
        if (questionAndAnswerDao.deleteQuestionAndAnswer(questionAndAnswerId)) {
            questionAndAnswerDao.deleteQuestionAndAnswer(questionAndAnswerId);
        }
    }
}
