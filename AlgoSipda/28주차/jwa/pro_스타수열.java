class Solution {
    public int solution(int[] a) {
        int answer = 0;
        int n = a.length;
        int[] counter = new int[a.length];

        for (int num : a) {
            counter[num]++;
        }

        // i: 교집합이 될 숫자
        for (int i = 0; i < a.length; i++) {
            if (counter[i] * 2 <= answer)
                continue;

            int star = 0;

            for (int j = 0; j < a.length - 1; j++) {
                if (a[j] != a[j + 1] && (a[j] == i || a[j + 1] == i)) {
                    star += 2;
                    j++;
                }
            }

            answer = Math.max(answer, star);
        }

        return answer;
    }
}
