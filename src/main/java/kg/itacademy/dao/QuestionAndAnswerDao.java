package kg.itacademy.dao;

import kg.itacademy.model.QuestionAndAnswer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionAndAnswerDao extends DbConnector {

    public ArrayList<QuestionAndAnswer> getAllQuestionAndAnwer() {
        ArrayList<QuestionAndAnswer> questionAndAnswers = new ArrayList<>();
        String SQL = "SELECT * FROM question_and_answer";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            QuestionAndAnswer questionAndAnswer = null;
            while (rs.next()) {
                questionAndAnswer = new QuestionAndAnswer(rs.getInt("id"),
                        rs.getInt("quiz_id"),
                        rs.getInt("question_id"),
                        rs.getInt("answer_id"),
                        rs.getBoolean("is_correct")
                );
                questionAndAnswers.add(questionAndAnswer);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return questionAndAnswers;
    }

    public QuestionAndAnswer getQuestionAndAnswer(int questionAndAnswerId) {
        QuestionAndAnswer questionAndAnswer = null;
        String SQL = "SELECT * FROM question_and_answer where id = ?";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, questionAndAnswerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                questionAndAnswer = new QuestionAndAnswer(rs.getInt("id"),
                        rs.getInt("quiz_id"),
                        rs.getInt("question_id"),
                        rs.getInt("answer_id"),
                        rs.getBoolean("is_correct")
                );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return questionAndAnswer;
    }

    public boolean addQuestionAndAnswer(QuestionAndAnswer questionAndAnswer) {
        String SQL =
                "insert into question_and_answer " +
                        "(quiz_id, question_id, answer_id) " +
                        "values (?, ?, ?)";
        try (Connection conn = connect()) {
            PreparedStatement stmt =
                    conn.prepareStatement(SQL);

            stmt.setInt(1, questionAndAnswer.getQuizId());
            stmt.setInt(2, questionAndAnswer.getQuestionId());
            stmt.setInt(3, questionAndAnswer.getAnswerId());
            stmt.executeUpdate();
            System.out.println("Successfully");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean deleteQuestionAndAnswer(int questionAndAnswerId) {
        String SQL = "delete from question_and_answer where id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)
        ) {
            stmt.setInt(1, questionAndAnswerId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
}
