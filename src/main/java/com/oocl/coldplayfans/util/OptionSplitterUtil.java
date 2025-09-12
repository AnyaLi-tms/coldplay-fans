package com.oocl.coldplayfans.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.oocl.coldplayfans.dto.Option;

public class OptionSplitterUtil {

    // 正则表达式：匹配以A-D开头，后跟.或)或空格的选项格式
    private static final Pattern OPTION_PATTERN = Pattern.compile("^([A-D])([.）)\\s]+)(.*)$");


    public static List<Option> splitOptions(String optionString) {
        List<Option> options = new ArrayList<>();
        if (optionString == null || optionString.trim().isEmpty()) {
            return options;
        }
        // 先按|分割出每个选项
        String[] optionArray = optionString.split("\\|");

        for (String option : optionArray) {
            Matcher matcher = OPTION_PATTERN.matcher(option.trim());
            if (matcher.matches()) {
                String letter = matcher.group(1);
                String content = matcher.group(3).trim(); // 选项内容
                options.add(new Option(letter, content));
            } else {
                options.add(new Option(null, option.trim()));
            }
        }
        return options;
    }
}