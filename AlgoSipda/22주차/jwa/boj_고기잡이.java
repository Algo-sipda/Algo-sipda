import java.io.*;
import java.util.*;
import java.awt.Point;

public class Main {
    static int N, I, M;
    static Point[] fishs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        I = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        fishs = new Point[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            fishs[i] = new Point(x, y);
        }

        int answer = 0;

        for (int i = 0; i < M; i++) {
            for (int j = i + 1; j < M; j++) {
                for (int size = 1; size < I / 2; size++) {
                    int x = Math.min((int) fishs[i].getX(), (int) fishs[j].getX());
                    int y = Math.min((int) fishs[i].getY(), (int) fishs[j].getY());

                    int count = fishing(x, y, size);
                    answer = Math.max(answer, count);
                }
            }
        }

        if (M == 1) {
            answer = 1;
        }

        System.out.println(answer);
    }

    static int fishing(int x, int y, int size) {
        int count = 0;

        for (int i = 0; i < M; i++) {
            int nx = (int) fishs[i].getX();
            int ny = (int) fishs[i].getY();
            if (x <= nx && nx <= x + size && y <= ny && ny <= y + (I / 2 - size)) {
                count++;
            }
        }

        return count;
    }
}
