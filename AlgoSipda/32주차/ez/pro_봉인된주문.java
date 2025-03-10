import java.util.*;

class pro_봉인된주문 {
    public String solution(long n, String[] bans) {
        long[] arr = new long[bans.length];
        for (int i = 0; i < bans.length; i++) {
            arr[i] = convertNum(bans[i]);
        }
        Arrays.sort(arr);

        for (long num : arr) {
            if (n >= num) {
                n++;
                continue;
            }
            break;
        }
        return convertString(n);
    }

    private String convertString(long num) {
        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            String mod = String.valueOf((char) ('a' + (int) ((num - 1) % 26)));
            sb.append(mod);
            // num /= 26;
            num = (num - 1) / 26;
        }

        return sb.reverse().toString();
    }

    private long convertNum(String s) {
        long sum = 0;
        for (int i = 0; i < s.length(); i++) {
            long value = s.charAt(i) - 'a' + 1;
            sum = sum * 26 + value;
        }
        return sum;
    }
}

/*
알파벳 개수 -> 26개
*/