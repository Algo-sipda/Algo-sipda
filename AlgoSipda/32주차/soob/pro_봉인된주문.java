import java.util.*;

class Solution {
    private static final int MAX_LENGTH = 12;
    private static List<Long>[] bannedWords = new ArrayList[MAX_LENGTH];

    static {
        for (int i = 0; i < MAX_LENGTH; i++) {
            bannedWords[i] = new ArrayList<>();
        }
    }

    private static long strToNum(String s) {
        long number = 0;
        for (char c : s.toCharArray()) {
            number = number * 26 + (c - 'a');
        }
        return number;
    }

    private static String numToStr(long number, int length) {
        StringBuilder sb = new StringBuilder();
        while (length-- > 0) {
            sb.append((char) ('a' + number % 26));
            number /= 26;
        }
        return sb.reverse().toString();
    }

    public static String solution(long n, String[] bans) {
        for (String ban : bans) {
            bannedWords[ban.length()].add(strToNum(ban));
        }

        for (int length = 1; length < MAX_LENGTH; length++) {
            Collections.sort(bannedWords[length]);

            long totalWords = (long) Math.pow(26, length);
            long bannedCount = bannedWords[length].size();
            long validCount = totalWords - bannedCount;

            if (n > validCount) {
                n -= validCount;
                continue;
            }

            long remaining = n;
            long previous = -1;
            long selectedWord = -1;

            for (long bannedWord : bannedWords[length]) {
                long gap = bannedWord - (previous + 1);

                if (remaining <= gap) {
                    selectedWord = previous + remaining;
                    return numToStr(selectedWord, length);
                }

                remaining -= gap;
                previous = bannedWord;
            }

            return numToStr(previous + remaining, length);
        }

        return "";
    }
}
