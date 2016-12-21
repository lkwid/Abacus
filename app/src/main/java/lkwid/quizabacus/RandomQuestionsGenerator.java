package lkwid.quizabacus;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomQuestionsGenerator implements QuestionsDatabase {
    public static final int QUIZ_DIFFICULTY = 10;

    @Override
    public List<Question> generateQuestions() {
        Random random = new Random();
        Question question = new Question();
        List<Question> setOfQuestions = new LinkedList<>();
        List<String> answers = new LinkedList<>();
        int left = random.nextInt(QUIZ_DIFFICULTY), right = random.nextInt(QUIZ_DIFFICULTY);
        int correctAnswer;
        int positionOfCorrectAnswer;

        for (int i = 0; i < 100; i++) {
            question.setQuestion(String.format("%d + %d = ?", left, right));
            correctAnswer = left + right;

            for (int j = 0; j < 3; j++) {
                answers.add(random.nextInt(QUIZ_DIFFICULTY*2)+"");
            }
            positionOfCorrectAnswer = random.nextInt(3);
            answers.set(positionOfCorrectAnswer, correctAnswer + "");

            question.setAnswers(answers);
            question.setPositionOfCorrectAnswer(positionOfCorrectAnswer);

            setOfQuestions.add(question);

        }

        return setOfQuestions;
    }
}
