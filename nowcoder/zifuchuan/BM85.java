package nowcoder.zifuchuan;

import java.util.regex.Pattern;

//验证IP地址
public class BM85 {
    //Todo：非常巧妙的方法
    public String solve(String IP) {
        return validIPv4(IP) ? "IPv4" : (validIPv6(IP) ? "IPv6" : "Neither");
    }

    private boolean validIPv4(String IP) {
        String[] strs = IP.split("\\.", -1);
        if (strs.length != 4) {
            return false;
        }

        for (String str : strs) {
            if (str.length() > 1 && str.startsWith("0")) {
                return false;
            }
            try {
                int val = Integer.parseInt(str);
                if (!(val >= 0 && val <= 255)) {
                    return false;
                }
            } catch (NumberFormatException numberFormatException) {
                return false;
            }
        }
        return true;
    }

    private boolean validIPv6(String IP) {
        String[] strs = IP.split(":", -1);
        if (strs.length != 8) {
            return false;
        }

        for (String str : strs) {
            if (str.length() > 4 || str.length() == 0) {
                return false;
            }
            try {
                //只要能成功地转化16进制数为十进制数即可视为成功
                int val = Integer.parseInt(str, 16);
            } catch (NumberFormatException numberFormatException) {
                return false;
            }
        }
        return true;
    }

    //正则表达式
    String solve2(String IP) {
        //正则表达式限制0-255 且没有前缀0 四组齐全
        String ipv4="(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
        Pattern ipv4_pattern = Pattern.compile(ipv4);
        //正则表达式限制出现8组，0-9a-fA-F的数，个数必须是1-4个
        String ipv6="([0-9a-fA-F]{1,4}\\:){7}[0-9a-fA-F]{1,4}";
        Pattern ipv6_pattern = Pattern.compile(ipv6);
        //调用正则匹配函数
        if (ipv4_pattern.matcher(IP).matches())
            return "IPv4";
        else if (ipv6_pattern.matcher(IP).matches())
            return "IPv6";
        else return "Neither";
    }
}
