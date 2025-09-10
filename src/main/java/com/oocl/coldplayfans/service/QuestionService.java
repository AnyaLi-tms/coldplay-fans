package com.oocl.coldplayfans.service;


import com.oocl.coldplayfans.dao.Question;
import com.oocl.coldplayfans.dto.AnswerRequest;
import com.oocl.coldplayfans.dto.Option;
import com.oocl.coldplayfans.dto.QuestionResponse;
import com.oocl.coldplayfans.repository.QuestionDBRepository;
import com.oocl.coldplayfans.util.OptionSplitterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private QuestionDBRepository questionDBRepository;

    public List<QuestionResponse> generateQuestion() {
        List<Question> questionList = questionDBRepository.generateQuestion();
        List<QuestionResponse> questionResponseList = new ArrayList<>();
        for (Question question : questionList) {
//            List<String> optionList = Arrays.stream(question.getOptions().split("\\|")).collect(Collectors.toList());
            List<Option> options = OptionSplitterUtil.splitOptions(question.getOptions());
            QuestionResponse questionResponse = new QuestionResponse(question.getId(), options, question.getQuestionText());
            questionResponseList.add(questionResponse);
        }
        return questionResponseList;
    }

    public Boolean validateAnswers(List<AnswerRequest> answers) {
        return answers.stream()
                .allMatch(answer -> {
                    Question question = questionDBRepository.findById(answer.getQuestionId());
                    return question != null && Objects.equals(answer.getAnswer(), question.getAnswer());
                });
    }
}
