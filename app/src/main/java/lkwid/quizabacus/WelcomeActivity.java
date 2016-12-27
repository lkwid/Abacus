package lkwid.quizabacus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

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
        Intent greetingsIntent = new Intent(this,DifficultyActivity.class);
        greetingsIntent.putExtra(DifficultyActivity.EXTRA_NAME, name);

        startActivity(greetingsIntent);
    }
}
