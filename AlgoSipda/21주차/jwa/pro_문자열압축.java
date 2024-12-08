class Solution {
    public int solution(String s) {
        int answer = 1001;

        if (s.length() == 1) {
            return 1;
        }

        for (int unit = 1; unit < s.length(); unit++) {
            int i = 0;
            int count = 0;
            while (true) {
                int j = i + unit;
                int times = 1; // 반복 횟수

                while (j <= s.length() - unit) {
                    if (s.substring(i, i + unit).equals(s.substring(j, j + unit))) {
                        times++;
                        j += unit;
                    } else {
                        break;
                    }
                }

                if (times == 1) {
                    count += unit;
                    if (j == s.length() - unit) {
                        count += unit;
                    }
                } else {
                    count += Math.log10(times) + 1 + unit;
                }
                i = j;
                if (i >= s.length() - unit) {
                    break;
                }
            }
            count += s.length() % unit;
            answer = Math.min(answer, count);
        }

        return answer;
    }
}

// 그냥 모든 경우 ...
