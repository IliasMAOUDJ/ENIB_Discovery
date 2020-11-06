package enibdiscovery.enibdiscovery.model;

import java.util.Collections;
import java.util.List;

public class QuestionBankEdm {
    private List<QuestionEdm> mQuestionListEdm;
    private int mNextQuestionIndexEdm;

    public QuestionBankEdm(List<QuestionEdm> questionListEdm){
        mQuestionListEdm=questionListEdm;
        Collections.shuffle(mQuestionListEdm);
        mNextQuestionIndexEdm=0;
    }

    public QuestionEdm getQuestionEdm(){
        if (mNextQuestionIndexEdm==mQuestionListEdm.size()){
            mNextQuestionIndexEdm=0;
        }
        return mQuestionListEdm.get(mNextQuestionIndexEdm++);
    }
}

