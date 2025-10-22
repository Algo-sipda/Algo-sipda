import java.io.*;
import java.util.*;

public class Main {

	private static int n;
	private static int[] parent;
	private static int delete = -1;
	private static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());

		parent = new int[n];

		int root = -1;
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			parent[i] = Integer.parseInt(st.nextToken());
			if (parent[i] == -1) {
				root = i;
			}
		}

		delete = Integer.parseInt(br.readLine());

		deleteNode(delete);
		countLeaf(root);
		System.out.println(answer);
	}

	private static void deleteNode(int delete) {
		parent[delete] = -2;
		for (int i = 0; i < n; i++) {
			if (parent[i] == delete)
				deleteNode(i);
		}
	}

	private static void countLeaf(int node) {
		for (int i = 0; i < n; i++) {
			if (parent[i] != -2) {
				boolean check = false;
				for (int j = 0; j < n; j++) {
					if (parent[j] != -2) {
						if (i == parent[j]) {
							check = true;
							break;
						}
					}
				}
				if (!check)
					answer++;
			}
		}
	}
}
