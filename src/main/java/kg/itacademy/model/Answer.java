package kg.itacademy.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "answer")
public class Answer {
    int id;
    Question question;
    String text;
    boolean isCorrect;

    public Answer() {
    }

    public Answer(int id, Question question, String text, boolean isCorrect) {
        this.id = id;
        this.question = question;
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
