import java.io.*;
import java.util.*;

public class Main {

    static int N, max = 0;
    static HashMap<String, Integer> name;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());

        name = new HashMap<>();

        for(int i=0;i<N;i++) {
            String[] arr = new String[3];

            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<3;j++) {
                arr[j] = st.nextToken();
            }
            Arrays.sort(arr);

            String str = arr[0]+arr[1]+arr[2];
            
            if(name.containsKey(str)) {
                name.put(str, name.get(str)+1);
                max = Math.max(name.get(str), max);
            }
            else {
                name.put(str, 1);
            }
        }
        
        System.out.println(max);
    }
}