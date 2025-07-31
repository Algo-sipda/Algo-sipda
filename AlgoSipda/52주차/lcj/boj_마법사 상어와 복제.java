import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Shark{
	int r, c;

	public Shark(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
	
}
public class Main {
	
	static int M, S;//M : 물고기 수, S : 마법 연습 횟수
	
	// 상어 이동
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	//물고기 이동
	static int[] fish_dr = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] fish_dc = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	static Shark shark;
	static int[][][] origin;//물고기 숫자
	static int[][] smell;//냄새 저장
	static int[][][] temp;//이동한 물고기
	
	static int[] eaten;//최대한 많이 먹을 수 있는 이동 방향
	static boolean[][] visited;//eaten구할 때 쓸 visited 배열
	static int Max;
	
	static int fishCnt;
	static int[][] fishCntMap;
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine()," ");
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		fishCnt = M;//물고기 수 
		
		origin = new int[4][4][8];
		smell = new int[4][4];
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken())-1;
			origin[r][c][d]++;
		}
		
		// 초기 상어 위치
		st = new StringTokenizer(br.readLine());
		shark = new Shark(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
		
		for(int i=0;i<S;i++) {
			//1. 물고기 옮기고 temp에 저장
			moveFish();
			//2. 가장 많이 먹을 수 있는 방법 저장
			eaten = new int[3];
			Max = Integer.MIN_VALUE;
			findEating();
			
			//3. 먹은 곳 삭제 + 냄새 남기기
			delete_LeaveSmell();
			
			//4. 배열 합치기
			addAll();
			fishCnt = fishCnt*2 - Max;
		}
		System.out.println(fishCnt);
	}

	private static void moveFish() {
		fishCntMap = new int[4][4];
		temp = new int[4][4][8];
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				for(int d = 0; d<8;d++) {
					if(origin[i][j][d] == 0) {
						//한마리도 없는 경우
						continue;
					}else {
						int nd = d;
						while(true) {
							int nr = i + fish_dr[nd];
							int nc = j + fish_dc[nd];
							
							if(!isRange(nr, nc) || smell[nr][nc] != 0 || (shark.r == nr  && shark.c == nc)) {
								nd--;
								if(nd==-1) {
									nd = 7;
								}
								if(nd == d) {
									temp[i][j][d] += origin[i][j][d];
									fishCntMap[i][j] += origin[i][j][d];
									break;
								}
							}else {
								temp[nr][nc][nd] += origin[i][j][d];
								fishCntMap[nr][nc] += origin[i][j][d];
								break;
								
							}
							
						}
					}
				}
			}
		}
	}
	
	private static boolean isRange(int r, int c) {
		return r>=0 && c>=0 && r<4 && c<4;
	}
	
	private static void findEating() {
		int r = shark.r;
		int c = shark.c;
		
		int sum = 0;
		
		for(int i=0;i<4;i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(!isRange(nr, nc))
				continue;
			
			sum += fishCntMap[nr][nc];
			
			for(int j=0;j<4;j++) {
				int nr2 = nr + dr[j];
				int nc2 = nc + dc[j];
				
				if(!isRange(nr2, nc2))
					continue;
				
				sum += fishCntMap[nr2][nc2];
				
				for(int k=0;k<4;k++) {
					int nr3 = nr2 + dr[k];
					int nc3 = nc2 + dc[k];
					if(!isRange(nr3, nc3))
						continue;
					
					if(nr != nr3 || nc != nc3)
						sum += fishCntMap[nr3][nc3];
					
					if(Max<sum) {
						Max = sum;
						eaten[0] = i;
						eaten[1] = j;
						eaten[2] = k;
					}

					if(nr != nr3 || nc != nc3)
						sum -= fishCntMap[nr3][nc3];
					
				}
				sum -= fishCntMap[nr2][nc2];
			}
			sum -= fishCntMap[nr][nc];
		}
	}

	private static void delete_LeaveSmell() {
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(smell[i][j] != 0)
					smell[i][j]--;
			}
		}
		
		int nr = shark.r;
		int nc = shark.c;
		for(int i=0;i<3;i++) {
			int d = eaten[i];
			
			nr += dr[d];
			nc += dc[d];
			
			//물고기가 있는 경우
			if(fishCntMap[nr][nc] != 0) {
				temp[nr][nc] = new int[8];//먹기
				fishCntMap[nr][nc] = 0;
				smell[nr][nc] = 2;//냄새 남기기
			}
		}
		shark = new Shark(nr, nc);
	}
	
	private static void addAll() {
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(fishCntMap[i][j] != 0) {
					for(int d = 0;d<8;d++) {
						origin[i][j][d] += temp[i][j][d];
					}
				}
			}
		}
	}
}