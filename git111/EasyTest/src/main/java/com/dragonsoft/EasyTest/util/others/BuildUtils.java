package com.dragonsoft.EasyTest.util.others;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.Locale;
import java.util.Map;

public class BuildUtils {
    /**
     * 打印心的形状
     */
    public static String buildHeart() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0, k = 0; i < 14; i++) {
            if (i < 3) {
                for (int j = 0; j < 5 - 2 * i; j++) {
//                    System.out.print(" ");
                    sb.append(" ");
                }
                if (i == 2) {
                    for (int j = 0; j < 6 + 4 * i - 1; j++) {
//                        System.out.print("*");
                        sb.append("*");
                    }
                    for (int j = 0; j < 7 - 4 * i + 2; j++) {
//                        System.out.print(" ");
                        sb.append(" ");
                    }
                    for (int j = 0; j < 6 + 4 * i - 1; j++) {
//                        System.out.print("*");
                        sb.append("*");
                    }
                } else {
                    for (int j = 0; j < 6 + 4 * i; j++) {
//                        System.out.print("*");
                        sb.append("*");
                    }
                    for (int j = 0; j < 7 - 4 * i; j++) {
//                        System.out.print(" ");
                        sb.append(" ");
                    }
                    for (int j = 0; j < 6 + 4 * i; j++) {
//                        System.out.print("*");
                        sb.append("*");
                    }
                }
            } else if (i < 6) {
                for (int j = 0; j < 29; j++) {
//                    System.out.print("*");
                    sb.append("*");
                }
            } else {
                if (i == 13) {
                    for (int j = 0; j < 2 * (i - 6); j++) {
//                        System.out.print(" ");
                        sb.append(" ");
                    }
//                    System.out.print("*");
                    sb.append("*");
                } else {
                    for (int j = 0; j < 2 * (i - 6) + 1; j++) {
//                        System.out.print(" ");
                        sb.append(" ");
                    }
                    for (int j = 1; j < 28 - 4 * k; j++) {
//                        System.out.print("*");
                        sb.append("*");
                    }
                    k++;
                }
            }
//            System.out.println();
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void getIpConfig() {
        try {
            Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
                System.out.println(ni.getName());
                ip = (InetAddress) ni.getInetAddresses().nextElement();
                if (!ip.isSiteLocalAddress()
                        && !ip.isLoopbackAddress()
                        && ip.getHostAddress().indexOf(": ") == -1) {
                    System.out.println("本机的ip= " + ip.getHostAddress());
                    break;
                } else {
                    ip = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getConfig() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            //ni.getInetAddresses().nextElement().getAddress();
            byte[] mac = ni.getHardwareAddress();
            String sIP = address.getHostAddress();
            String sMAC = "";
            Formatter formatter = new Formatter();
            for (int i = 0; i < mac.length; i++) {
                sMAC = formatter.format(Locale.getDefault(), "%02X%s", mac[i],
                        (i < mac.length - 1) ? "-" : "").toString();

            }
            System.out.println("IP：" + sIP);
            System.out.println("MAC：" + sMAC);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        System.out.println(buildHeart());
        getIpConfig();
    }
}
