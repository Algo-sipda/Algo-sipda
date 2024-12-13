package ez;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class boj_고기잡이 {

    static int N, I, M, max;
    static int[] selected;
    static Fish[] fishes;

    static class Fish {
        int r, c;
        public Fish(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        I = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        fishes = new Fish[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            fishes[i] = new Fish(r, c);
        }

        selected = new int[2];
        combi(0);
        System.out.println(max);
    }

    private static void combi(int cnt) {
        if (cnt == 2) {
            for (int k = 1; k < I / 2; k++) {
                max = Math.max(max, useNet(k, I/2 - k));
            }
            return;
        }

        for (int i = 0; i < M; i++) {
            selected[cnt] = i;
            combi(cnt + 1);
        }
    }

    private static int useNet(int width, int height) {
        int cnt = 0;
        for (int k = 0; k < M; k++) {
            if(fishes[selected[0]].r <= fishes[k].r && fishes[k].r <= fishes[selected[0]].r + width &&
                    fishes[selected[1]].c <= fishes[k].c && fishes[k].c <= fishes[selected[1]].c + height) {
                cnt++;
            }
        }
        return cnt;
    }
}