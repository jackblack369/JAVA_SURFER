package com.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dongwei
 * 2018/8/7 9:32
 **/
public class TestString {
    /**
     * 判断是否含有特殊字符
     *
     * @param str
     * @return true为包含，false为不包含
     */
    public static boolean isSpecialChar(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    public static boolean isContainChinese(String str) {
        String regEx = "[\u4e00-\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }


    public static boolean isValidStr(String str) {

        if (isSpecialChar(str) || isContainChinese(str)) {
            return false;
        } else {
            return true;
        }

    }


    /**
     * 我国公民的身份证号码特点如下
     * 1.长度18位
     * 2.第1-17号只能为数字
     * 3.第18位只能是数字或者x
     * 4.第7-14位表示特有人的年月日信息
     * 请实现身份证号码合法性判断的函数，函数返回值：
     * 1.如果身份证合法返回0
     * 2.如果身份证长度不合法返回1
     * 3.如果第1-17位含有非数字的字符返回2
     * 4.如果第18位不是数字也不是x返回3
     * 5.如果身份证号的出生日期非法返回4
     *
     * @since 0.0.1
     */
    private static boolean isValidIdCard(String id) {
        String regEx = "[1-9]{2}[0-9]{4}(19|20)[0-9]{2}"
                + "((0[1-9]{1})|(1[1-2]{1}))((0[1-9]{1})|([1-2]{1}[0-9]{1}|(3[0-1]{1})))"
                + "[0-9]{3}[0-9x]{1}";
        Pattern p = Pattern.compile(regEx);

        Matcher m = p.matcher(id);
        return m.find();

//        return p.matcher(id).matches() ? 0 : 1;
    }


    private static boolean getValidIdCard(String cardid) {
        String ls_id = cardid;
        if (ls_id.length() != 18) {
            return false;
        }
        char[] l_id = ls_id.toCharArray();
        int l_jyw = 0;
        int[] wi = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};
        char[] ai = new char[]{'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
        for (int i = 0; i < 17; i++) {
            if (l_id[i] < '0' || l_id[i] > '9') {
                return false;
            }
            l_jyw += (l_id[i] - '0') * wi[i];
        }
        l_jyw = l_jyw % 11;
        if (ai[l_jyw] != l_id[17]) {
            return false;
        }
        return true;
    }


    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            return isMatch;
        }
    }


    public static void main(String[] args) {
        String testString = "1dfdfdfdsd*fsdfa";
        boolean resultSpe = isSpecialChar(testString);

        String testChn = "123434jdfhaskhfkhdslfeeee我eeeeeeeeeeeeeeeeeeeeeeeeeeeee";

        boolean resultChn = isContainChinese(testChn);

        String testValid = "ksjdflajofojwojefjej  ";

        boolean resultValid = isValidStr(testValid.trim());

//        String testId = "230231199011024913";
        String testId = "370826198812266814";
//        boolean resultIdCard = isValidIdCard(testId);
        boolean resultIdCard = getValidIdCard(testId);

        String phone = "18615261379";
        boolean resultPhone = isPhone(phone);

        System.out.println("result:" + resultPhone);
    }
}
