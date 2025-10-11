import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	private static int N, L;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());	//	N : 물 웅덩이 개수
		L = Integer.parseInt(st.nextToken());	// 	L : 널빤지 길이
		
		int[][] arr = new int[N][2];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // 시작 위치
            arr[i][1] = Integer.parseInt(st.nextToken()); // 끝 위치
		}
		
		Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return Integer.compare(o1[1], o2[1]);
                }
                return Integer.compare(o1[0], o2[0]);
            }
        });
		
		int range = 0;
		int answer = 0;
		
		for(int i=0;i<N;i++) {
            if (arr[i][0] > range) { // 시작위치가 범위보다 클 경우
                range = arr[i][0];
            }
            if (arr[i][1] >= range) { // 끝위치가 범위보다 클 경우
                while (arr[i][1] > range) {
                    range += L;
                    answer++;
                }
            }
		}
		System.out.println(answer);
	}

}