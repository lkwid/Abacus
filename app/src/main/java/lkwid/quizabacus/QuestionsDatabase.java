package lkwid.quizabacus;

import java.util.List;

public interface QuestionsDatabase {
    List<Question> generateQuestions(int numbersRange, int difficultyLevel);
}
