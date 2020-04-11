package com.service.found.test;

public class StringExample {
    public static void main(String[] args) {
        System.out.println("%20".length());
    }

    /**
     * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
     * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     *
     * @param str
     * @return
     */
    public String replaceSpace(StringBuffer str) {
        StringBuffer newSb = new StringBuffer();
        for (int x = 0; x < str.length(); x++) {
            if (str.charAt(x) == ' ') {
                newSb.append("%20");
            } else {
                newSb.append(str.charAt(x));
            }
        }
        return newSb.toString();
    }
}
