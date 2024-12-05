import java.io.*;
import java.util.*;

// 두 개씩 골라서 x, y로 삼고 조건 만족하는 z 개수 세기
// 수식을 z 기준 정리하고 범위 최솟값, 최댓값 binarySearch 해서 범위 내에 들어오는 개수 셈

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> cows = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            cows.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(cows);

        int answer = 0;
        // 앞에서부터 2개씩 탐색
        for (int i = 0; i < cows.size() - 2; i++) {
            for (int j = i + 1; j < cows.size() - 1; j++) {
                int x = cows.get(i);
                int y = cows.get(j);
                int minValue = 2 * y - x;
                int maxValue = 3 * y - 2 * x;
                // 조건 만족하는 범위 내에 있는 소 카운트
                int minIdx = Collections.binarySearch(cows, minValue);
                int maxIdx = Collections.binarySearch(cows, maxValue);
                if (minIdx < 0 && maxIdx < 0) {
                    answer += minIdx - maxIdx;
                    continue;
                }
                if (minIdx < 0) {
                    minIdx = -minIdx - 1;
                }
                if (maxIdx < 0) {
                    maxIdx = -maxIdx - 1;
                } else {
                    answer += 1; // 범위 최댓값에 소가 있으면 하나 더해줘야 함
                }
                answer += maxIdx - minIdx;
            }
        }
        System.out.println(answer);
    }
}
