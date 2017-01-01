package lkwid.quizabacus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class DifficultyActivity extends AppCompatActivity {
    static final String EXTRA_NAME = "name";
    @BindView(R.id.difficulty_field)
    TextView mDifficulty;

    private QuestionsDatabase mQuestionsDatabase = new RandomQuestionsGenerator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.easy_button)
    void easyGame() {
        setDifficulty(10, 1);
    }

    @OnClick(R.id.hard_button)
    void hardGame() {
        setDifficulty(20, 2);
    }

    void setDifficulty(int numbersRange, int difficultyLevel){
        Random random = new Random();
        Intent gameIntent = new Intent(this,GameActivity.class);
        String name = getIntent().getStringExtra(EXTRA_NAME);

        RandomQuestionsGenerator.numbersRange = numbersRange;
        RandomQuestionsGenerator.difficultyLevel = difficultyLevel;
        gameIntent.putExtra(GameActivity.EXTRA_NAME,name);
        List<Question> questions = mQuestionsDatabase.generateQuestions(numbersRange, difficultyLevel);
        while (questions.size() > 5) {
            questions.remove(random.nextInt(questions.size()));
        }
        Collections.shuffle(questions);
        gameIntent.putExtra(GameActivity.EXTRA_QUESTIONS, new ArrayList<>(questions));

        startActivity(gameIntent);
    }
}
