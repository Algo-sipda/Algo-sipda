import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] map = new int[n];
        int total = 0;
        for(int y = 0; y < n; y++){
            int cnt = 0;
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x < m; x++){
                if(Integer.parseInt(st.nextToken()) == 1)
                    cnt++;
            }
            map[y] = cnt;
            total += cnt;
        }

        int[] attack = new int[n];
        int min = 101;
        int max = -1;
        for(int i = 0; i < 2; i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken())-1;
            int r = Integer.parseInt(st.nextToken())-1;
            attack[l]++;
            if(r < n-1)
                attack[r+1]--;
            min = Math.min(min, l);
            max = Math.max(max, r);
        }

        int remove = 0;
        int dead = 0;
        for(int i = min; i <= max; i++){
            dead += attack[i];
            remove += Math.min(dead, map[i]);
        }

        System.out.println(total - remove);
    }
}