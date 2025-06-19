import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
세그먼트 트리 강의 참고
https://www.youtube.com/watch?v=1d9sqmuLy-o

배열 크기: 2^k >= N을 만족하는 k값 -> [2^k * 2]
시작 인덱스: 2^k
인덱스 수정: 3번째 값 수정할 경우 -> 2^k + 3(인덱스) - 1
질의값 구하는 과정
1. start % 2 == 1일 때 노드 선택
2. end % 2 == 0일 때 노드 선택
3. start = (start + 1) / 2, end = (end - 1) / 2로 변경
4. start < end 일 때 종료

 */

public class boj_구간합구하기 {

    static int N, M, K, size;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        size = (int) Math.ceil(Math.log(N) / Math.log(2));
        tree = new long[(int) Math.pow(2, size) * 2];
        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(br.readLine());
            tree[(int) Math.pow(2, size) + i] = num;
        }

        init(1, N - 1, 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            long command = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (command == 1) { // update
                int idx = (int) ((int) Math.pow(2, size) + b - 1);
                long diff = c - tree[idx];
                update(idx, diff);
            } else { // sum
                long start = (long) (Math.pow(2, size) + b - 1);
                long end = (long) (Math.pow(2, size) + c - 1);
                sb.append(getSum(start, end) + "\n");
            }
        }
        System.out.print(sb);
    }

    private static long getSum(long start, long end) {
        long sum = 0;
        while (start <= end) {
            if (start % 2 == 1) {
                sum += tree[(int) start];
            }
            if (end % 2 == 0) {
                sum += tree[(int) end];
            }

            start = (start + 1) / 2;
            end = (end - 1) / 2;
        }
        return sum;
    }

    private static void update(int idx, long diff) {
        while (idx > 0) {
            tree[idx] += diff;
            idx /= 2;
        }
    }

    private static void init(int start, int end, int node) {
        for (int i = (int) Math.pow(2, size) - 1; i > 0; i--) {
            tree[i] += tree[i * 2] + tree[i * 2 + 1];
        }
    }
}
