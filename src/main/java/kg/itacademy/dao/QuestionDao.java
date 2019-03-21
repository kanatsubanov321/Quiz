package kg.itacademy.dao;

import kg.itacademy.model.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionDao extends DbConnector {
    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        String SQL = "SELECT * FROM questions";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            Question ques = null;
            while(rs.next()){
                ques = new Question(rs.getInt("id"),
                        rs.getString("text"),
                        rs.getString("description"),
                        rs.getInt("category_id"),
                        rs.getInt("grade"));
                questions.add(ques);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return questions;
    }

    public Question getQuestion(int questionId) {
        Question ques = null;
        String SQL = "SELECT * FROM questions where id = ?";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, questionId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                ques = new Question(rs.getInt("id"),
                        rs.getString("text"),
                        rs.getString("description"),
                        rs.getInt("category_id"),
                        rs.getInt("grade"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return ques;
    }

    public Question addQuestion(Question question) {
        String SQL =
                "insert into questions " +
                        "(text, description, category_id, grade) " +
                        "values (?, ?, ?, ?, ?)";
        try (Connection conn = connect()) {
            PreparedStatement stmt =
                    conn.prepareStatement(SQL);

            stmt.setString(1, question.getText());
            stmt.setString(2, question.getDescription());
            stmt.setInt(3, question.getCategoryId());
            stmt.setInt(4, question.getGrade());
            stmt.executeUpdate();
            System.out.println("Successfully");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return question;
    }
    public Question updateQuestionText(Question question) {
        String SQL = "update questions set text = '?' where id = '?'";

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)
        ) {
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return question;
    }
    public boolean deleteQuestion(int questionId) {
        String SQL = "delete from questions where id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)
        ) {
            stmt.setInt(1, questionId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
}
