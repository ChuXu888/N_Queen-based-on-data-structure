package com.N_Queen;

import java.util.Date;
import java.util.Stack;

/**
 * 使用数据结构“栈”，模拟递归函数
 * 实现非递归方案的回溯算法
 *
 * @author newflydd@189.cn
 * Time: 2015年12月31日 下午6:13:05
 */
public class Queen_Stack {
    private static final short N = 4;

    public static void main(String[] args) {
        Date begin = new Date();

        long count = 0;
        /**
         *  初始化栈和棋盘，并向栈中压入第一张初始化的棋盘
         */
        Stack<Chess> stack = new Stack<Chess>();
        short[] chessData = new short[N];
        for (short i = 1; i < N; i++) {
            chessData[i] = -1;        //初始化棋盘，所有行没有皇后，赋值-1
        }
        Chess initChess = new Chess(chessData);
        stack.push(initChess);

        //对栈进行操作，直到栈为空，程序计算完毕
        EMPTY:
        while (!stack.isEmpty()) {
            /**
             * 访问出口处的棋盘，判断是否访问过
             * 如果没有访问过，访问标志改为true，构建下层数据
             * 如果访问过，尝试对此棋盘col++寻找此行的合法解
             * 寻找直至溢出边界，pop掉，在寻找过程中如果发现合法解：
             * 修改col，访问量状态恢复到false，跳出isEmpty循环去访问他
             */

            Chess chess = stack.peek();

            if (chess.visited) {
                while (chess.moveCol()) {
                    if (isSafety(chess)) {
                        chess.visited = false;
                        continue EMPTY;
                    }
                }
                stack.pop();
            } else {
                chess.visited = true;
                /**
                 * 构建下层数据：
                 * 构建栈顶元素的克隆，访问状态设为false
                 * row下移一层，如果溢出边界丢弃，这种情况不应该发生
                 * col:0->N寻找第一个合法解，如果row达到边界count+1，否则push进栈
                 */
                Chess chessTemp = chess.clone();
                if (chessTemp.moveRow()) {
                    while (chessTemp.moveCol()) {
                        if (isSafety(chessTemp)) {
                            if (chessTemp.currentRow == N - 1) {
                                count++;
                                continue;
                            } else {
                                stack.push(chessTemp);
                                continue EMPTY;
                            }
                        }
                    }
                }

            }
        }
        Date end = new Date();
        System.out.println("解决 " + N + "皇后问题，用时：" + String.valueOf(end.getTime() - begin.getTime()) + "毫秒，计算结果：" + count);
    }

    private static boolean isSafety(Chess chess) {
        // 判断中上、左上、右上是否安全
        short step = 1;
        for (short i = (short) (chess.currentRow - 1); i >= 0; i--) {
            if (chess.chess[i] == chess.currentCol) // 中上
                return false;
            if (chess.chess[i] == chess.currentCol - step) // 左上
                return false;
            if (chess.chess[i] == chess.currentCol + step) // 右上
                return false;

            step++;
        }

        return true;
    }
}

class Chess implements Cloneable {
    public short[] chess;        //棋盘数据
    public short currentRow = 0;    //当前行
    public short currentCol = 0;        //当前列
    public boolean visited = false;    //是否访问过

    public Chess(short[] chess) {
        this.chess = chess;
    }

    public boolean moveCol() {
        this.currentCol++;
        if (this.currentCol >= chess.length)
            return false;
        else {
            this.chess[currentRow] = currentCol;
            return true;
        }
    }

    public boolean moveRow() {
        this.currentRow++;
        if (this.currentRow >= chess.length)
            return false;
        else
            return true;
    }

    public Chess clone() {
        short[] chessData = this.chess.clone();
        Chess chess = new Chess(chessData);
        chess.currentCol = -1;
        chess.currentRow = this.currentRow;
        chess.visited = false;
        return chess;
    }
}
