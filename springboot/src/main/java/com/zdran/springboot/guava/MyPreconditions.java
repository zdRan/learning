package com.zdran.springboot.guava;

import com.google.common.base.Preconditions;
import com.zdran.springboot.dao.AccountInfo;

/**
 * 前置条件
 * Create by ranzhendong on 2019-09-09
 *
 * @author ranzhendong@maoyan.com
 */
public class MyPreconditions {
    /**
     * 检查参数是否为 true
     * @param attr
     */
    private static void testAttrs(boolean attr){
        Preconditions.checkArgument(attr,"参数错误");
    }

    /**
     * 检查参数是否为 null
     * @param accountInfo
     */
    private static void testAttr2(AccountInfo accountInfo) {
        Preconditions.checkNotNull(accountInfo, "参数不能为 null");
    }

    /**
     * 检查索引是否越界
     * @param index
     * @param size
     */
    private static void testAttr3(int index, int size) {
        Preconditions.checkElementIndex(index, size);
    }

    /**
     * 检查区间是否越界
     * @param start
     * @param end
     * @param size
     */
    private static void testAttr4(int start,int end, int size) {
        Preconditions.checkPositionIndexes(start,end, size);
    }
    public static void main(String[] args) {
        //testAttrs(Boolean.FALSE);
        //testAttr2(null);
        //testAttr3(3, 2);
        testAttr4(2, 4, 3);
    }
}
