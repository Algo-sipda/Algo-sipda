import java.io.*;
import java.util.*;

// Logic
// 해당 값이 중앙값으로 나오도록 하기 위해서는
// 해당 값의 index 위치를 찾은 다음 그 앞에 있는 부분과 뒤에 있는 부분을 곱해줘서 계산해줍니다.

public class Main {

    public static int n, q;
    public static int[] m;
    

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());//n: 자동차 개수
        q = Integer.parseInt(st.nextToken());//q: 질의 개수

        m = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            m[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(m);

        for(int i=0;i<q;i++) {
            int num = Integer.parseInt(br.readLine());

            int idx = 0;
            for(int j=0;j<n;j++) {
                if(m[j] == num) {
                    idx = j+1;
                    break;
                }
            }
            int answer = (idx-1)*(n-idx);
            if(answer <0) answer = 0;
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
