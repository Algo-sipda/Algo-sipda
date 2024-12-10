class pro_최고의집합 {
    public int[] solution(int n, int s) {
        if (n > s) {
            return new int[] { -1 };
        }

        int[] answer = new int[n];
        int idx = 0;
        while (n > 0) {
            int divide = s / n;
            answer[idx++] = divide;
            n--;
            s -= divide;
        }

        return answer;
    }
}