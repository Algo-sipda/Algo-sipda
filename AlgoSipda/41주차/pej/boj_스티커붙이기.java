// [BOJ] 스티커 붙이기 https://www.acmicpc.net/problem/18808
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K; // 세로, 가로, 스티커 개수
    static int[][] notebook;
    static int[][] sticker;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 1. 회전 없이 -> 회전 후
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 노트북 세로
        M = Integer.parseInt(st.nextToken()); // 가로
        K = Integer.parseInt(st.nextToken()); // 스티커의 개수
        notebook = new int[N][M];

        for(int k = 0; k < K ; k++){
            // 스티커 개수
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            sticker = new int[R][C];
            for(int r = 0; r < R; r++){
                st = new StringTokenizer(br.readLine());
                for(int c = 0; c < C; c++){
                    sticker[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            //print("Sticker", sticker);
            // 0 그대로 -> 1 2 3
            for(int r = 0; r < 4; r++) {
                int[] start = findAttachLoc();
                if(isValid(start)) {
                    attach(start);
                    //print(">>>>>>BOX", notebook);
                    break;
                }
                sticker = rotate(sticker);
                //print("rotated", sticker);
            }
        }

        System.out.println(count());
    }
    static int[][] rotate(int[][] sticker) {
        int[][] next = new int[sticker[0].length][sticker.length];
        for(int i = 0; i < sticker.length; i++) {
            for(int j = 0; j < sticker[0].length; j++) {
                next[j][sticker.length - 1 - i] = sticker[i][j];
            }
        }
        return next;
    }

    static int count() {
        int sum = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                sum += notebook[i][j];
            }
        }
        return sum;
    }

    static void attach(int[] start) {
      for(int r = start[0]; r < start[0] + sticker.length; r++) {
          for(int c = start[1]; c < start[1] + sticker[0].length; c++) {
              notebook[r][c] = sticker[r-start[0]][c-start[1]] == 1 ? 1 : notebook[r][c];
          }
      }
    }

    static int[] findAttachLoc() {
        for(int i = 0; i < notebook.length; i++){
            for(int j = 0; j < notebook[0].length; j++){
                if(!isStickerInRange(i, j, sticker.length, sticker[0].length)) {
                    continue;
                }
                // TODO: out of range check
                if(canAttach(i, j)) {
                    return new int[]{i, j};
                }

            }
        }
        return new int[]{-1, -1};

    }

    static boolean canAttach(int i, int j) {
        for(int r = i; r < i + sticker.length; r++) {
            for(int c = j; c < j + sticker[0].length; c++){
                if(notebook[r][c] == 1 && sticker[r-i][c-j] == 1) { // 붙일 수 없는 조건
                    return false;
                }
            }
        }
        return true;
    }
    static boolean isStickerInRange(int i, int j, int srmax, int scmax) {
        return i + srmax <= N && j + scmax <= M;
    }

    static boolean isValid(int[] start) {
        return start[0] != -1 && start[1] != -1;
    }
//    static void print(String name, int[][] box){
//        System.out.println("===="+name+"=====");
//        for(int i = 0; i < box.length; i++) {
//            for(int j = 0; j < box[0].length; j++) {
//                System.out.print(box[i][j]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//    }
}