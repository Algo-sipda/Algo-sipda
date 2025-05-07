class pro_두원사이의정수쌍 {
    public long solution(int r1, int r2) {
        long answer = 0;
        long ro1 = (long) Math.pow(r1, 2);
        long ro2 = (long) Math.pow(r2, 2);
        long num = 0;
        for (long i = 0; i <= r2; i++) {
            long y2 = (long) Math.sqrt(ro2 - i * i);
            long y1 = (long) Math.sqrt(ro1 - i * i);
            if (Math.sqrt(ro1 - i * i) % 1 == 0) num++;

            answer += (y2 - y1) * 4;
        }
        answer += num * 4;
        return answer - 4;
    }
}
