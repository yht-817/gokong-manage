package cn.gokong.www.gokongmain;

import java.util.List;
import java.util.Random;

/**
 * 说明:
 *
 * @author ikook
 * @CreateDate 2018/11/15 17:32
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public class Test {

    public static <T> List<T> shuffle1(List<T> list) {
        int size = list.size();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            // 获取随机位置
            int randomPos = random.nextInt(size);

            // 当前元素与随机元素交换
            T temp = list.get(i);
            list.set(i, list.get(randomPos));
            list.set(randomPos, temp);
        }
        return list;
    }

    public static void main(String[] args) {
        String userno = "yh154883565759920272";
        System.out.println(userno.substring(0, 2));
    }


}
