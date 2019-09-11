package com.zdran.springboot.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

/**
 * Create by ranzhendong on 2019-09-11
 *
 * @author ranzhendong@maoyan.com
 */
public class MyStrings {
    private static void testJoiner() {
        //使用 '-' 链接，遇到 null 值用 '#' 代替
        Joiner joiner = Joiner.on("-").useForNull("#");
        System.out.println(joiner.join("aaa", "bbb", null, "", "ccc"));
        //忽略空值
        joiner = Joiner.on("-").skipNulls();
        //将拼接好的 String append 到 StringBuilder 之后
        StringBuilder sb = Joiner.on("-").appendTo(new StringBuilder("sss"), "aaa", "bbb");
        System.out.println(sb.toString());
    }

    private static void testSplitter() {
        //使用 ; 分隔，并且忽略结果集中的空字符串，移除结果中的开头和结尾的空白字符
        Splitter splitter = Splitter.on(';').omitEmptyStrings().trimResults();

        Iterable<String> arr = splitter.split("a;aa ;  a ;;;");
        for (String str : arr) {
            System.out.print(str + ",");
        }
        System.out.println();
        //按照固定长度拆分
        arr = Splitter.fixedLength(3).split("aaabbbcccdd");
        for (String str : arr) {
            System.out.print(str + ",");
        }
        System.out.println();
    }

    private static void testCharMatcher() {
        //去掉控制字符（回车、换行、tab等）
        String noControl = CharMatcher.javaIsoControl().removeFrom("aa\tbb\ncc\\d\\.");
        System.out.println(noControl);

        String theDigits = CharMatcher.inRange('0', '9').retainFrom("1a2b3c4d");
        System.out.println(theDigits);

        //去除两端的空格，并把中间的连续空格替换成 '-'
        String spaced = CharMatcher.whitespace().trimAndCollapseFrom("aaaa   bbb cc  ", '-');
        System.out.println(spaced);

        //用*号替换所有数字
        String noDigits = CharMatcher.inRange('0', '9').replaceFrom("1a2b3c4d", "*");
        System.out.println(noDigits);

        String lowerAndDigit = CharMatcher.inRange('0', '9').or(CharMatcher.javaLowerCase()).retainFrom("1Aa2Bb3Cc");
        System.out.println(lowerAndDigit);

    }

    public static void main(String[] args) {
//        testJoiner();
//        testSplitter();
        testCharMatcher();
    }


}
