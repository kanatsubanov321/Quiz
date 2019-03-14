package kg.itacademy.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;

@XmlRootElement(name = "quiz")
public class Quiz {
    int id;
    LocalDateTime dateTime;
    User user;
    int totalGrade;

    public Quiz() {
    }

    public Quiz(int id, LocalDateTime dateTime, User user, int totalGrade) {
        this.id = id;
        this.dateTime = dateTime;
        this.user = user;
        this.totalGrade = totalGrade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(int totalGrade) {
        this.totalGrade = totalGrade;
    }
}
