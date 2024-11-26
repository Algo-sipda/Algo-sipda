class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long maxTime = 0;
        for (int i = 0; i < times.length; i++) {
            maxTime = Math.max(maxTime, times[i]);
        }
        
        long start = 0, end = maxTime * n;
        while (start <= end) {
            long mid = (start + end) / 2;
            long sum = 0;
            for (int time : times) {
                sum += mid / time;
                if (sum > n) {
                    answer = mid;
                    end = mid - 1;
                    break;
                }
            }
            if (sum < n) {
                start = mid + 1;
            } else if (sum == n) {
                answer = mid;
                end = mid - 1;
            }
        }
        
        return answer;
    }
}

// 1. 최대 심사시간 구하기
// 2. 최대 심사시간의 절반부터 탐색
// 3. times 배열 돌면서 몫을 다 더한 값을 기준으로
// 4. n보다 크면 중간값 줄이기
// 5. n보다 작으면 중간값 키우기
