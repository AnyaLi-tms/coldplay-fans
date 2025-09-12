package com.oocl.coldplayfans.model;

import jakarta.persistence.*;
@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "question_text", length = 255)
    private String questionText;

    @Column(name = "options", length = 1024)
    private String options;

    @Column(name = "answer", length = 255)
    private String answer;

    @Column(name = "status", nullable = false)
    private Boolean status = true;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    public Question() {
    }

    public enum QuestionType {
        option,
        fill_blank,
        true_false
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionText='" + questionText + '\'' +
                ", options='" + options + '\'' +
                ", answer='" + answer + '\'' +
                ", status=" + status +
                ", isDeleted=" + isDeleted +
                '}';
    }

    public Question(Integer id, String options, String questionText,  String answer, Boolean status, Boolean isDeleted) {
        this.id = id;
        this.options = options;
        this.questionText = questionText;
        this.answer = answer;
        this.status = status;
        this.isDeleted = isDeleted;
    }
}
