package com.oocl.coldplayfans.dto;

public class Option {
    private String option;
    private String description;

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Option(String option, String description) {
        this.option = option;
        this.description = description;
    }
}
