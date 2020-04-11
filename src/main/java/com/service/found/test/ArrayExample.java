package com.service.found.test;


import java.util.*;

public class ArrayExample {
    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[20];
        for (int i = 0; i < 20; i++) {
            arr[i] = random.nextInt(10);
            System.out.print(arr[i] + ",");
        }
        System.out.println();
        //输出arr数组中重复元素，重复数量
        Arrays.sort(arr);
        int sum = 1;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < 20 - 1; i++) {
            if (arr[i] == arr[i+1]){
                sum++;
            }else {
                if (sum>1){
                    map.put(arr[i-1],sum);
                }
                sum = 1;
            }
        }
        if (sum > 1){
            map.put(arr[arr.length-1],sum);
        }
        Set<Integer> keys = map.keySet();
        Object[] keysArr =  keys.toArray();
        for (int x = 0;x<keys.size();x++){
            System.out.println(keysArr[x]+"重复"+map.get(keysArr[x])+"次");
        }
    }

    public static String method(String a, String b, String c) {
        String result = "";
        String[] split = a.split(b);
        for (int x = 0; x < split.length; x++) {
            if (x != split.length - 1) {
                result += split[x] + c;
            } else {
                result += split[x];
            }
        }
        return result;
    }

    public static int reverse(int x) {
        int temp = 0;
        if (x > Integer.MAX_VALUE / 10 || x <= -2147483648) {
            temp = 0;
        } else if (x < 0) {
            temp = Math.abs(x);
        } else {
            temp = x;
        }

        String s = String.valueOf(temp);
        char[] chars = s.toCharArray();
        int result = 0;
        for (int m = 0; m < chars.length; m++) {
            int n = chars[m] - '0';
            result += n * Math.pow(10, m);
        }
        if (x < 0) return -result;
        return result;

    }

    /**
     * 在一个二维数组中（每个一维数组的长度相同），
     * 每一行都按照从左到右递增的顺序排序，
     * 每一列都按照从上到下递增的顺序排序。
     * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     */
    public boolean Find(int target, int[][] array) {
        /**
         * 数组左下角的数字，如果target比他大，则在他右边，如果比他小，则在他上面
         */
        //行的长度
        int h = 0;
        //列的长度
        int l = array[0].length;
        for (int x = l - 1; x >= 0; x--) {
            for (int y = 0; y < array.length; y++) {
                //说明在他右边
                if (target > array[y][x]) {
                    continue;
                } else if (target < array[y][x]) {
                    break;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
     * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     *
     * @param array
     * @return
     */
    public int minNumberInRotateArray(int[] array) {
        int low = 0;
        int high = array.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            //1 3 5 1 2
            if (array[mid] > array[high]) {
                low = mid + 1;

            } else if (array[mid] == array[high]) {
                high = high - 1;
            } else {
                high = mid - 1;
            }
        }
        return array[low];
    }

    /**
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
     * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
     * 排序之后，如果存在超过一半的数，肯定是中间那个数
     */
    public int MoreThanHalfNum_Solution(int[] array) {
        Arrays.sort(array);
        int halfLength = array.length / 2;
        int result = array[halfLength];
        int sum = 0;
        for (int x = 0; x < array.length; x++) {
            if (array[x] == result) sum++;
        }
        if (sum <= halfLength) return 0;
        return result;
    }

    /**
     * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323
     */
    public String PrintMinNumber(int[] numbers) {
        ArrayList<Integer> list = new ArrayList();
        for (int x = 0; x < numbers.length; x++) {
            list.add(numbers[x]);
        }
        String s = "";

        //实现comparator接口，重写compare方法
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                String s1 = o1 + "" + o2;
                String s2 = o2 + "" + o1;
                //字符串的compareTo方法根据字典顺序比较大小
                return s1.compareTo(s2);
            }
        });
        for (int j : list) {
            s += j;
        }
        return s;
    }
}
