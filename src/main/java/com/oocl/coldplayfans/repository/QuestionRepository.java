package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.dao.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository {

    public List<Question> generateQuestion();
}
