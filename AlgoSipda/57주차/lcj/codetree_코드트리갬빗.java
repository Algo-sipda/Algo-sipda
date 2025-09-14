import java.util.*;

// 못 풀겠어요.. 풀이 해설

public class Main {
    static class Pair {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Pair pair = (Pair) obj;
            return first == pair.first && second == pair.second;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }

    // 체스판의 상태를 나타내는 클래스
    static class Board {
        // 체스판 상황
        List<List<Integer>> A;

        // 흰색 기물, 검은색 기물의 위치
        List<Pair> WV, BV;

        // 흰색 기물, 검은색 기물의 체력
        List<Integer> WHP, BHP;

        // Deep copy
        static Board newInstance(Board board) {
            Board result = new Board();
            result.A = new ArrayList<>();
            result.WV = new ArrayList<>();
            result.BV = new ArrayList<>();
            result.WHP = new ArrayList<>();
            result.BHP = new ArrayList<>();

            for (List<Integer> row : board.A) {
                List<Integer> newRow = new ArrayList<>(row);
                result.A.add(newRow);
            }

            for (Pair pair : board.WV) {
                result.WV.add(new Pair(pair.first, pair.second));
            }

            for (Pair pair : board.BV) {
                result.BV.add(new Pair(pair.first, pair.second));
            }

            for (int value : board.WHP) {
                result.WHP.add(value);
            }

            for (int value : board.BHP) {
                result.BHP.add(value);
            }
            return result;
        }

        // 기물 정보 수정
        void setV(int o, Pair v) {
            if (0 < o) {
                WV.set(Math.abs(o), v);
            } else {
                BV.set(Math.abs(o), v);
            }
        }

        // 기물 정보 접근
        Pair V(int o) {
            return (0 < o ? WV : BV).get(Math.abs(o));
        }

        // 기물 정보 접근
        Pair V(boolean c, int i) {
            return V(c ? i : -i);
        }

        // 체력 정보 수정
        void setHP(int o, int v) {
            if (0 < o) {
                WHP.set(Math.abs(o), v);
            } else {
                BHP.set(Math.abs(o), v);
            }
        }

        // 체력 정보 접근
        int HP(int o) {
            return (0 < o ? WHP : BHP).get(Math.abs(o));
        }

        // 체력 정보 수정
        void setHP(boolean c, int i, int v) {
            setHP(c ? i : -i, v);
        }

        // 체력 정보 접근
        int HP(boolean c, int i) {
            return HP(c ? i : -i);
        }

        // 정답 출력
        void prt() {
            for (int i = 1; i <= H; i++) {
                for (int j = 1; j <= W; j++) {
                    int v = A.get(i).get(j);
                    if (v == 0) {
                        System.out.print('.');
                    } else if (1 <= v && v <= N) {
                        System.out.print("K" + String.valueOf(v));
                    } else if (1 <= -v && -v <= N) {
                        System.out.print("k" + String.valueOf(-v));
                    } else if (v == N + 1) {
                        System.out.print(1 == i ? 'Q' : 'P');
                    } else if (v == -(N + 1)) {
                        System.out.print(i == H ? 'q' : 'p');
                    }

                    if (j < W) {
                        System.out.print(' ');
                    }
                }
                System.out.println();
            }
        }
    }

    static class PairBoard {
        Board first;
        boolean second;

        PairBoard(Board first, boolean second) {
            this.first = first;
            this.second = second;
        }
    }

    // 각 기물의 방향 우선순위
    static List<List<Integer>> WDO, BDO;

    // 기물이 움직이는 순서
    static List<Integer> O;

    static int H, W, N, K, CA, CB;
    static final int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
    static final int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static final int INF = 0x3f3f3f3f;

    static int pw(int x) {
        return x * x;
    }

    static int getDist(Pair a, Pair b) {
        return pw(a.first - b.first) + pw(a.second - b.second);
    }

    static boolean isin(int y, int x) {
        return 1 <= y && y <= H && 1 <= x && x <= W;
    }

    // 승진을 하기 위해 필요한 거리를 반환
    // c: 색깔, y: y좌표
    static int promoDist(boolean c, int y) {
        return c ? y - 1 : H - y;
    }

    // 폰이 승진한 경우에 대한 처리
    static void promotePawn(Board board, boolean c) {
        Pair queen = board.V(c, N + 1);
        for (int i = 1; i <= N; i++) {
            int hp = board.HP(!c, i);
            if (0 < hp) {
                Pair v = board.V(!c, i);
                hp -= getDist(queen, v);
                board.setHP(!c, i, hp);
                if (hp <= 0) {
                    board.A.get(v.first).set(v.second, 0);
                }
            }
        }
    }

    // 킹을 움직이는 함수
    // dr 방향으로 움직이는 경우에 대한 처리
    // 움직이는 경우 true, 움직이지 않는 경우 false를 반환
    static boolean moveKing(Board board, int o, int dr) {
        int y = board.V(o).first;
        int x = board.V(o).second;

        if (!isin(y + dy[dr], x + dx[dr])) {
            return false;
        }

        board.A.get(y).set(x, 0);
        y += dy[dr];
        x += dx[dr];

        // 밀치는 모든 경우를 처리
        while (true) {
            int t = board.A.get(y).get(x);
            if (!isin(y, x)) {
                if (N + 1 == Math.abs(o)) {
                    int pv = board.A.get(y - dy[dr]).get(x - dx[dr]);
                    board.setHP(pv, 0);
                    board.A.get(y - dy[dr]).set(x - dx[dr], o);
                    board.setV(o, new Pair(y - dy[dr], x - dx[dr]));
                    return true;
                } else {
                    board.setHP(o, 0);
                    board.setV(o, new Pair(y, x));
                    return true;
                }
            } else if (t == 0) {
                board.A.get(y).set(x, o);
                board.setV(o, new Pair(y, x));
                return true;
            } else if (Math.abs(o) <= N) {
                if (((N + 1) * (0 < o ? -1 : 1)) == t) {
                    return false;
                } else if (Math.abs(t) <= N) {
                    int hp = board.HP(t);
                    hp -= ((0 < o) == (0 < t) ? CA : CB);
                    board.setHP(t, hp);
                    board.A.get(y).set(x, o);
                    board.setV(o, new Pair(y, x));
                    if (hp <= 0) {
                        return true;
                    }
                    o = t;
                    y += dy[dr];
                    x += dx[dr];
                } else {
                    board.A.get(y).set(x, o);
                    board.setV(o, new Pair(y, x));
                    o = t;
                    y += dy[dr];
                    x += dx[dr];
                }
            } else {
                if (N + 1 == Math.abs(t)) {
                    int pv = board.A.get(y - dy[dr]).get(x - dx[dr]);
                    board.setHP(pv, 0);
                    board.A.get(y - dy[dr]).set(x - dx[dr], o);
                    board.setV(o, new Pair(y - dy[dr], x - dx[dr]));
                    return true;
                } else {
                    board.setHP(t, 0);
                    board.A.get(y).set(x, o);
                    board.setV(o, new Pair(y, x));
                    return true;
                }
            }
        }
    }

    static void dfs(int y, int x, Board board, int o, Set<Pair> V) {
        if (!isin(y, x) || board.A.get(y).get(x) == 0 || (0 < o) != (0 < board.A.get(y).get(x)))
            return;
        Pair nyx = new Pair(y, x);
        if (V.contains(nyx))
            return;
        V.add(nyx);
        for (int dr = 0; dr < 8; dr++)
            dfs(y + dy[dr], x + dx[dr], board, o, V);
    }

    // 기물 o를 움직이는 함수
    static PairBoard run(Board board, int o) {
        // 킹 이동
        if (Math.abs(o) <= N) {
            // 체력이 0 이하인 경우, 파괴된 기물이므로 이동하지 않음
            if (board.HP(o) <= 0) {
                return new PairBoard(board, true);
            }

            // 8방향에 대해서 움직이는 시뮬레이션 진행
            List<PairBoard> V = new ArrayList<>(8);
            for (int i = 0; i < 8; i++) {
                V.add(new PairBoard(Board.newInstance(board), false));
                V.get(i).second = moveKing(V.get(i).first, o, i);
            }

            // 움직일 수 있는 방향 중에서 가장 우선순위가 높은 방향을 선택
            int ret_dr = -1, ret_d = INF, ret_x = INF;
            for (int dr : (0 < o ? WDO : BDO).get(Math.abs(o)))
                if (V.get(dr).second) {
                    Board nxt = V.get(dr).first;
                    int dst = promoDist(0 < o, nxt.V(0 < o, N + 1).first);
                    int x = nxt.V(0 < o, N + 1).second;

                    if (dst < ret_d || (dst == ret_d && x < ret_x)) {
                        ret_dr = dr;
                        ret_d = dst;
                        ret_x = x;
                    }
                }
            board = V.get(ret_dr).first;

        } else {
            // 폰 이동
            Set<Pair> V = new HashSet<>();

            int oy = board.V(o).first;
            int ox = board.V(o).second;

            // bfs를 통해서 그룹을 구함
            dfs(oy, ox, board, o, V);

            if (V.size() > 4) {
                Pair p = new Pair(-1, -1);
                int d = INF;
                int s = INF;

                for (Pair v : V) {
                    int dst = promoDist(o > 0, v.first);
                    int sum = 0;

                    for (int i = 1; i <= N + 1; i++) {
                        if (N + 1 == i || board.HP(o > 0, i) > 0) {
                            sum += getDist(v, board.V(o > 0, i));
                        }
                    }

                    if (dst < d || (dst == d && sum < s)) {
                        p = v;
                        d = dst;
                        s = sum;
                    }
                }

                int k = board.A.get(p.first).get(p.second);

                if (o != k) {
                    int ky = board.V(k).first;
                    int kx = board.V(k).second;

                    int tmp = board.A.get(oy).get(ox);
                    board.A.get(oy).set(ox, board.A.get(ky).get(kx));
                    board.A.get(ky).set(kx, tmp);
                    Pair tmp2 = board.V(o);
                    board.setV(o, board.V(k));
                    board.setV(k, tmp2);
                }
            } else {
                int ret_dr = -1;
                int ret_d = INF;

                for (int dr : (o > 0 ? WDO : BDO).get(N + 1)) {
                    int nxty = board.V(o).first + dy[dr];
                    int nxtx = board.V(o).second + dx[dr];

                    if (isin(nxty, nxtx) && board.A.get(nxty).get(nxtx) == 0) {
                        int dst = 0;

                        for (int i = 1; i <= N; i++) {
                            if (board.HP(o > 0, i) > 0) {
                                dst += getDist(new Pair(nxty, nxtx), board.V(o > 0, i));
                            }
                        }

                        if (dst < ret_d) {
                            ret_dr = dr;
                            ret_d = dst;
                        }
                    }
                }

                if (ret_dr >= 0) {
                    Pair p = board.V(o);
                    board.A.get(p.first).set(p.second, 0);
                    p.first += dy[ret_dr];
                    p.second += dx[ret_dr];
                    board.A.get(p.first).set(p.second, o);
                }
            }
        }

        return new PairBoard(board, promoDist(o > 0, board.V(0 < o, N + 1).first) > 0);
    }

    // 기물을 읽는 함수
    static int readPiece(String s) {
        char p = s.charAt(0);

        if ('P' == p)
            return N + 1;
        if ('p' == p)
            return -(N + 1);

        int v = Integer.parseInt(s.substring(1));

        if ('K' == p)
            return v;
        if ('k' == p)
            return -v;

        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        H = sc.nextInt();
        W = sc.nextInt();
        N = sc.nextInt();
        K = sc.nextInt();
        CA = sc.nextInt();
        CB = sc.nextInt();

        Board board = new Board();

        board.A = new ArrayList<>();
        for (int i = 0; i < H + 2; i++) {
            board.A.add(new ArrayList<>());
            for (int j = 0; j < W + 2; j++) {
                board.A.get(i).add(0);
            }
        }

        board.WV = new ArrayList<>();
        board.BV = new ArrayList<>();
        for (int i = 0; i < N + 2; i++) {
            board.WV.add(new Pair(0, 0));
            board.BV.add(new Pair(0, 0));
        }

        board.WHP = new ArrayList<>();
        board.BHP = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            board.WHP.add(0);
            board.BHP.add(0);
        }

        List<Integer> O = new ArrayList<>();
        for (int i = 0; i < (N + 1) << 1; i++) {
            int v = readPiece(sc.next());

            int y = sc.nextInt();
            int x = sc.nextInt();

            if (v > 0)
                board.WV.set(Math.abs(v), new Pair(y, x));
            else
                board.BV.set(Math.abs(v), new Pair(y, x));

            board.A.get(y).set(x, v);

            O.add(v);
        }

        WDO = new ArrayList<>();
        WDO.add(new ArrayList<>());
        for (int i = 1; i < N + 2; i++) {
            WDO.add(new ArrayList<>());
            String s = sc.next();
            for (int j = 0; j < 8; j++) {
                WDO.get(i).add(s.charAt(j) - '0');
            }
        }

        BDO = new ArrayList<>();
        BDO.add(new ArrayList<>());
        for (int i = 1; i < N + 2; i++) {
            BDO.add(new ArrayList<>());
            String s = sc.next();
            for (int j = 0; j < 8; j++) {
                BDO.get(i).add(s.charAt(j) - '0');
            }
        }

        for (int i = 1; i <= N; i++) {
            int v = sc.nextInt();
            board.WHP.set(i, v);
        }
        for (int i = 1; i <= N; i++) {
            int v = sc.nextInt();
            board.BHP.set(i, v);
        }

        while (K-- > 0) {
            boolean flag = false;
            for (int o : O) {
                PairBoard retpair = run(board, o);
                board = retpair.first;
                boolean ret = retpair.second;

                if (!ret) {
                    promotePawn(board, o > 0);
                    flag = true;
                    break;
                }
            }
            if (flag)
                break;
        }

        board.prt();
    }
}
