package kg.itacademy.dao;

import kg.itacademy.model.Answer;
import kg.itacademy.model.Question;
import kg.itacademy.model.Quiz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuizDao extends DbConnector {

    public ArrayList<Quiz> getAllQuiz() {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        String SQL = "SELECT * FROM quiz";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            Quiz quiz = null;
            while (rs.next()) {
                quiz = new Quiz(rs.getInt("id"),
                        rs.getTimestamp("date"),
                        rs.getInt("user_id"),
                        rs.getInt("total_grade")
                );
                quizzes.add(quiz);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return quizzes;
    }
    public int getTotalGrade(Answer answer, int totalGrade, Question question) {
        String SQL = "select totalGrade from quiz";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (answer.isCorrect()) {
                    totalGrade += question.getGrade();
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return totalGrade;
    }

    public Quiz getQuiz(int quizId) {
        Quiz quiz = null;
        String SQL = "SELECT * FROM quiz where id = ?";
        try (Connection conn = connect()) {
            PreparedStatement stmt = conn.prepareStatement(SQL);
            stmt.setInt(1, quizId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                quiz = new Quiz(rs.getInt("id"),
                        rs.getTimestamp("date"),
                        rs.getInt("user_id"),
                        rs.getInt("total_grade")
                );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return quiz;
    }
    public boolean addQuiz(Quiz quiz) {
        String SQL =
                "insert into quiz " +
                        "(date, user_id, total_grade) " +
                        "values (Now(), ?, ?)";
        try (Connection conn = connect()) {
            PreparedStatement stmt =
                    conn.prepareStatement(SQL);
            QuizDao quizDao = new QuizDao();
            AnswerDao answerDao = new AnswerDao();
            QuestionDao questionDao = new QuestionDao();
            int totalGrade = quizDao.getTotalGrade(answerDao.getAnswer(),quiz.getTotalGrade(),
                    questionDao.getQuestion());

            stmt.setInt(1, quiz.getUserId());
            stmt.setInt(2, totalGrade);
            stmt.executeUpdate();
            System.out.println("Successfully");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean deleteQuiz(int quizId) {
        String SQL = "delete from quiz where id = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(SQL)
        ) {
            stmt.setInt(1, quizId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }
}
