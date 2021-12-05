package com.algorithm.kmp;

//得背                    
public class KMP {
    public static void main(String[] args) {
        System.out.println(kmp("BBC ABCDAB ABCDABCDABDE", "AB AB"));
    }

    public static int kmp(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        // 计算部分匹配表 -- next数组
        // 依次取出s2前a个数，其最大等长的相同前缀后缀长度，就是其匹配表值
        int[] next = kmpNext(s2);
        int i = 0, j = 0;
        while(i < s1.length && j < s2.length){
            while(j > 0 && s1[i] != s2[j])
                j = next[j-1];
            if(s1[i] == s2[j])
                ++j;
            ++i;
        }
        return j == s2.length ? i-j : -1;
    }

    // 计算部分匹配表 -- next数组
    public static int[] kmpNext(char[] s) {
        int next[] = new int[s.length];
        next[0] = 0;
        // i表示取前i个字符的最长公共前后缀
        // j表示i-1的最长前后公共前后缀长度，
        for (int i = 1, j = 0; i < s.length; ++i) {
            while(j > 0 && s[j] != s[i])
                j = next[j-1];
            if (s[j] == s[i])
                ++j;
            next[i] = j;
        }
        return next;
    }
}
