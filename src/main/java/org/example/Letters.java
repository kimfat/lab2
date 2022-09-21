package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Letters {
    private int lower;
    private int upper;
    private int space;
    private int digit;
    private int sum;

    public void incLower(){
        lower++;
    }
    public void incUpper(){
        upper++;
    }
    public void incSpace(){
        space++;
    }
    public void incDigit(){
        digit++;

    }
    public void incSum(){
        sum = lower+upper;
    }
    public int getLower() {
        return lower;
    }
    public int getUpper() {
        return upper;
    }
    public int getSum() {
        return sum;
    }
    public Letters add(Letters letters){
        this.upper += letters.getUpper();
        this.lower += letters.getLower();
        return this;
    }
    public static Letters sum(Letters l1, Letters l2){
        Letters l = new Letters();
        l.add(l1);
        l.add(l2);
        return l;
    }
    public static Letters stringToLettersCount(String s){
        Letters l = new Letters();
        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) l.incUpper();
            else if (Character.isLowerCase(c)) l.incLower();
            else if (Character.isWhitespace(c)) l.incSpace();
            else if (Character.isDigit(c)) l.incDigit();
        }
        l.incSum();
        return l;
    }


    public String toString() {
        return "UpperCase: " + upper + ", LowerCase: " + lower + ", Sum: " + sum + ", Spaces: " + space + ", Digit: " + digit;
    }
}
