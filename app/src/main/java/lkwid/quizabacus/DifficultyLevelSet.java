package lkwid.quizabacus;

public class DifficultyLevelSet extends Difficulty {
    private int rightL2 = random.nextInt(NUMBERS_RANGE - 1) + 1;
    private int questionDifficultyBuilder = 1;

    @Override
    public int getCorrectAnswer(Question question) {
        correctAnswer = super.getCorrectAnswer(question);
        while (questionDifficultyBuilder < DIFFICULTY) {
            char sign = getSign();
            if (sign == '-') {
                if (correctAnswer < rightL2) {
                    rightL2 = random.nextInt(correctAnswer + 1);
                }
                correctAnswer -= rightL2;
            } else {
                correctAnswer += rightL2;
            }
            askedQuestion = String.format(super.askedQuestion + " %c %d", sign, rightL2);
            question.setQuestion(askedQuestion);
            questionDifficultyBuilder++;
        }
        return correctAnswer;
    }
}
