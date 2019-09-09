package com.zdran.springboot.guava;

import com.google.common.base.Predicates;
import com.zdran.springboot.dao.AccountInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Create by ranzhendong on 2019-09-09
 *
 * @author ranzhendong@maoyan.com
 */
public class AvoidingNull {
    /**
     * 使用 empty 代替 null
     *
     * @param num
     * @return
     */
    private static Optional<Integer> getNum(int num) {
        if (num > 0) {
            return Optional.of(num);
        } else {
            //使用 empty 代替 null
            return Optional.empty();
        }
    }

    public static void testGetNum() {
        Optional<Integer> num = getNum(5);
        if (num.isPresent()) {
            System.out.println(num.get());
        }
        //也可以使用 ifPresent 方法
        num.ifPresent(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    public static void testEqual() {
        Optional<Integer> num = getNum(1111);
        //使用 equals 比较值
        num.filter(Predicates.equalTo(12)).ifPresent(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });

    }

    public static void testIn() {
        List<AccountInfo> data = new ArrayList<>();

        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setName("aaa");
        accountInfo.setBalance(123);
        data.add(accountInfo);

        AccountInfo accountInfo2 = new AccountInfo();
        accountInfo2.setName("aaa");
        accountInfo2.setBalance(123);

        System.out.println("equals：" + accountInfo.equals(accountInfo2));

        Optional<AccountInfo> optional = Optional.of(accountInfo2);
        //通过 调用对象的 equals 方法来判断是否存在
        optional.filter(Predicates.in(data)).ifPresent(new Consumer<AccountInfo>() {
            @Override
            public void accept(AccountInfo accountInfo) {
                System.out.println("1：" + accountInfo.toString());
            }
        });
    }

    public static void main(String[] args) {
        testGetNum();
        testIn();
    }
}
