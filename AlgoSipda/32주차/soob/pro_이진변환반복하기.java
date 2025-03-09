class Solution {
    public int[] solution(String s) {
        int count = 0; // 변환 횟수
        int zeroCount = 0; // 제거된 0의 개수

        while (!s.equals("1")) {
            int originalLength = s.length();
            s = s.replace("0", "");
            int newLength = s.length();
            zeroCount += (originalLength - newLength);

            s = Integer.toBinaryString(newLength);
            count++;
        }

        return new int[]{count, zeroCount};
    }
}
