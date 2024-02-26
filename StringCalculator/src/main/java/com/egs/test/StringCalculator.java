package com.egs.test;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        String delimiters = getDelimiter(numbers);
        String[] nums = numbers.split("["+ delimiters + "]");
        int sum = 0;
        StringBuilder negetiveNum = new StringBuilder();
        for (String num : nums) {
            int newNum = NumberUtils.isNumber(num) ? NumberUtils.toInt(num) < 1000 ? NumberUtils.toInt(num) : 0 : 0;
            if (newNum >= 0) {
                sum += newNum;
            }else{
                negetiveNum.append(",").append(newNum);
            }
        }
        if (StringUtils.isNotBlank(negetiveNum)){
            throw new RuntimeException("Negatives not allowed: " + (StringUtils.substringAfter(negetiveNum.toString(),",")));
        }
        return sum;
    }

    private static String getDelimiter(String numbers){
        String delimiter = ",\n";
        String[] parts = numbers.split("\n", 2);
        if (parts[0].startsWith("//")) {
            delimiter += parts[0].substring(2);
        }
        return delimiter;
    }

}

