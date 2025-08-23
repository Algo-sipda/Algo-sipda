// [BOJ] 컵라면 
// https://www.acmicpc.net/problem/1781



import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

		// 데드라인이 짦은 순, 컵라면이 많은 순
        PriorityQueue<int[]> problems = new PriorityQueue<>((a,b)->{
            int result = a[0]-b[0];
            return result == 0 ? b[1] - a[1] : result;
        });

		// 문제 정보 입력
        while (N-- > 0) {
            String[] input = br.readLine().split(" ");
            int deadLine = Integer.parseInt(input[0]);
            int numOfRamen = Integer.parseInt(input[1]);

            problems.offer(new int[]{deadLine, numOfRamen});
        }

        PriorityQueue<Integer> select = new PriorityQueue<>();

		// 문제 결정
        while (!problems.isEmpty()) {
            int[] problem = problems.poll();
            int deadLine = problem[0];
            int numOfRamen = problem[1];

            if (select.size() < deadLine) {
                select.offer(numOfRamen);
            } else {
                if (select.peek() < numOfRamen) {
                    select.poll();
                    select.offer(numOfRamen);
                }
            }
        }

        long result = 0L;
        for (Integer numOfRamen : select) {
            result += numOfRamen;
        }
        System.out.println(result);

    }
}