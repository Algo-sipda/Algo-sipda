import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M; // 사람 수, 파티 수
	static int knowTruthCnt; // 진실을 아는 사람 수
	static ArrayList<Integer> knowTruthPeople = new ArrayList<>(); // 진실을 아는 사람 목록
	static ArrayList<Integer>[] comePeopleList; // 파티별 오는 사람 목록
	static ArrayList<Integer>[] goPartyList; // 사람별 가는 파티 목록
	static boolean[] personChecked, cantLieParty;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		personChecked = new boolean[N + 1];
		cantLieParty = new boolean[M + 1];

		st = new StringTokenizer(br.readLine());
		knowTruthCnt = Integer.parseInt(st.nextToken());
		for (int i = 0; i < knowTruthCnt; i++) {
			knowTruthPeople.add(Integer.parseInt(st.nextToken()));
		}

		comePeopleList = new ArrayList[M + 1];
		goPartyList = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			goPartyList[i] = new ArrayList<>();
		}

		for (int i = 0; i <= M; i++) {
			comePeopleList[i] = new ArrayList<>();
			if (i == 0)
				continue;
			st = new StringTokenizer(br.readLine());
			int comePeopleCnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < comePeopleCnt; j++) {
				int pNum = Integer.parseInt(st.nextToken());
				comePeopleList[i].add(pNum);
				goPartyList[pNum].add(i);
			}
		}

		DFS(knowTruthPeople);
		System.out.println(countCanLie());

	}

	public static void cantLiePartyCheck(int personNum) {
		for (int n : goPartyList[personNum]) {
			cantLieParty[n] = true;
		}
	}

	public static ArrayList<Integer> cantLiePersonCheck(int partyNum) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int n : comePeopleList[partyNum]) {
			if (!personChecked[n]) {
				list.add(n);
				personChecked[n] = true;
			}
		}
		return list;
	}

	public static void DFS(ArrayList<Integer> array) {
		for (int i : array) {
			cantLiePartyCheck(i);
			for (int n : goPartyList[i]) {
				DFS(cantLiePersonCheck(n));
			}
		}
	}

	public static int countCanLie() {
		int answer = 0;
		for (boolean b : cantLieParty) {
			if (!b)
				answer++;
		}
		return answer - 1;
	}

}