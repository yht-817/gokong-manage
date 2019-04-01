package cn.gokong.www.gokongmain;

import cn.ikeek.www.emoji.EmojiUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 说明:
 *
 * @author ikook
 * @CreateDate 2018/9/10 14:20
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public class Main {

    public static void main(String[] args) {
        /*ExcelReader reader = ExcelUtil.getReader("D:\\work\\county.xlsx");
        List<List<Object>> read = reader.read();
        System.out.println(read);*/

        // 差集
        /*List<String> listA_02 = new ArrayList<String>(){{
            add("A");
            add("B");
            add("C");
        }};
        List<String> listB_02 = new ArrayList<String>(){{
            add("A");
            add("B");
            add("D");
            add("E");
        }};
        listA_02.retainAll(listB_02);
        System.out.println(listA_02); // 结果:[A]
        System.out.println(listB_02); // 结果:[B, C]*/

        System.out.println(EmojiUtil.emojiToStr("\uD83D\uDE00哈哈哈\uD83E\uDD63"));
        System.out.println(EmojiUtil.strToEmoji(EmojiUtil.emojiToStr("\uD83D\uDE00哈哈哈\uD83E\uDD63")));

        //System.out.println(resolveToByteFromEmoji("\uD83D\uDE00"));
        //System.out.println(resolveToEmojiFromByte(resolveToByteFromEmoji("\uD83D\uDE00")));
    }

    /**
     * 将str中的emoji表情转为byte数组
     *
     * @param str
     * @return
     */
    public static String resolveToByteFromEmoji(String str) {
        Pattern pattern = Pattern
                .compile("[^(\u2E80-\u9FFF\\w\\s`~!@#\\$%\\^&\\*\\(\\)_+-？（）——=\\[\\]{}\\|;。，、《》”：；“！……’:'\"<,>\\.?/\\\\*)]");
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb2 = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb2, resolveToByte(matcher.group(0)));
        }
        matcher.appendTail(sb2);
        return sb2.toString();
    }

    /**
     * 将str中的byte数组类型的emoji表情转为正常显示的emoji表情
     *
     * @param str
     * @return
     */
    public static String resolveToEmojiFromByte(String str) {
        Pattern pattern2 = Pattern.compile("<:([[-]\\d*[,]]+):>");
        Matcher matcher2 = pattern2.matcher(str);
        StringBuffer sb3 = new StringBuffer();
        while (matcher2.find()) {
            matcher2.appendReplacement(sb3, resolveToEmoji(matcher2.group(0)));
        }
        matcher2.appendTail(sb3);
        return sb3.toString();
    }

    private static String resolveToByte(String str) {
        byte[] b = str.getBytes();
        StringBuffer sb = new StringBuffer();
        sb.append("<:");
        for (int i = 0; i < b.length; i++) {
            if (i < b.length - 1) {
                sb.append(Byte.valueOf(b[i]).toString() + ",");
            } else {
                sb.append(Byte.valueOf(b[i]).toString());
            }
        }
        sb.append(":>");
        return sb.toString();
    }

    private static String resolveToEmoji(String str) {
        str = str.replaceAll("<:", "").replaceAll(":>", "");
        String[] s = str.split(",");
        byte[] b = new byte[s.length];
        for (int i = 0; i < s.length; i++) {
            b[i] = Byte.valueOf(s[i]);
        }
        return new String(b);
    }
}
