import java.io.*;
import java.util.*;

/**
 * 문제 N개 가짐 -> 문제의 난이도를 정수로 수치화(i번째의 문제 난이도)
 * 캠프에서 사용할 문제 => 두 문제 이상
 * L <= 문제 난이도 <= R
 * 어려운 문제 난이도 - 쉬운 문제 난이도 >= X
 * @author lcj52
 *
 */

public class Main {

	static int N, L, R, X;
	static int[] levelA;
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 문제 수
		L = Integer.parseInt(st.nextToken()); // 난이도 최소
		R = Integer.parseInt(st.nextToken()); // 난이도 최대
		X = Integer.parseInt(st.nextToken()); // 난이도 차 최소
		
		levelA = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			levelA[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(levelA);
		
		comb(0, 0, 0, Integer.MAX_VALUE, 0);
		System.out.println(answer);
	}

	private static void comb(int level, int sum, int idx, int min, int max) {
		if(level > N)
			return;
		
		if(level >= 2 && L <= sum && sum <= R) {
			if(max - min >= X)
				answer++;
		}
		
		for(int i = idx; i<N;i++) {
			comb(level+1, sum + levelA[i], i+1, Math.min(min, levelA[i]), Math.max(max, levelA[i]));
		}
	}
}