// [BOJ] 넴모넴모 https://www.acmicpc.net/problem/14712

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int MAX;
    static int N, M; // N x M : 1 x 1 - 25 x 25
    static int[][] box;
    static int result;
    public static void main(String[] args) throws Exception {
        // 1. 비어 있는 곳에 네모 하나 추가
        // 2. 2 x 2 사각형 이루는 부분 찾기
        // 넴모가 없으면 게임을 그만두기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        box = new int[N][M];
        MAX = N * M;
        back(0, box);
        System.out.println(result);
    }

    static boolean isValid(int[][] box) {
        for(int i = 0; i <= N-2; i++) {
            for(int j = 0; j <= M-2; j++) {
                int cnt = 0; // 2 x 2 채워져 있음
                for(int k = 0; k < 2; k++) {
                    for(int l = 0; l < 2; l++) {
                        if(box[i+k][j+l] == 1) {
                            cnt += 1;
                        }
                    }
                }
                if(cnt == 4) {
                    return false;
                }
            }
        }
        return true;
    }

    // 비어 있는 곳에 추가
    static void back(int depth, int[][] box) {
        if(depth == N * M) {
            if(isValid(box)){
                result+=1;
            }
            return;
        }

        // (r,c) 칸을 선택한 경우
        int r = depth / M;
        int c = depth % M;
        box[r][c] = 1;
        back(depth + 1, box);

        // (r,c) 칸을 선택하지 않은 경우
        box[r][c] = 0;
        back(depth + 1, box);

    }
}