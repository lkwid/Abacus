package lkwid.quizabacus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends AppCompatActivity {
    @BindView(R.id.name_field)
    EditText mEditText;

    private QuestionsDatabase mQuestionsDatabase = new RandomQuestionsGenerator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.next_button)
    void openNextScreen() {
        Random random = new Random();
        String name = mEditText.getText().toString();
        Intent greetingsIntent = new Intent(this,GameActivity.class);
        greetingsIntent.putExtra(GameActivity.EXTRA_NAME, name);
        List<Question> questions = mQuestionsDatabase.generateQuestions();
        while (questions.size() > 5) {
            questions.remove(random.nextInt(questions.size()));
        }
        Collections.shuffle(questions);
        greetingsIntent.putExtra(GameActivity.EXTRA_QUESTIONS, new ArrayList<>(questions));

        startActivity(greetingsIntent);
    }
}
