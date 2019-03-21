package kg.itacademy.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "questionAndAnswer")
public class QuestionAndAnswer {
    int id;
    int quizId;
    int questionId;
    int answerId;
    boolean isCorrect;

    public QuestionAndAnswer() {
    }

    public QuestionAndAnswer(int id, int quizId, int questionId, int answerId, boolean isCorrect) {
        this.id = id;
        this.quizId = quizId;
        this.questionId = questionId;
        this.answerId = answerId;
        this.isCorrect = isCorrect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
