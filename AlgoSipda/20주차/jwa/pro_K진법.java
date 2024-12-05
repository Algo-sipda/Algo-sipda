import java.util.*;

class Solution {
    static boolean isPrime(long num) {
        if (num < 2)
            return false;
        for (long i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }

    // 첫 번째 풀이
    public int solution(int n, int k) {
        int answer = 0;
        String convertedNum = Integer.toString(n, k);
        int length = convertedNum.length();
        int i = 0;
        int j = 0;
        StringBuilder sb = new StringBuilder();
        while (i < length) {
            sb.append(convertedNum.charAt(j));
            if (++j < length && convertedNum.charAt(j) != '0') {
                continue;
            }
            if (isPrime(Long.parseLong(sb.toString()))) {
                answer++;
            }
            while (j < length && convertedNum.charAt(j) == '0') {
                j++;
            }
            i = j;
            sb = new StringBuilder();
        }

        return answer;
    }

    // 두 번째 풀이 (다른 사람 코드 참고)
    public int solution2(int n, int k) {
        int answer = 0;
        String convertedNum = Integer.toString(n, k);
        long[] nums = Arrays.stream(convertedNum.split("0")).filter(s -> !s.isEmpty())
                .mapToLong(Long::parseLong).toArray();

        for (long num : nums) {
            if (isPrime(num)) {
                answer++;
            }
        }

        return answer;
    }
}
