
// [BOJ] 행렬 제곱
// https://www.acmicpc.net/problem/10830

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 행렬 제곱
    static int N;
    static long B;
    static long[][] origin;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        origin = new long[N][N]; // N * N 행렬 A, A의 B제곱, A^B의 각 원소를 1,000으로 나눈 나머지
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                origin[i][j] = Long.parseLong(st.nextToken());
            }
        }

        // 행렬 A를 B제곱한 결과
        long[][] res = pow(origin, B);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static long[][] pow(long[][] matrix, long n) {
        if (n == 1) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    matrix[i][j] %= 1000;
                }
            }
            return matrix;
        }
        long[][] ret = pow(matrix, n / 2);
        ret = multiply(ret, ret);
        if (n % 2 == 1L) {
            ret = multiply(ret, origin);
        }
        return ret;
    }

    private static long[][] multiply(long[][] matrix, long[][] matrix2) {
        long[][] answer = new long[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    answer[i][j] += matrix[i][k] * matrix2[k][j]; //그냥 외우자
                    answer[i][j] %= 1000;
                }
            }
        }
        return answer;    
    }