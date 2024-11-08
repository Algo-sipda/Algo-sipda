/*
1. n -> binary 교체
2. 1 개수 카운트해서 저장
3. n부터 1씩 더해가면서 1 개수 비교
*/

class pro_다음큰숫자 {
    public int solution(int n) {
        int answer = n;
        int cnt = binaryCount(n);

        while (true) {
            if (cnt == binaryCount(++answer)) {
                break;
            }
        }
        return answer;
    }

    private static int binaryCount(int n) {
        int cnt = 0;
        while (n > 0) {
            if (n % 2 == 1) {
                cnt++;
            }
            n /= 2;
        }
        return cnt;
    }
}
