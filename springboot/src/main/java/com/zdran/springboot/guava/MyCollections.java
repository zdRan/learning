package com.zdran.springboot.guava;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import com.zdran.springboot.dao.AccountInfo;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.*;

/**
 * Create by ranzhendong on 2019-09-09
 *
 * @author ranzhendong@maoyan.com
 */
public class MyCollections {
    public static void testImmutable() {
        ImmutableSet<String> immutableSet = ImmutableSet.<String>builder().add("c")
                .add("b").build();
        for (String s : immutableSet) {
            System.out.print(s + ",");
        }
        System.out.println();
    }

    private static void testSort() {
        ImmutableSortedSet<String> immutableSortedSet = ImmutableSortedSet.of("c", "a", "b");
        for (String s : immutableSortedSet) {
            System.out.print(s + ",");
        }
        System.out.println();

    }

    private static void testMultiset() {
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.setCount("b", 3);

        System.out.println(multiset.contains("a"));
        System.out.println(multiset.count("a"));
        System.out.println(multiset.remove("b"));
        System.out.println(multiset.count("b"));
        System.out.println(multiset.count("a"));
        System.out.println(multiset.size());
    }

    private static void testSortMultiset() {
        SortedMultiset<Integer> multiset = TreeMultiset.create();
        multiset.addAll(Lists.newArrayList(3, 2, 5, 6, 9, 2, 5, 6, 8));
        for (int num : multiset) {
            System.out.print(num + ",");
        }
    }

    private static void testMultimap() {
        Multimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("a", 1);
        multimap.put("a", 2);
        multimap.put("a", 3);
        multimap.put("a", 4);
        System.out.println(multimap.containsKey("a"));
        System.out.println(multimap.containsEntry("a", 1));
        System.out.println(multimap.containsEntry("a", 0));
        System.out.println(multimap.remove("a", 3));
        System.out.println(Arrays.deepToString(multimap.get("a").toArray()));

    }

    private static void testBiMap() {
        BiMap<String, Integer> biMap = HashBiMap.create();
        biMap.put("a", 1);
        biMap.put("b", 2);
        System.out.println(biMap.inverse().get(2));
        biMap.forcePut("c", 2);
        System.out.println(biMap.inverse().get(2));

    }

    private static void testUtil1() {
        List<AccountInfo> accountInfoList = Lists.newArrayList();
        List<AccountInfo> accountInfoList2 = Lists.newArrayListWithCapacity(3);
        List<AccountInfo> accountInfoList3 = Lists.newArrayListWithExpectedSize(3);
        List<AccountInfo> accountInfoList4 = Lists.newArrayList(new AccountInfo());

        Iterable<Integer> concatenated = Iterables.concat(Ints.asList(1, 2, 3),
                Ints.asList(3, 4, 5));

        System.out.println(Iterables.frequency(concatenated, 3));
        System.out.println(Iterables.getFirst(concatenated, 0));

    }

    private static void testSets() {
        Set<Integer> set = Sets.union(Sets.newHashSet(1, 2, 3), Sets.newHashSet(3, 4, 5));
        System.out.println(Arrays.deepToString(set.toArray()));

        Set<Integer> set2 = Sets.intersection(Sets.newHashSet(1, 2, 3), Sets.newHashSet(3, 4, 5));
        System.out.println(Arrays.deepToString(set2.toArray()));

        Set<Integer> set3 = Sets.difference(Sets.newHashSet(1, 2, 3), Sets.newHashSet(1, 2));
        System.out.println(Arrays.deepToString(set3.toArray()));
    }

    private static void testMaps() {
        Map<String, Integer> hashMap = Maps.newHashMap();
        hashMap.put("a", 1);
        hashMap.put("bb", 2);
        hashMap.put("ccc", 3);

        Map<String, Integer> hashMap1 = Maps.filterKeys(hashMap, new Predicate<String>() {
            @Override
            public boolean apply(@Nullable String input) {
                return input.length() > 2;
            }
        });

        System.out.println(Arrays.deepToString(hashMap1.entrySet().toArray()));

        Map<String, Integer> hashMap2 = Maps.filterEntries(hashMap, new Predicate<Map.Entry<String, Integer>>() {
            @Override
            public boolean apply(Map.@Nullable Entry<String, Integer> input) {
                return input.getValue() > 2;
            }
        });
        System.out.println(Arrays.deepToString(hashMap2.entrySet().toArray()));


        Map<Integer, String> hashMap3 = Maps.uniqueIndex(Lists.newArrayList("sss", "ss", "ssss"),
                new Function<String, Integer>() {
                    @Nullable
                    @Override
                    public Integer apply(@Nullable String input) {
                        return input.length();
                    }
                });
        System.out.println(Arrays.deepToString(hashMap3.entrySet().toArray()));

        Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> right = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        MapDifference<String, Integer> diff = Maps.difference(left, right);

        diff.entriesInCommon(); // {"b" => 2}
        diff.entriesInCommon(); // {"b" => 2}
        diff.entriesOnlyOnLeft(); // {"a" => 1}
        diff.entriesOnlyOnRight(); // {"d" => 5}MapDifference
    }

    private static void testForwarding() {
        ForwardingList<String> forwardingList = new ForwardingList<String>() {
            final List<String> delegate = new ArrayList<>(); // backing list

            @Override
            protected List<String> delegate() {
                return delegate;
            }

            @Override
            public void add(int index, String element) {
                System.out.println("add:" + element);
                super.add(index, element);
            }

            @Override
            public String get(int index) {
                System.out.println("get:" + index);
                return super.get(index);
            }
        };

        forwardingList.add(0, "aaa");
        System.out.println(forwardingList.get(0));
    }

    public static void main(String[] args) {
//        testImmutable();
//        testSort();
//        testMultiset();
//        testSortMultiset();
//        testMultimap();
//        testBiMap();
//        testSets();
//        testMaps();
//        testForwarding();
        test();
    }

    private static void test() {
        Map<String, Object> result = new HashMap<>();

        List<Integer> integers = new ArrayList<>();

        integers.add(8);
        integers.add(23);
        integers.add(85);

        result.put("attrIds", integers);

        System.out.println(Joiner.on(",").join((Collection) (result.get("attrIds"))));

    }
}
