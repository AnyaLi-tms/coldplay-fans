package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.model.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository {

    public List<Question> generateQuestion();
}
