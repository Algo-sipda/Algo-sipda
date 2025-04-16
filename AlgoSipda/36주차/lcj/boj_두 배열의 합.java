import java.io.*;
import java.util.*;

public class Main {
	
	static int[] A, B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		
		int n = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine()," ");
		A = new int[n];
		for(int i=0;i<n;i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int m = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine()," ");
		B = new int[m];
		for(int i=0;i<m;i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		ArrayList<Integer> subA = new ArrayList<>();
		ArrayList<Integer> subB = new ArrayList<>();
		
		for(int i=0;i<n;i++) {
			int sum = 0;
			for(int j=i;j<n;j++) {
				sum += A[j];
				subA.add(sum);
			}
		}
		
		for(int i=0;i<m;i++) {
			int sum = 0;
			for(int j=i;j<m;j++) {
				sum += B[j];
				subB.add(sum);
			}
		}
		
		Collections.sort(subA);
		Collections.sort(subB);
		
		int aSize = subA.size();
		
		long cnt = 0;
		int leftP = 0;
		int rightP = subB.size()-1;
		
		while(leftP < aSize && rightP >= 0) {
			int sum = subA.get(leftP) + subB.get(rightP);
			
			if(sum == T) {
				int a = subA.get(leftP);
				int b = subB.get(rightP);
				
				long leftCnt = 0;
				long rightCnt = 0;
				
				while(leftP < aSize && subA.get(leftP) == a) {
					leftCnt++;
					leftP++;
				}
				
				while(rightP>=0 && subB.get(rightP) == b) {
					rightCnt++;
					rightP--;
				}
				
				cnt += (leftCnt * rightCnt);
			}
			else if(sum < T) {
				leftP++;
			}
			else if(sum > T) {
				rightP--;
			}
		}
		System.out.println(cnt);
	}

}