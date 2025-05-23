import java.io.*;
import java.util.*;


class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Set<Integer> set = new HashSet<>();

        int n = Integer.parseInt(br.readLine());

        int[] u = new int[n];
        
        for(int i = 0; i < n; i++){
            u[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 0; i < n; i++){
            for(int j = i; j < n; j++){
                set.add(u[i] + u[j]);
            }
        }

        int max = -1;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                int target = u[i] - u[j];
                
                if(set.contains(target)){
                    max = Math.max(max, u[i]);
                }
            }
        }

        System.out.println(max);

    }
}