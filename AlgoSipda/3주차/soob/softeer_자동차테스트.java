import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        
        List<Integer> list = new ArrayList<>();
        
        st = new StringTokenizer(br.readLine());
        
        for(int i = 0; i < n; i++){
            list.add(Integer.parseInt(st.nextToken()));    
        }
        
        Collections.sort(list);
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int i = 0; i < n; i++){
            map.put(list.get(i), i + 1);
        }

        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < q; i++){
            int m = Integer.parseInt(br.readLine());
            if(map.get(m) == null)
                sb.append(0).append("\n");
            else
                sb.append((map.get(m) - 1) * (n - map.get(m))).append("\n");
        }
        
        System.out.println(sb);
    }
}