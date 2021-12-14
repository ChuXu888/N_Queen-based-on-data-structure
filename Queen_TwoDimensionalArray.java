package com.test;

public class Queen_TwoDimensionalArray {
    private static final int QUEEN_NUMBER = 10;
    private static int count = 0;
    private static int judgecount = 0;
    private static int[][] chessBoard = new int[QUEEN_NUMBER][QUEEN_NUMBER];

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        backStracking(0);
        long end = System.currentTimeMillis();
        System.out.println("总共耗时：" + (end - start) + "毫秒");
        System.out.println("解的数量：" + count);
        System.out.println("判断次数：" + judgecount);
    }

    public static void backStracking(int row) {
        if (row == QUEEN_NUMBER) {
            printQueen();
            return;
        }
        for (int k = 0; k < chessBoard.length; k++) {
            if (judge(row, k)) {
                chessBoard[row][k] = 1;
                backStracking(row + 1);
                chessBoard[row][k] = 0;
            }
        }
    }

    public static boolean judge(int row, int k) {
        judgecount++;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < chessBoard.length; j++) {
                if (chessBoard[i][k] == 1 || (chessBoard[i][j] == 1 && Math.abs(k - j) == Math.abs(row - i))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printQueen() {
        count++;
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                System.out.print(chessBoard[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("===================================");
    }
}
