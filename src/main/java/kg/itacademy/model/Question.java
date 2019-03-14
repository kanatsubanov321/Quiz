package kg.itacademy.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "question")
public class Question {
    int id;
    String text;
    String description;
    Category category;
    int grade;

    public Question() {
    }

    public Question(int id, String text, String description, Category category, int grade) {
        this.id = id;
        this.text = text;
        this.description = description;
        this.category = category;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
