import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] house = new int[n];
        for(int i = 0; i < n; i++){
            house[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> map = new HashMap<>();
        for(int h : house){
            Set<Integer> set = new HashSet<>();
            for(int i = 2; i <= h; i++){
                if(h % i == 0)
                    set.add(i);
            }
            
            for(int s : set){
                if(map.containsKey(s))
                    map.put(s, map.get(s) + 1);
                else
                    map.put(s, 1);
            }
        }

        int max = 0;
        int answer = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() > max){
                answer = entry.getKey();
                max = entry.getValue();
            }
        }
        System.out.println(max);
    }
}