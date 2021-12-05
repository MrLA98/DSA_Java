package com.algorithm.kmp;

public class ViolentSearch {
    public static void main(String[] args) {
        System.out.println(violentSearch("i like java", "java"));

    }

    public static int violentSearch(String str1, String str2){
        char [] s1 = str1.toCharArray();
        char [] s2 = str2.toCharArray();
        int i = 0, j = 0;
        while(i < s1.length && j < s2.length){
            if(s1[i] == s2[j]){
                ++i;
                ++j;
                continue;
            }
            i = i - j + 1;
            j = 0;
        }
        return j == s2.length ? i-j : -1;
    }
}
