package kg.itacademy.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;

@XmlRootElement(name = "quiz")
public class Quiz {
    int id;
    Timestamp dateTime;
    int userId;
    int totalGrade;

    public Quiz() {
    }

    public Quiz(int id, Timestamp dateTime, int userId, int totalGrade) {
        this.id = id;
        this.dateTime = dateTime;
        this.userId = userId;
        this.totalGrade = totalGrade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(int totalGrade) {
        this.totalGrade = totalGrade;
    }
}
