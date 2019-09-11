package com.zdran.springboot.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
import com.zdran.springboot.dao.AccountInfo;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Create by ranzhendong on 2019-09-11
 *
 * @author ranzhendong@maoyan.com
 */
public class Caches {

    private static LoadingCache<String, AccountInfo> loadingCache = CacheBuilder.newBuilder()
            //最大缓存数量
            .maximumSize(10).build(new CacheLoader<String, AccountInfo>() {
                @Override
                public AccountInfo load(String key) throws Exception {
                    System.out.println("load 方法执行：" + key);
                    AccountInfo accountInfo = new AccountInfo();
                    accountInfo.setName(key);
                    return accountInfo;
                }
            });

    private static void testCaches() throws ExecutionException {
        //如果缓存中没有该值，就会添加新值
        loadingCache.get("aaa").setBalance(123);
        System.out.println(loadingCache.get("aaa"));
        //如果没有值，就会执行 call 方法，创建对应的值，并返回
        AccountInfo accountInfo = loadingCache.get("bbb", new Callable<AccountInfo>() {
            @Override
            public AccountInfo call() throws Exception {
                AccountInfo accountInfo = new AccountInfo();
                accountInfo.setName("bbb");
                accountInfo.setPwd("call-add");
                return accountInfo;
            }
        });
        System.out.println(accountInfo.toString());

        accountInfo.setPwd("put-add");
        loadingCache.put("ccc", accountInfo);
        System.out.println(loadingCache.get("ccc"));
        System.out.println(Arrays.deepToString(loadingCache.asMap().entrySet().toArray()));
    }

    private static void testEviction() throws ExecutionException, InterruptedException {
        LoadingCache<String, AccountInfo> cache = CacheBuilder.newBuilder()
                //最大缓存数量
                //.maximumSize(10)
                //最大权重
                .maximumWeight(10L)
                .weigher(new Weigher<String, AccountInfo>() {
                    @Override
                    public int weigh(String key, AccountInfo value) {
                        return value.getBalance();
                    }
                })
                .expireAfterAccess(2, TimeUnit.SECONDS)
                .weakKeys()
                .weakValues()
                .build(new CacheLoader<String, AccountInfo>() {
                    @Override
                    public AccountInfo load(String key) throws Exception {
                        AccountInfo accountInfo = new AccountInfo();
                        accountInfo.setName(key);
                        accountInfo.setBalance(99);
                        return accountInfo;
                    }
                });
        for (int i = 0; i < 12; i++) {
            AccountInfo accountInfo = new AccountInfo();
            accountInfo.setName("aaa" + i);
            accountInfo.setBalance(i);
            cache.put(accountInfo.getName(), accountInfo);
        }
        cache.invalidate("aaa10");
        System.out.println(cache.asMap().size());
        System.out.println(Arrays.deepToString(cache.asMap().entrySet().toArray()));
        Thread.sleep(3000);
        System.out.println(Arrays.deepToString(cache.asMap().entrySet().toArray()));
        System.out.println(cache.asMap().size());

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        testCaches();
        testEviction();
    }
}
