package com.oocl.coldplayfans.controller;


import com.oocl.coldplayfans.dao.Question;
import com.oocl.coldplayfans.dto.AnswerRequest;
import com.oocl.coldplayfans.dto.QuestionResponse;
import com.oocl.coldplayfans.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@CrossOrigin
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping
    public List<QuestionResponse> generateQuestion(){
        return questionService.generateQuestion();
    }

    @PostMapping
    public Boolean validateAnswers(@RequestBody List<AnswerRequest> answers){
        return questionService.validateAnswers(answers);
    }
}
