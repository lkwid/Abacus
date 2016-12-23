package lkwid.quizabacus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends AppCompatActivity {
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_QUESTIONS = "questions";
    public static final String CURRENT_QUESTION = "current question";
    public static final String CHOICES = "choices";

    @BindView(R.id.question)
    TextView mQuestion;
    @BindView(R.id.answer_choice)
    RadioGroup mAnswers;
    @BindViews({R.id.answer_1, R.id.answer_2, R.id.answer_3})
    List<RadioButton> mRadioButtons;
    @BindView(R.id.back)
    Button mBackButton;
    @BindView(R.id.next)
    Button mNextButton;

    private String mPlayerName;
    private List<Question> mQuestions;
    private int mCurrentQuestion;
    private int[] mChoices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        mPlayerName = getIntent().getStringExtra(EXTRA_NAME);
        mQuestions = (List<Question>) getIntent().getSerializableExtra(EXTRA_QUESTIONS);
        mChoices = new int[mQuestions.size()];

        refreshView();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mChoices[mCurrentQuestion] = mAnswers.getCheckedRadioButtonId();
        outState.putInt(CURRENT_QUESTION, mCurrentQuestion);
        outState.putIntArray(CHOICES, mChoices);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCurrentQuestion = savedInstanceState.getInt(CURRENT_QUESTION, 0);
        mChoices = savedInstanceState.getIntArray(CHOICES);

        refreshView();
    }

    private void refreshView() {
        Question question = mQuestions.get(mCurrentQuestion);
        mQuestion.setText(question.getQuestion());

        int index = 0;
        for (RadioButton rb : mRadioButtons) {
            rb.setText(question.getAnswers().get(index++));
        }

        mBackButton.setVisibility(mCurrentQuestion == 0 ? View.GONE : View.VISIBLE);
        mNextButton.setText(mCurrentQuestion == mQuestions.size() - 1 ? "Zakończ" : "Dalej");

        mAnswers.clearCheck();

        if (mChoices[mCurrentQuestion] > 0) {
            mAnswers.check(mChoices[mCurrentQuestion]);
        }
    }

    @OnClick(R.id.back)
    void onBackClick() {
        mChoices[mCurrentQuestion] = mAnswers.getCheckedRadioButtonId();
        mCurrentQuestion--;
        refreshView();
    }

    @OnClick(R.id.next)
    void onNextClick() {
        mChoices[mCurrentQuestion] = mAnswers.getCheckedRadioButtonId();
        boolean isLastQuestion = mCurrentQuestion + 1 == mQuestions.size();
        if (isLastQuestion) {
            countResults();
            return;
        }
        mCurrentQuestion++;
        refreshView();
    }

    private void countResults() {
        int correctAnswers = 0;
        int questionsCount = mQuestions.size();

        for (int i = 0; i < questionsCount; i++) {
            int correctAnswerIndex = mQuestions.get(i).getPositionOfCorrectAnswer();
            int chosenRadioButtonId = mChoices[i];
            int choiceIndex = -1;

            for (int j = 0; j < mRadioButtons.size(); j++) {
                if (mRadioButtons.get(j).getId() == chosenRadioButtonId) {
                    choiceIndex = j;
                    break;
                }
            }
            if (correctAnswerIndex == choiceIndex) {
                correctAnswers++;
            }
        }

        QuizResultDialogFragment.createDialog(mPlayerName, correctAnswers, questionsCount)
                .show(getSupportFragmentManager(),"");
    }
}
