package com.globo.challenge.util;

import org.springframework.util.StringUtils;

public class StringUtil {

    public StringUtil() {
    }

    public static String onlyNumbers(String str) {
        StringBuffer ret = new StringBuffer();

        try {
            for(int i = 0; i < str.length(); ++i) {
                if (Character.isDigit(str.charAt(i))) {
                    ret.append(str.charAt(i));
                }
            }
        } catch (Exception var3) {
        }

        return ret.toString();
    }

    public static String removeSpecialCharacters(String word) {
        return StringUtils.trimWhitespace(word).replaceAll("\\\"", "");
    }

    public static String fillWithLeadingZeros(String str, int tamanho) {
        StringBuffer ret = new StringBuffer("");
        if (str != null && !str.equals("null")) {
            for(int i = str.length(); i < tamanho; ++i) {
                ret.append("0");
            }

            ret.append(str);
        }

        return ret.toString();
    }

}
