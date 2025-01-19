class Solution {
    public int solution(int n, int[] stations, int w) {
         int answer = 0;
        int coverage = 2 * w + 1; // 한 기지국이 커버할 수 있는 범위
        int position = 1;        // 현재 위치

        for (int station : stations) {
            int left = station - w; // 기지국 왼쪽 커버리지 시작
            if (position < left) {  // 커버되지 않은 구간 존재
                int gap = left - position; // 커버되지 않은 구간 길이
                answer += (gap + coverage - 1) / coverage; // 필요한 기지국 수 계산
            }
            position = station + w + 1; // 다음 시작 위치 갱신
        }

        // 마지막 기지국 이후로 커버되지 않은 구간 처리
        if (position <= n) {
            int gap = n - position + 1; // 남은 구간 길이
            answer += (gap + coverage - 1) / coverage; // 필요한 기지국 수 계산
        }

        return answer;
    }
}