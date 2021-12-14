package com.test;

//最后出来的是一维数组，这个数组是要用我们的既定法则去理解的
//数组中隐含的字典：i:array(i)，第[i+1]个皇后放在第[i+1]行的第[array(i)+1]列
//第[序号+1]个皇后放在第[序号+1]行的第[value+1]列，所以没有关于放到第几行的代码

public class Queen_OneDimensionalArray {
    //定义一个QUEEN_NUMBER表示共有多少个皇后
    private static final int QUEEN_NUMBER = 10;
    private static int count = 0;
    private static int judgecount = 0;
    //定义数组array，保存皇后放置位置的结果，比如arr = {0,4,7,5,2,6,1,3}
    //第[序号+1]个皇后放在第[序号+1]行的第[value+1]列
    private static int[] array = new int[QUEEN_NUMBER];

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        backStracking(0);
        //先放第1个皇后，这个0有两层含义，第1个皇后，放在第1行
        //事实上，这里只能传入0，因为必然是从第一行开始判断的，否则可以试着改一下运行试试
        long end = System.currentTimeMillis();
        System.out.println("总共耗时：" + (end - start) + "毫秒");
        System.out.println("解的数量：" + count);
        System.out.println("判断次数：" + judgecount);
    }

    //写一个方法，放置第n个皇后
    //特别注意，check是每一次递归时，进入到check中都有for(int i=0;i<max;i++)，因此会有回溯
    private static void backStracking(int row) {
        if (row == QUEEN_NUMBER) {
            //相当于此时已经在放第QUEEN_NUMBER+1个皇后了，也就是第QUEEN_NUMBER个皇后放好了
            print();
            return;
        }
        //否则就依次放置皇后，并判断是否冲突
        for (int i = 0; i < QUEEN_NUMBER; i++) {
            //先把当前这个第(row+1)个皇后，放到第(row+1)行的第1列，然后判断，如果不行，就放到第2列，以此类推
            array[row] = i;
            //此处需要利用array[n]做判断，所以需要先赋值，二维做法中在内部赋值更能体现回溯思想
            //并且当当前无解或者找到了一组可行解时，此处并没有将当前值初始化，而是直接通过赋值的形式覆盖原来的值。
            // 因为在Judge的时候，第4个值并不会影响我判断前三个的情况

            //判断当放置第n个皇后到i列时，是否冲突
            if (judge(row)) {
                //如果不冲突，就接着摆第n+1个皇后，也即开始递归
                backStracking(row + 1);
                //如果不冲突，n+1之后，行也就换了，所以在我们的算法里面不会出现两个皇后摆在同一行的情况
            }
            //如果冲突，就继续摆到第i+1列，再行判断
            //i++已经写在了循环里
            array[row] = 0;
        }
    }

    //写一个方法，查看放置第row个皇后时，检测该皇后是否和前面已经摆放的皇后冲突
    //false表示冲突，true表示不冲突
    private static boolean judge(int row) {
        judgecount++;
        //当我放置第row个皇后时，要将==前面摆放的所有的皇后==>i=0，i<row，依次做检测
        for (int i = 0; i < row; i++) {
            if (array[i] == array[row] || Math.abs(row - i) == Math.abs(array[row] - array[i])) {
                //因为array[i]的值代表放置皇后的列标，所以array[i] == array[row]表示它们在同一列，且我们的思路不会出现摆在同一行的情况
                //如果行索引之差的绝对值等于列索引之差的绝对值，即Math.abs(row-i) == Math.abs(array[row]-array[i])，则表示两个皇后是否在同一个斜线
                return false;
            }
        }

        return true;
    }

    //写一个方法，可以将皇后摆放的位置输出
    private static void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
