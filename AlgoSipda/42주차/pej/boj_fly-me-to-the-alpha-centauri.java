// [BOJ] Fly me to the Alpha Centauri https://www.acmicpc.net/problem/1011

import java.io.*;
import java.util.*;
class Main {
    static int T;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dist = y - x;
            int max = (int)Math.sqrt(dist);

            // dist = 4 인 경우 
            if(max == Math.sqrt(dist)) {
                System.out.println(2 * max - 1);
            } else if(dist <= max * max + max) {// dist = 5 인 경우
                System.out.println(2 * max);
            } else {
                System.out.println(2 * max + 1);
            }

        }
        
    }   
}
/*
1
0 1 2
  1 2 3
    2 3 4 
  1 2 3
0 1 2
 */
// 1 
// 1 1
// 1 2 1
// 1 2 2 1            4
// 1 2 3 2 1          5
// 1 2 3 3 2 1        6
// 1 2 3 4 3 2 1      7
// 1 2 3 4 4 3 2 1    8
// 1 2 3 4 5 4 3 2 1  9
// x -> (y-1) -> y 