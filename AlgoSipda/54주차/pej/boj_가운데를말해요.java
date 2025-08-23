
// [BOJ] 가운데를 말해요
// https://www.acmicpc.net/problem/1655

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    // 왼쪽: 최대 힙(작은 쪽 절반의 최댓값이 루트)
    static PriorityQueue<Integer> left = new PriorityQueue<>(Comparator.reverseOrder());
    // 오른쪽: 최소 힙(큰 쪽 절반의 최솟값이 루트)
    static PriorityQueue<Integer> right = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());

            // 1) 사이즈 균형 맞추며 삽입
            if (left.size() == right.size()) {
                left.offer(x);
            } else {
                right.offer(x);
            }

            // 2) 순서 조건 위배 시 루트만 교환
            if (!right.isEmpty() && left.peek() > right.peek()) {
                int a = left.poll();
                int b = right.poll();
                left.offer(b);
                right.offer(a);
            }

            // 현재 중간값 = left의 루트
            sb.append(left.peek()).append('\n');
        }

        System.out.print(sb);
    }
}
