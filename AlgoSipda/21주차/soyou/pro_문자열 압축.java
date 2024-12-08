class Solution {
    public int solution(String s) {
        int answer = 1000;
        int len = s.length();
        int maxSlice = len / 2 == 0 ? 1 : len / 2;

        for (int sliceNum = 1; sliceNum <= maxSlice; sliceNum++) {
            StringBuilder result = new StringBuilder();
            int count = 1;

            for (int i = 0; i < len; i += sliceNum) {
                String str = s.substring(i, Math.min(i + sliceNum, len));
                String nextStr = i + sliceNum < len ? s.substring(i + sliceNum, Math.min(i + 2 * sliceNum, len)) : "";

                if (!str.equals(nextStr)) {
                    if (count == 1) {
                        result.append(str);
                    } else {
                        result.append(count).append(str);
                    }
                    count = 1;
                } else {
                    count++;
                }
            }

            answer = Math.min(answer, result.length());
        }
        return answer;
    }
}