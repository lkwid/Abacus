package lkwid.quizabacus;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertNotNull;

public class RandomQuestionsGeneratorTest {
    QuestionsDatabase generator = new RandomQuestionsGenerator();
    List<Question> questionDatabase = generator.generateQuestions(10, 1);
    Question question = questionDatabase.get(0);

    @Test
    public void generatesCorrectly() throws Exception {
        System.out.println(String.format("Pytanie %s, odpowiedź %s na pozycji %d",
                question.getQuestion(), question.getAnswers().get(question.getPositionOfCorrectAnswer()), question.getPositionOfCorrectAnswer()));
        for (String answer : question.getAnswers()) {
            System.out.print(answer + " ");
        }
        assertNotNull(questionDatabase);
    }
}
