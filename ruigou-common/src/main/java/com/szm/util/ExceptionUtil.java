package com.szm.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常处理工具
 */
public class ExceptionUtil {
    /**
     * 获取异常堆栈信息
     * @param t 抛出的异常
     * @return 异常信息
     */
    public static String getStackTrave(Throwable t){
        StringWriter sw=new StringWriter();
        PrintWriter pw=new PrintWriter(sw);
        try {
            t.printStackTrace(pw);
            return sw.toString();
        }finally {
            pw.close();
        }
    }
}
