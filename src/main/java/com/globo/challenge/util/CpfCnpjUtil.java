package com.globo.challenge.util;

public class CpfCnpjUtil {
    private static final int[] weightSsn = new int[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] weightTin = new int[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    public CpfCnpjUtil() {
    }

    private static int calculate(final String str, final int[] weight) {
        int sum = 0;

        for(int i = str.length() - 1; i >= 0; --i) {
            int digit = Integer.parseInt(str.substring(i, i + 1));
            sum += digit * weight[weight.length - str.length() + i];
        }

        sum = 11 - sum % 11;
        return sum > 9 ? 0 : sum;
    }

    public static boolean isValidCPF(final String ssn) {
        if (ssn != null && !ssn.trim().isEmpty()) {
            String cpf = StringUtil.onlyNumbers(formatCpf(ssn));
            if (cpf.matches(cpf.charAt(0) + "{11}")) {
                return false;
            } else {
                int digit1 = calculate(cpf.substring(0, 9), weightSsn);
                int digit2 = calculate(cpf.substring(0, 9) + digit1, weightSsn);
                return cpf.equals(cpf.substring(0, 9) + digit1 + digit2);
            }
        } else {
            return false;
        }
    }

    public static boolean isCpf(String cpf) {
        String cpfFormatted = formatCpf(cpf);
        return cpfFormatted != null && cpfFormatted.length() == 14;
    }

    public static boolean isCnpj(String cnpj) {
        String cnpjFormatted = formatCnpj(cnpj);
        return cnpjFormatted != null && cnpjFormatted.length() == 18;
    }

    public static boolean isValidCNPJ(final String tin) {
        if (tin != null && !tin.trim().isEmpty()) {
            String cnpj = StringUtil.onlyNumbers(formatCnpj(tin));
            if (cnpj.matches(cnpj.charAt(0) + "{14}")) {
                return false;
            } else {
                Integer digit1 = calculate(cnpj.substring(0, 12), weightTin);
                Integer digit2 = calculate(cnpj.substring(0, 12) + digit1, weightTin);
                return cnpj.equals(cnpj.substring(0, 12) + digit1.toString() + digit2.toString());
            }
        } else {
            return false;
        }
    }

    public static String formatCnpj(String cnpj) {
        if (cnpj != null) {
            cnpj = cnpj.replaceAll("\\D", "");
            cnpj = StringUtil.fillWithLeadingZeros(cnpj, 14);
            return cnpj.replaceAll("([0-9]{2})([0-9]{3})([0-9]{3})([0-9]{4})([0-9]{2})", "$1\\.$2\\.$3/$4-$5");
        } else {
            return null;
        }
    }

    public static String formatCpf(String cpf) {
        if (cpf != null) {
            cpf = cpf.replaceAll("\\D", "");
            cpf = StringUtil.fillWithLeadingZeros(cpf, 11);
            return cpf.replaceAll("([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})", "$1\\.$2\\.$3-$4");
        } else {
            return null;
        }
    }

}
