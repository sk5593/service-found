package com.service.found.test;

import java.util.*;

public class MathExample {
    public static void main(String[] args) {
        NumberOf1Between1AndN_Solution(11);
    }

    /**
     * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
     * n<=39
     * F(1)=1，F(2)=1, F(n)=F(n - 1)+F(n - 2)
     */
    public int Fibonacci(int n) {
        return 0;
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。
     * 求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
     * 假设有6个台阶，从第五个台阶只能跳1次到6，所以跳到5的跳法和跳到6的跳法是一样的。
     * 同理还可以从4跳2级到6。
     *
     * @param target
     * @return
     */
    public int JumpFloor(int target) {
        if (target == 1) return 1;
        if (target == 2) return 2;
        return JumpFloor(target - 1) + JumpFloor(target - 2);
    }

    /**
     * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，
     * 总共有多少种方法？
     * <p>
     * 比如n=3时，2*3的矩形块有3种覆盖方法：
     */
    public int RectCover(int target) {
        return 0;
    }

    /**
     * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
     * 每次进行一次while循环，就可以使n的二进制数的最右边的一位1，变成0
     * 比如1100，减1之后变成1011，1100&1011 = 1000，1000-1 = 0111，1000&0111 = 0000
     * 牛逼
     * 原码转补码：符号位不变，数值位按位取反后加1
     */
    public int NumberOf1(int n) {
        int count = 0;
        while (n != 0) {
            n = (n - 1) & n;
            count++;
        }
        return count;
    }

    /**
     * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
     * <p>
     * 保证base和exponent不同时为0
     */
    public double Power(double base, int exponent) {
        /**
         * 指数为正，就把底数乘指数次，
         * 指数为负，就用1/值
         */
        return 0;
    }

    /**
     * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分
     * ，所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     */
    public void reOrderArray(int[] array) {
        for (int x = 0; x < array.length; x++) {
            for (int y = x; y > 0; y--) {
                //如果array[y]是基数，而且array[y-1]是偶数的话，替换
                if (array[y] % 2 == 1 && array[y - 1] % 2 == 0) {
                    int temp = array[y];
                    array[y] = array[y + 1];
                    array[y + 1] = temp;
                }
            }
        }
    }

    /**
     * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
     * 例如，如果输入如下4 X 4矩阵：
     * 1 2 3 4
     * 5 6 7 8
     * 9 10 11 12
     * 13 14 15 16
     * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
     */
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList list = new ArrayList();
        //列数
        int l = matrix.length - 1;
        //列的初始值
        int n = 0;
        int h = matrix[0].length - 1;
        //行的初始值
        int m = 0;
        if (l == 0) {
            list.add(matrix[0][0]);
            return list;
        }
        while (l > 0) {
            for (int x = n; x <= l; x++) {
                list.add(matrix[m][x]);
            }
            m++;
            for (int x = m; x <= h; x++) {
                list.add(matrix[x][l]);
            }
            l--;
            for (int x = l; x >= n; x--) {
                list.add(matrix[h][x]);

            }
            h--;
            for (int x = h; x >= m; x--) {
                list.add(matrix[x][n]);
            }
            n++;
        }
        return list;
    }

    /**
     * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
     * 排序
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        return null;
    }

    /**
     * 统计一个数字在排序数组中出现的次数。
     * 先找到第一个数字，再找到最后一个数字，相减+1即是它的长度也是它出现的次数
     */
    public int GetNumberOfK(int [] array , int k) {
        int length = array.length-1;
        int start = 0;
        int first = getFirst(array, k, start, length);
        int last = getLast(array, k, start, length);
        if(first != -1 && last != -1){
            return last - first + 1;
        }
        return 0;
    }

    //递归写法
    public int getFirst(int[] array, int k, int start, int end) {
        if(start > end){
            return -1;
        }
        int mid = (start + end) / 2;
        if (array[mid] > k) {
            return getFirst(array, k, start, mid - 1);
        } else if (array[mid] < k) {
            return getFirst(array, k, mid + 1, end);
        } else if (mid-1>=0&&array[mid - 1] == k) {
            return getFirst(array, k, start, mid - 1);
        } else {
            //如果mid的前一个不是key，mid就是那个key
            return mid;
        }
    }

    //循环写法，二分查找
    public int getLast(int[] array, int k, int start, int end) {
        int length = array.length;
        int mid = (start + end) >> 1;
        while (start <= end) {
            if (array[mid] > k) {
                end = mid - 1;
            } else if (array[mid] < k) {
                start = mid + 1;
            }else if (mid+1<length&&array[mid+1]==k){
                start = mid + 1;
            }else{
                return mid;
            }
            mid = (start + end) / 2;
        }
        return -1;
    }

    /**
     * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
     * 排序后两个相同的数字会在一起
     */
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        Arrays.sort(array);
        List list = new ArrayList();
        for (int x = 0; x < array.length; x++) {
            list.add(array[x]);
        }
        for (int x = list.size() - 1; x > 0; x--) {
            if (list.get(x) == list.get(x - 1)) {
                list.remove(x);
                list.remove(x - 1);
            }

        }
        num1[0] = (Integer) list.get(0);
        num2[0] = (Integer) list.get(1);
    }

    public static int FindGreatestSumOfSubArray(int[] array) {
        int sum = 0;
        for (int x = 0; x < array.length; x++) {
            if (array[x] >= 0) {
                sum += array[x];
            } else {
                int temp = 0;
                for (int y = x + 1; y < array.length; y++) {
                    temp += array[y];
                }
                if (Math.sqrt(array[x]) < temp) {
                    return sum;
                } else {
                    sum += array[x];
                }
            }
        }
        return sum;
    }
    /**
     * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)
     * 中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
     * 由hashmap中键为字母，值为字母出现的次数，
     * 添加的时候看看这个字母有没有，如果有，则获取它的值并+1，如果没有，则把字母添加进去，并把值设为1。
     */
    public int FirstNotRepeatingChar(String str) {
        char key ='a' ;
        int result = -1;
        Map<Character,Integer> map = new LinkedHashMap();
        for(int x=0;x<str.length();x++){
            if(map.containsKey(str.charAt(x))){
                int time = map.get(str.charAt(x));
                map.replace(str.charAt(x),++time);
            }else{
                map.put(str.charAt(x),1);
            }
        }
        for(Map.Entry<Character,Integer> entry:map.entrySet()){
            if(entry.getValue()==1){
                key = entry.getKey();
                break;
            }
        }
        for (int x =0;x<str.length();x++){
            if(str.charAt(x)==key){
                result = x;
            }
        }
        return result;
    }
    /**
     * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。
     * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
     * 1*2,1*3,1*5,即为丑数，再把值依次*2，*3, *5
     */
    public int GetUglyNumber_Solution(int index) {
        if(index<=0)return 0;
        List<Integer> list = new ArrayList();
        list.add(1);
        int i2 = 0,i3 = 0,i5 = 0;
        while(list.size()<index){
            int m2 = list.get(i2)*2;
            int m3 = list.get(i3)*3;
            int m5 = list.get(i5)*5;
            int min = Math.min(m2, Math.min(m3, m5));
            list.add(min);
            if (min==m2)i2++;
            if (min==m3)i3++;
            if (min==m5)i5++;
        }
        return list.get(list.size()-1);
    }
    /**
     * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
     * 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
     * ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
     */
    public static int NumberOf1Between1AndN_Solution(int n) {
        int count=0;
        while(n>0){
            String str=String.valueOf(n);
            char [] chars=str.toCharArray();
            for(int i=0;i<chars.length;i++){
                if(chars[i]=='1')
                    count++;
            }
            n--;
        }
        return count;
    }

}
