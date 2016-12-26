package lkwid.quizabacus;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomQuestionsGenerator implements QuestionsDatabase {
    public static final int NUMBERS_RANGE = 10;
    private String answerCandidate;

    @Override
    public List<Question> generateQuestions() {
        List<Question> setOfQuestions = new LinkedList<>();
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            Question question = new Question();
            int left = random.nextInt(NUMBERS_RANGE - 1) + 1, right = random.nextInt(NUMBERS_RANGE - 1) + 1;
            int correctAnswer;
            int positionOfCorrectAnswer;
            List<String> answers = new LinkedList<>();

            char sign = '+';
            if (random.nextInt(2) == 1)
                sign = '-';
            correctAnswer = left + right;
            if (sign == '-') {
                if (left < right) {
                    right = random.nextInt(left) + 1;
                }
                correctAnswer = left - right;
            }
            question.setQuestion(String.format("%d %c %d = ?", left, sign, right));

            do {
                String answerCandidate;
                do {
                    answerCandidate = (random.nextInt(NUMBERS_RANGE * 2) + "");
                } while (answerCandidate.equals(correctAnswer+""));
                if (answers.size() == 0)
                    answers.add(answerCandidate);
                else {
                    for (String answer : answers) {
                        if (answer.equals(answerCandidate)) {
                            answers.remove(answer);
                            break;
                        }
                    }
                    answers.add(answerCandidate);
                }
            } while (answers.size() < 3);

            positionOfCorrectAnswer = random.nextInt(3);
            answers.set(positionOfCorrectAnswer, correctAnswer + "");

            question.setAnswers(answers);
            question.setPositionOfCorrectAnswer(positionOfCorrectAnswer);

            setOfQuestions.add(question);

        }
        return setOfQuestions;
    }
}
