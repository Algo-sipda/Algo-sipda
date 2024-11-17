import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        BigInteger B = BigInteger.valueOf(Long.parseLong(st.nextToken()));

        // 초기 상태 저장
        List<Integer> onList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x == 1) {
                onList.add(i);
            }
        }

        Map<List<Integer>, BigInteger> stateToStep = new HashMap<>();
        List<List<Integer>> stateHistory = new ArrayList<>();
        BigInteger step = BigInteger.ZERO;

        while (step.compareTo(B) < 0) {
            if (stateToStep.containsKey(onList)) {
                // 사이클 발견
                BigInteger cycleStartStep = stateToStep.get(onList);
                BigInteger cycleLength = step.subtract(cycleStartStep);

                // 남은 반복 횟수에서 사이클 활용
                BigInteger remainingSteps = B.subtract(step);
                BigInteger skipCycles = remainingSteps.divide(cycleLength);

                step = step.add(skipCycles.multiply(cycleLength));
            }

            if (step.compareTo(B) >= 0) {
                break;
            }

            // 현재 상태 저장
            stateToStep.put(new ArrayList<>(onList), step);
            stateHistory.add(new ArrayList<>(onList));

            // 상태 업데이트
            List<Integer> temp = new ArrayList<>(onList);
            for (int x : onList) {
                int next = (x + 1) % N;
                if (onList.contains(next)) {
                    temp.remove(Integer.valueOf(next));
                } else {
                    temp.add(next);
                }
            }
            onList = temp;
            step = step.add(BigInteger.ONE);
        }

        // 최종 상태 출력
        for (int i = 0; i < N; i++) {
            if (onList.contains(i)) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}
