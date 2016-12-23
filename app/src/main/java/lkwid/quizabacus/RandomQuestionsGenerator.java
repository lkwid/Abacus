package lkwid.quizabacus;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomQuestionsGenerator implements QuestionsDatabase {
    public static final int QUIZ_DIFFICULTY = 10;

    @Override
    public List<Question> generateQuestions() {
        List<Question> setOfQuestions = new LinkedList<>();
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            Question question = new Question();
            int left = random.nextInt(QUIZ_DIFFICULTY-1)+1, right = random.nextInt(QUIZ_DIFFICULTY-1)+1;
            int correctAnswer;
            int positionOfCorrectAnswer;
            List<String> answers = new LinkedList<>();

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
