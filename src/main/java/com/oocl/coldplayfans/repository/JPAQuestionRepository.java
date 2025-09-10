package com.oocl.coldplayfans.repository;

import com.oocl.coldplayfans.dao.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JPAQuestionRepository extends JpaRepository<Question, Integer> {
    @Query(value = "SELECT * FROM question ORDER BY RAND() LIMIT 5", nativeQuery = true)
    List<Question> generateQuestion();
}
