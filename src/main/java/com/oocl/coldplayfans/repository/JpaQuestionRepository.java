package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaQuestionRepository extends JpaRepository<Question, Integer> {
    @Query(value = "SELECT * FROM question ORDER BY RAND() LIMIT 5", nativeQuery = true)
    List<Question> generateQuestion();

    List<Question> findAllById(Iterable<Integer> questionIds);
}
