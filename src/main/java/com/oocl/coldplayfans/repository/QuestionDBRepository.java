package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.dao.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDBRepository implements QuestionRepository {

    @Autowired
    private JpaQuestionRepository jpaQuestionRepository;

    public List<Question> generateQuestion() {
        return jpaQuestionRepository.generateQuestion();
    }

    public Question findById(Integer questionId) {
        return jpaQuestionRepository.findById(questionId).orElse(null);
    }
}
