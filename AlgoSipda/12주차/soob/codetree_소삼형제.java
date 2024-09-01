import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Map<String, Integer> map = new HashMap<>();
    
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String word1 = st.nextToken();
            String word2 = st.nextToken();
            String word3 = st.nextToken();
            List<String> list = new ArrayList<>();
            list.add(word1);
            list.add(word2);
            list.add(word3);
            Collections.sort(list);
            String totalWord = "";
            for(String str : list){
                totalWord += str + " ";
            }
            if(map.containsKey(totalWord))
                map.put(totalWord, map.get(totalWord)+1);
            else
                map.put(totalWord, 1);
        }

        List<Integer> list = new ArrayList<>(map.values());
        Collections.sort(list, Collections.reverseOrder());

        System.out.println(list.get(0));
    }
}