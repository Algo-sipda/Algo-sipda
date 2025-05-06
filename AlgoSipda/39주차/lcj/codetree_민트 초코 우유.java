import java.io.*;
import java.util.*;

public class Main {

    static class Member implements Comparable<Member> {
        int r, c, value;
        Set<Character> hs;

        public Member(int r, int c) {
            this.r = r;
            this.c = c;
            hs = new HashSet<>();
        }

        public int getPower() {
            int power = this.value - 1;
            this.value = 1;
            return power;
        }

        @Override
        public int compareTo(Member m) {
            if(this.value != m.value) {
                return Integer.compare(m.value, this.value);
            }
            if(this.r != m.r) {
                return Integer.compare(this.r, m.r);
            }
            return Integer.compare(this.c, m.c);
        }
    }

    static class Group implements Comparable<Group> {
        List<Member> members = new ArrayList<>();
        int typeCode, typeCodeSize;
        Member leader;

        public void setLeader(Member member) {
            this.leader = member;
        }

        public void setTypeCodeSize(int typeCodeSize) {
            this.typeCodeSize = typeCodeSize;
        }

        public void add(Member m) {
            members.add(m);
        }

        public void setTypeCode(int typeCode) {
            this.typeCode = typeCode;
        }

        public Member getLeader() {
            return Collections.min(members);
        }

        @Override
        public int compareTo(Group g) {
            return this.getLeader().compareTo(g.getLeader());
        }
    }

    static int N, T;
    static Member[][] map;
    static boolean[][] isGrouped, isDamaged;
    static StringBuilder sb = new StringBuilder();

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new Member[N+1][N+1];
        resetDay();

        for(int i=1;i<=N;i++) {
            String str = br.readLine();

            for(int j=1;j<=N;j++) {
                map[i][j] = new Member(i, j);
                map[i][j].hs.add(str.charAt(j-1));
            }
        }

        for(int i=1;i<=N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++) {
                map[i][j].value = Integer.parseInt(st.nextToken());
            }
        }

        for(int t=0;t<T; t++) {
            morning();
            List<Group> groups = afternoon();
            evening(groups);
            result();
            resetDay();
        }

        System.out.print(sb);
    }

    public static void morning() {
        for(int i=1;i<=N;i++) {
            for(int j=1; j<=N;j++) {
                map[i][j].value++;
            }
        }
    }

    public static List<Group> afternoon() {
        List<Group> groups = new ArrayList<>();

        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                if(!isGrouped[i][j]) groups.add(findGroup(i, j));
            }
        }

        for(Group g:groups) {
            
            Member leader = g.getLeader(); // 단 한 번만 호출
            g.setLeader(leader);           // 리더를 고정
            int type = findWho(leader);   // 리더로 타입 결정
            g.setTypeCode(type);
            g.setTypeCodeSize(leader.hs.size());

            for(Member m : g.members) {
                if(leader.equals(m)) continue;

                m.value--;
                leader.value++;
            }
        }

        return groups;
    }

    public static Group findGroup(int sr, int sc) {
        Group curG = new Group();
        Deque<Member> dq = new ArrayDeque<>();

        Member sm = map[sr][sc];
        curG.members.add(sm);
        isGrouped[sr][sc] = true;

        dq.add(sm);

        while(!dq.isEmpty()) {
            Member curM = dq.poll();

            for(int d = 0; d<4;d++) {
                int nr = curM.r + dr[d];
                int nc = curM.c + dc[d];

                if(!isRange(nr, nc)) continue;
                if(isGrouped[nr][nc]) continue;
                if(!checkSame(curM.r, curM.c, nr, nc)) continue;

                Member nm = map[nr][nc];
                isGrouped[nr][nc] = true;
                curG.members.add(nm);
                dq.add(nm);
            }
        }

        return curG;
    }

    public static boolean isRange(int r, int c) {
        return 0<r && r<=N && 0<c && c<=N;
    }

    public static boolean checkSame(int r, int c, int nr, int nc) {
        if(map[r][c].hs.size() != map[nr][nc].hs.size()) {
            return false;
        }

        return map[r][c].hs.containsAll(map[nr][nc].hs);
    }

    public static int findWho(Member member) {
        if(member.hs.size() == 3) { // 민트 초코 우유
            return 1;
        }
        else if(member.hs.size() == 2) {
            if(member.hs.contains('T') && member.hs.contains('C'))
                return 2;
            if(member.hs.contains('M') && member.hs.contains('T'))
                return 3;
            if(member.hs.contains('C') && member.hs.contains('M'))
                return 4;
        }
        else {
            if(member.hs.contains('M'))
                return 5;
            if(member.hs.contains('C'))
                return 6;
            if(member.hs.contains('T'))
                return 7;
        }
        return 0;
    }

    public static void evening(List<Group> groups) {
        groups.sort((g1, g2) -> {
            if(g1.typeCodeSize != g2.typeCodeSize) 
                return Integer.compare(g1.typeCodeSize, g2.typeCodeSize);
            return g1.compareTo(g2);
        });

        for(Group g:groups) {
            Member leader = g.leader;

            if(isDamaged[leader.r][leader.c]) continue;

            int power = leader.getPower();
            int dir = (power + 1) % 4;

            int nr = leader.r;
            int nc = leader.c;
            int curType = findWho(leader);

            while(true) {
                nr += dr[dir];
                nc += dc[dir];

                if(!isRange(nr, nc)) break;

                Member target = map[nr][nc];
                int nextType = findWho(target);

                if(curType == nextType) continue;

                if (power > target.value) {
                    target.value++;
                    power -= target.value;
                    target.hs.clear();
                    target.hs.addAll(leader.hs);
                    isDamaged[target.r][target.c] = true;
                } else {
                    target.hs.addAll(leader.hs); // 병합
                    target.value += power;
                    isDamaged[target.r][target.c] = true;
                    power = 0;
                }

                if(power == 0) break;
            }

        }
    }

    public static void resetDay() {
        isDamaged = new boolean[N+1][N+1];
        isGrouped = new boolean[N+1][N+1];
    }

    public static void result() {
        int[] answer = new int[8];

        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                int type = findWho(map[i][j]);
                answer[type] += map[i][j].value;
            }
        }

        for(int i=1;i<answer.length;i++) {
            sb.append(answer[i]+" ");
        }
        sb.append("\n");
    }
}