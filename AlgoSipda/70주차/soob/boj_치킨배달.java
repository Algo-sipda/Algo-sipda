import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N; // map의 크기 N*N
	static int M; // 남길 치킨집 개수
	static int totalChickCnt; // 총 치킨집 개수
	static int[][] map; // 지도 정보
	static int answer = Integer.MAX_VALUE;
	static ArrayList<Point> houseArr = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		ArrayList<Point> chickPlace = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					houseArr.add(new Point(i, j));
				}
				if (map[i][j] == 2) {
					totalChickCnt++;
					chickPlace.add(new Point(i, j));
				}
			}
		}

		ArrayList<Point> selectedChickPlace = new ArrayList<>();
		selectChick(chickPlace, selectedChickPlace, 0, 0);

		System.out.println(answer);
	}

	/**
	 * 
	 * 치킨집을 고르고, 고른 치킨집 좌표를 chickPlace에 저장 -> 조합 M개가 되면 고른 치킨집 좌표 배열을 리턴
	 * 
	 * @param chickPlace : 모든 치킨집 좌표
	 * @param count      : 몇 개를 선택했는지
	 */
	public static void selectChick(ArrayList<Point> chickPlace, ArrayList<Point> selectedChickPlace, int start,
			int count) {

		if (count == M) {

			int sum = 0;

			for (int i = 0; i < houseArr.size(); i++) {
				sum += calDis(selectedChickPlace, houseArr.get(i).x, houseArr.get(i).y, Integer.MAX_VALUE);
				if (sum >= answer)
					return;
			}

			answer = Math.min(answer, sum);
			return;
		}

		// 치킨집 조합
		for (int i = start; i < totalChickCnt; i++) {
			selectChick(chickPlace, selectedChickPlace, i + 1, count);
			selectedChickPlace.add(chickPlace.get(i));
			selectChick(chickPlace, selectedChickPlace, i + 1, count + 1);
			selectedChickPlace.remove(selectedChickPlace.size() - 1);
		}
	}

	/**
	 * 현재 지점에서 가장 가까운 치킨집 거리 구하기
	 * 
	 * @param selectedChickPlace : 치킨집 좌표 배열
	 * @param x                  : 현재 지점의 x좌표
	 * @param y                  : 현재 지점의 y좌표
	 * @param minDis             : 가장 가까운 치킨거리를 저장해줄 변수
	 * @return 현재 위치에서 치킨거리
	 */
	public static int calDis(ArrayList<Point> selectedChickPlace, int x, int y, int minDis) {
		int tempDis;
		for (int i = 0; i < M; i++) {
			tempDis = Math.abs(selectedChickPlace.get(i).x - x) + Math.abs(selectedChickPlace.get(i).y - y);
			minDis = Math.min(minDis, tempDis);
		}

		return minDis;
	}

}