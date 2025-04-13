import java.io.*;
import java.util.*;

public class Main {
	
	static boolean check = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		findGoodSequence("", N);
	}
	
	private static void findGoodSequence(String str, int n) {
		if(check) return;
		else if(str.length() == n) {
			System.out.println(str);
			check = true;
			return;
		}
		else {
			for(int i=1;i<=3;i++) {
				if(checkGoodSequence(str+i)) {
					findGoodSequence(str+i, n);
				}
			}
		}
	}
	
	private static boolean checkGoodSequence(String str) {
		int len = str.length() / 2;
        
		for(int i=1;i<=len;i++) {
			if (str.substring(str.length() - i).equals(str.substring(str.length() - 2 * i, str.length() - i))) {
                return false;
            }
		}
		
		return true;
	}

}
