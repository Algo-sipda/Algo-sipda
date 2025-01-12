class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int cur = 1;
        int stationIdx = 0;
        
        while(cur <= n) {
            if(stationIdx >= stations.length || cur < stations[stationIdx] - w) {
                // 현재 위치가 모든 기지국을 넘어가거나
                // 현재 위치가 기지국의 범위보다 작을 경우
                answer++;
                cur = cur + 2*w + 1; // cur를 새로 설치한 기지국의 범위 밖으로 이동
            }
            else {
                cur = stations[stationIdx] + w + 1;
                stationIdx++;
            }
        }
        
        return answer;
    }
}