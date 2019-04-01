package cn.gokong.www.base.util;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * 说明:数据库工具类
 *
 * @author Mick
 * CreateDate 2018/6/9/009 21:53
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public class DataBaseTool {

    /**
     * 创建数据库ID (32位)
     *
     * @return
     */
    public static String createId() {
        return DigestUtil.md5Hex(UUID.randomUUID().toString());
    }

    /**
     * 创建数据库编码（参数+14位时间+4位随机）
     *
     * @param head 编码头
     * @return
     */
    public static String createNo(String head) {
        return head + DateUtil.currentSeconds() + RandomUtil.randomNumbers(8);
    }

    /**
     * 创建数据库时间
     *
     * @return
     */
    public static Date createDate() {
        return new Date();
    }

    /**
     * 创建用户昵称
     *
     * @return
     */
    public static String createNickName() {
        return "GoKong" + RandomUtil.randomNumbers(6);
    }

    // 生成订单
    public static String createNum(int length) {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 14 代表长度为14
        // d 代表参数为正数型
        length -= 7;//生成随机数长度
        String date = new SimpleDateFormat("yyMMdd").format(new Date());
        return date + machineId + String.format("%0" + length + "d", hashCodeV);
    }

    public static String getMonday() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (dayWeek == 1) {
            dayWeek = 8;
        }
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.set(Calendar.AM_PM, 0);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date mondayDate = cal.getTime();
        String weekBegin = sdf.format(mondayDate);
        return weekBegin;
    }

    public static int calLastedTime(Date startDate) {
        long a = new Date().getTime();
        long b = startDate.getTime();
        int c = (int) ((a - b) / 1000);
        return c;
    }


    // 获取现在到凌晨的总秒数
    public static int getTimeSum() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (int) ((cal.getTimeInMillis() - System.currentTimeMillis()) / 1000);
    }

    /**
     * 获取当前天凌晨时间
     */
    public static String getWeeHours() {
        long now = System.currentTimeMillis() / 1000l;
        long daySecond = 60 * 60 * 24;
        long dayTime = now - (now + 8 * 3600) % daySecond;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(dayTime * 1000);
        return simpleDateFormat.format(date);
    }

    /**
     * 根据下单时间获取两个后的小时间，然后求两个小时的与现在这个时间是否大于，不大于然后求当前的值
     */
    public static int twoHours(Date date) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR,
                    calendar.get(Calendar.HOUR) + 3);// 让日期加1
            System.out.println(calendar.get(Calendar.DATE));// 加1之后的日期Top
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startDate = df.parse(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS").format(calendar.getTime()));
            long a = new Date().getTime();
            long b = startDate.getTime();
            int c = (int) ((b - a) / 1000);
            return c;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取当前时间前一个月的时间
     */
    public static String getMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String mon = format.format(m);
        return mon;
    }

    /**
     * 获取当前时间前两个月的时间
     */
    public static String getTwoMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -2);
        Date m = c.getTime();
        String mon = format.format(m);
        return mon;
    }

    public static String firstM() {
        //规定返回日期格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date theDate = calendar.getTime();
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        //设置为第一天
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first = sf.format(gcLast.getTime());
        //打印本月第一天
        return day_first;
    }

    // 获取当前时间前几天的时间

    public static String getfewDaysTime(int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -day);
        c.set(Calendar.HOUR, -12);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.SECOND, 0);
        Date monday = c.getTime();
        String preMonday = sdf.format(monday);
        return preMonday;
    }
}
