class Solution {
    public int solution(String name) {
        int answer = 0;
        int len = name.length();
        int idx = 0;
        int min = len - 1;

        for (int i = 0; i < len; i++) {
            char alpha = name.charAt(i);
            answer += Math.min(alpha - 'A', 'Z' - alpha + 1);
            idx = i + 1;

            while (idx < len && name.charAt(idx) == 'A') {
                idx++;
            }

            min = Math.min(min, i * 2 + len - idx);
            min = Math.min(min, (len - idx) * 2 + i);
        }
        return answer + min;
    }
}