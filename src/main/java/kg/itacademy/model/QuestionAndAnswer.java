package kg.itacademy.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "questionAndAnswer")
public class QuestionAndAnswer {
    int id;
    Quiz quiz;
    Question question;
    Answer answer;
    boolean isCorrect;

    public QuestionAndAnswer() {
    }

    public QuestionAndAnswer(int id, Quiz quiz, Question question, Answer answer, boolean isCorrect) {
        this.id = id;
        this.quiz = quiz;
        this.question = question;
        this.answer = answer;
        this.isCorrect = isCorrect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
