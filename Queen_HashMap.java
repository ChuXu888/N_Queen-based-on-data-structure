package com.test;

import java.util.HashMap;

public class Queen_HashMap {
    private static final int QUEEN_NUMBER = 10;
    private static int count = 0;
    private static int judgecount = 0;
    private static HashMap<Integer, Integer> hashMap = new HashMap<>();

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        backStracking(1);
        long end = System.currentTimeMillis();
        System.out.println("总共耗时：" + (end - start) + "毫秒");
        System.out.println("解的数量：" + count);
        System.out.println("判断次数：" + judgecount);
    }

    private static void backStracking(int key) {
        if (key > QUEEN_NUMBER) {
            print();
            return;
        }

        for (int value = 1; value <= QUEEN_NUMBER; value++) {
            hashMap.put(key, value);
            if (judge(key)) {
                backStracking(key + 1);
            }
            hashMap.replace(key, value, 0);
        }
    }

    private static boolean judge(int key) {
        judgecount++;
        for (int i = 1; i < key; i++) {
            if (hashMap.get(i) == hashMap.get(key) || Math.abs(key - i) == Math.abs(hashMap.get(key) - hashMap.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static void print() {
        count++;
        for (Integer i : hashMap.keySet()) {
            System.out.print("key:" + i + " value:" + hashMap.get(i) + "\t");
        }
        System.out.println();
    }
}







