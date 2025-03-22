import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] score = new int[N];
        int[] boss = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            boss[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            score[Integer.parseInt(st.nextToken()) - 1] += Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++) {
            score[i] += score[boss[i]];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(score[i] + (i < N - 1 ? " " : ""));
        }

        System.out.println(sb);
    }
}
