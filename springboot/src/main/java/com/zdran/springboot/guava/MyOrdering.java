package com.zdran.springboot.guava;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;
import com.zdran.springboot.dao.AccountInfo;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 流畅风格比较器
 * Create by ranzhendong on 2019-09-09
 *
 * @author ranzhendong@maoyan.com
 */
public class MyOrdering {
    /**
     * 插入顺序的倒序
     *
     * @param list
     */
    private static void arbitrary(List<String> list) {
        list.sort(Ordering.arbitrary());
    }

    /**
     * 默认顺序
     *
     * @param list
     */
    private static void natural(List<String> list) {
        //默认排序，针对可排序类型
        list.sort(Ordering.natural());
        //使用 String 的字典顺序
        list.sort(Ordering.usingToString());
    }

    /**
     * 自定义比较器
     *
     * @param list
     */
    private static void lengthOrdering(List<String> list) {
        Ordering<String> lengthOrdering = new Ordering<String>() {
            @Override
            public int compare(@Nullable String left, @Nullable String right) {
                return left.length() - right.length();
            }
        };
        list.sort(lengthOrdering);
        //倒序
        list.sort(lengthOrdering.reverse());

    }

    /**
     * onResultOf 方法
     *
     * @param list
     */
    private static void lengthOrdering2(List<AccountInfo> list) {
        Ordering<AccountInfo> lengthOrdering = Ordering.natural().nullsFirst().onResultOf(new Function<AccountInfo, Integer>() {
            @Nullable
            @Override
            public Integer apply(@Nullable AccountInfo accountInfo) {
                return accountInfo.getBalance();
            }
        });
        list.sort(lengthOrdering);
        //倒序
        list.sort(lengthOrdering.reverse());
    }

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("cccc");
        list1.add("bb");
        list1.add("aaa");
        //arbitrary(list1);
        //natural(list1);
        lengthOrdering(list1);
        System.out.println(Arrays.deepToString(list1.toArray()));


    }
}
