package lkwid.quizabacus;

import java.util.Random;

public abstract class Difficulty {
    public static final int NUMBERS_RANGE = 10;
    public static final int DIFFICULTY = 4;
    Random random = new Random();
    protected int left = random.nextInt(NUMBERS_RANGE - 1) + 1, rightL1 = random.nextInt(NUMBERS_RANGE - 1) + 1;
    protected int correctAnswer;
    protected String askedQuestion;

    public int getCorrectAnswer(Question question) {
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
