// [BOJ] 세 수의 합 https://www.acmicpc.net/problem/2295
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int N;
    static int[] U;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        U = new int[N];
        for (int i = 0; i < N; i++) {
            U[i] = Integer.parseInt(br.readLine());
        }
        // a + b + c = d
        // a + b = d - c : d 가 최대가 되도록 하는 것이 목표이므로 d를 큰수 -> 작은 수로 줄인다
        // 1. 두 수의 합을 구해서 배열을 만든다
        // 2. d가 최대인수부터 시작해서 i = 0 .. 값이 있는지 1번배열에서 찾는다
        int t = 0;
        arr = new int[N*N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                arr[t++] = U[i] + U[j];
            }
        }
        Arrays.sort(U);
        Arrays.sort(arr);

        for(int d = N - 1; d >= 0; d--) {
            for(int i = 0; i < d; i++) { // U[i]보다 U[j]가 작아야됨
                int gap = U[d] - U[i]; // 원본 배열에서 확인해야 함 
                if(isExist(gap)) {
                    System.out.println(U[d]);
                    return;
                }
            }
        }

    }
    static boolean isExist(int target) {
        int left = 0, right = N * N - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(arr[mid] == target) {
                return true;
            } else if(arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

}