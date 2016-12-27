package lkwid.quizabacus;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomQuestionsGenerator implements QuestionsDatabase {
    public static int numbersRange;
    public static int difficultyLevel;

    @Override
    public List<Question> generateQuestions(int numbersRange, int difficultyLevel) {
        List<Question> setOfQuestions = new LinkedList<>();
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            Question question = new Question();
            Difficulty difficulty = new DifficultyLevelSet();
            int correctAnswer;
            int positionOfCorrectAnswer;
            List<String> answers = new LinkedList<>();

            correctAnswer = difficulty.getCorrectAnswer(question);

            do {
                String answerCandidate;
                do {
                    answerCandidate = (random.nextInt(numbersRange * (difficultyLevel+1))+"");
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
