package enibdiscovery.enibdiscovery.model;

import android.util.Log;

import java.util.List;


public class QuestionEdm {
    private int mQuestionEdm;
    private List<Integer> mChoiceListEdm;
    private int mAnswerIndexEdm;

    public QuestionEdm(int questionEdm, List<Integer> choiceListEdm, int answerIndexEdm){
        this.setQuestionEdm(questionEdm);
        this.setChoiceListEdm(choiceListEdm);
        this.setAnswerIndexEdm(answerIndexEdm);
    }
    public int getQuestionEdm() {
        Integer i= mQuestionEdm;
        Log.d("getQuestionEdm",i.toString());
        return mQuestionEdm;
    }
    public void setQuestionEdm(int QuestionEdm) {
        mQuestionEdm = QuestionEdm;
    }

    public List<Integer> getChoiceListEdm() {
        return mChoiceListEdm;
    }

    public void setChoiceListEdm(List<Integer> choiceListEdm) {
        if (choiceListEdm == null) {
            throw new IllegalArgumentException("Impossible null");
        }

        mChoiceListEdm = choiceListEdm;
    }

    public int getAnswerIndexEdm() {
        return mAnswerIndexEdm;
    }

    public void setAnswerIndexEdm(int answerIndexEdm) {
        if (answerIndexEdm < 0 || answerIndexEdm >= mChoiceListEdm.size()) {
            throw new IllegalArgumentException("Impossible index");
        }

        mAnswerIndexEdm = answerIndexEdm;
    }
}