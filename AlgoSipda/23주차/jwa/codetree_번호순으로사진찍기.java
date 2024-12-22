import java.io.*;
import java.util.*;

public class Main {
    static class Relationship implements Comparable<Relationship> {
        int a, b;

        Relationship(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Relationship o) {
            if (this.b == o.b)
                return this.a - o.a;
            return this.b - o.b;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Relationship[] relationships = new Relationship[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            relationships[i] = new Relationship(Math.min(a, b), Math.max(a, b));
        }

        Arrays.sort(relationships);

        int answer = 1;
        int end = 0;
        for (Relationship r : relationships) {
            if (end < r.a) {
                answer += 1;
                end = r.b;
            }
        }
        System.out.println(answer);
    }
}

// 회의실 배정 문제 유형
