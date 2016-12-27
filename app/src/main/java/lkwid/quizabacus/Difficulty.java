package lkwid.quizabacus;

import java.util.Random;

public abstract class Difficulty {
    Random random = new Random();
    protected int left = random.nextInt(RandomQuestionsGenerator.numbersRange - 1) + 1, rightL1 = random.nextInt(RandomQuestionsGenerator.numbersRange - 1) + 1;
    protected int correctAnswer;
    protected String askedQuestion;

    public int getCorrectAnswer(Question question) {
        int left = random.nextInt(RandomQuestionsGenerator.numbersRange - 1) + 1, rightL1 = random.nextInt(RandomQuestionsGenerator.numbersRange - 1) + 1;
        char sign = getSign();
        correctAnswer = left + rightL1;
        if (sign == '-') {
            if (left < rightL1) {
                rightL1 = random.nextInt(left) + 1;
            }
            correctAnswer = left - rightL1;
        }
        askedQuestion = String.format("%d %c %d", left, sign, rightL1);
        question.setQuestion(askedQuestion);
        return correctAnswer;
    }

    public char getSign() {
        char sign = '+';
        if (random.nextInt(2) == 1)
            sign = '-';
        return sign;
    }
}
