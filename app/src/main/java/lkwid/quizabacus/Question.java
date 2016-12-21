package lkwid.quizabacus;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
    private String question;
    private List<String> answers;
    private int positionOfCorrectAnswer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public int getPositionOfCorrectAnswer() {
        return positionOfCorrectAnswer;
    }

    public void setPositionOfCorrectAnswer(int positionOfCorrectAnswer) {
        this.positionOfCorrectAnswer = positionOfCorrectAnswer;
    }
}
