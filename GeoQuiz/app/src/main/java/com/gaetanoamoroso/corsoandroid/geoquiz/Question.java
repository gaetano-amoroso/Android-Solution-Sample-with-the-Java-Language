package com.gaetanoamoroso.corsoandroid.geoquiz;

public class Question {
    int m_resource_id;
    boolean m_answer_true;

    public int getResource_id() {
        return m_resource_id;
    }

    public void setResource_id(int resource_id) {
        m_resource_id = resource_id;
    }

    public boolean isAnswer_true() {
        return m_answer_true;
    }

    public void setAnswer_true(boolean answer_true) {
        m_answer_true = answer_true;
    }

    public Question(int resource_id, boolean answer_true) {
        m_resource_id =  resource_id;
        m_answer_true = answer_true;
    }
}
