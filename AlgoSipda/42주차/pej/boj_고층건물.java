// [BOJ] 고층 건물 https://www.acmicpc.net/problem/1027
import java.io.*;
import java.util.*;
public class Main {
    static int N; // 빌딩의 수 
    static int[] buildings;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        buildings = new int[N];
        for(int i = 0; i < N; i++){
            buildings[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        for(int i = 0; i < N; i++) {
            answer = Math.max(answer, find(i));
        }
        System.out.println(answer);
    }   
    static int find(int id){
        int cnt = 0;
        double temp = 0;
        for(int i = id - 1; i >= 0; i--) {
            double slope = (double) (buildings[id] - buildings[i]) / (id - i);
            if(i == id - 1 || temp > slope) { // 기울기 음수 
                cnt += 1;
                temp = slope;  
            }
        }

        for(int i = id + 1; i < N; i++) {
            double slope = (double)(buildings[id] - buildings[i]) / (id - i);
            if(i == id + 1 || slope > temp) { // 기울기 양수, temp 초기화 다시 안해도 처음에 무조건 만족함 
                cnt += 1; 
                temp = slope;
            }
        }
        return cnt;
    }
}