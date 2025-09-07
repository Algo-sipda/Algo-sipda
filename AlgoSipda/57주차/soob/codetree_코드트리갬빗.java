import java.io.*;
import java.util.*;

public class Main {

    static class P {
        int y, x;
        P(int y, int x) { this.y = y; this.x = x; }
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            P p = (P) o;
            return y == p.y && x == p.x;
        }
        @Override public int hashCode() { return Objects.hash(y, x); }
    }

    static class Grid {
        List<List<Integer>> cell;
        List<P> wPos, bPos;
        List<Integer> wHp, bHp;

        static Grid cloneOf(Grid g) {
            Grid r = new Grid();
            r.cell = new ArrayList<>();
            r.wPos = new ArrayList<>();
            r.bPos = new ArrayList<>();
            r.wHp = new ArrayList<>();
            r.bHp = new ArrayList<>();
            for (List<Integer> row : g.cell) r.cell.add(new ArrayList<>(row));
            for (P p : g.wPos) r.wPos.add(new P(p.y, p.x));
            for (P p : g.bPos) r.bPos.add(new P(p.y, p.x));
            for (int v : g.wHp) r.wHp.add(v);
            for (int v : g.bHp) r.bHp.add(v);
            return r;
        }

        void setPos(int id, P p) { if (id > 0) wPos.set(Math.abs(id), p); else bPos.set(Math.abs(id), p); }
        P posOf(int id) { return (id > 0 ? wPos : bPos).get(Math.abs(id)); }
        P posOf(boolean white, int i) { return posOf(white ? i : -i); }

        void setHp(int id, int v) { if (id > 0) wHp.set(Math.abs(id), v); else bHp.set(Math.abs(id), v); }
        int hpOf(int id) { return (id > 0 ? wHp : bHp).get(Math.abs(id)); }
        void setHp(boolean white, int i, int v) { setHp(white ? i : -i, v); }
        int hpOf(boolean white, int i) { return hpOf(white ? i : -i); }

        void dump() {
            StringBuilder sb = new StringBuilder();
            for (int y = 1; y <= H; y++) {
                for (int x = 1; x <= W; x++) {
                    int v = cell.get(y).get(x);
                    if (v == 0) sb.append('.');
                    else if (1 <= v && v <= N) sb.append("K").append(v);
                    else if (1 <= -v && -v <= N) sb.append("k").append(-v);
                    else if (v == N + 1) sb.append(y == 1 ? 'Q' : 'P');
                    else if (v == -(N + 1)) sb.append(y == H ? 'q' : 'p');
                    if (x < W) sb.append(' ');
                }
                sb.append('\n');
            }
            System.out.print(sb.toString());
        }
    }

    static class GS {
        Grid g;
        boolean cont;
        GS(Grid g, boolean cont) { this.g = g; this.cont = cont; }
    }

    static List<List<Integer>> prefW, prefB;
    static List<Integer> turnOrder;

    static int H, W, N, K, dmgSelf, dmgOpp;
    static final int[] ay = {-1,-1,0,1,1,1,0,-1};
    static final int[] ax = {0,1,1,1,0,-1,-1,-1};
    static final int BIG = 0x3f3f3f3f;

    static BufferedReader br;
    static StringTokenizer st;

    static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
        return st.nextToken();
    }
    static int nextInt() throws IOException { return Integer.parseInt(next()); }

    static int sq(int v) { return v * v; }
    static int dist(P a, P b) { return sq(a.y - b.y) + sq(a.x - b.x); }
    static boolean inside(int y, int x) { return 1 <= y && y <= H && 1 <= x && x <= W; }
    static int needPromDist(boolean white, int y) { return white ? y - 1 : H - y; }

    static void afterPromote(Grid g, boolean white) {
        P q = g.posOf(white, N + 1);
        for (int i = 1; i <= N; i++) {
            int hp = g.hpOf(!white, i);
            if (hp > 0) {
                P t = g.posOf(!white, i);
                hp -= dist(q, t);
                g.setHp(!white, i, hp);
                if (hp <= 0) g.cell.get(t.y).set(t.x, 0);
            }
        }
    }

    static boolean slideKing(Grid g, int id, int dir) {
        int y = g.posOf(id).y;
        int x = g.posOf(id).x;
        if (!inside(y + ay[dir], x + ax[dir])) return false;

        g.cell.get(y).set(x, 0);
        y += ay[dir]; x += ax[dir];

        while (true) {
            int t = g.cell.get(y).get(x);
            if (!inside(y, x)) {
                if (Math.abs(id) == N + 1) {
                    int prev = g.cell.get(y - ay[dir]).get(x - ax[dir]);
                    g.setHp(prev, 0);
                    g.cell.get(y - ay[dir]).set(x - ax[dir], id);
                    g.setPos(id, new P(y - ay[dir], x - ax[dir]));
                    return true;
                } else {
                    g.setHp(id, 0);
                    g.setPos(id, new P(y, x));
                    return true;
                }
            } else if (t == 0) {
                g.cell.get(y).set(x, id);
                g.setPos(id, new P(y, x));
                return true;
            } else if (Math.abs(id) <= N) {
                if (((N + 1) * (id > 0 ? -1 : 1)) == t) return false;
                else if (Math.abs(t) <= N) {
                    int hp = g.hpOf(t);
                    hp -= ((id > 0) == (t > 0) ? dmgSelf : dmgOpp);
                    g.setHp(t, hp);
                    g.cell.get(y).set(x, id);
                    g.setPos(id, new P(y, x));
                    if (hp <= 0) return true;
                    id = t; y += ay[dir]; x += ax[dir];
                } else {
                    g.cell.get(y).set(x, id);
                    g.setPos(id, new P(y, x));
                    id = t; y += ay[dir]; x += ax[dir];
                }
            } else {
                if (Math.abs(t) == N + 1) {
                    int prev = g.cell.get(y - ay[dir]).get(x - ax[dir]);
                    g.setHp(prev, 0);
                    g.cell.get(y - ay[dir]).set(x - ax[dir], id);
                    g.setPos(id, new P(y - ay[dir], x - ax[dir]));
                    return true;
                } else {
                    g.setHp(t, 0);
                    g.cell.get(y).set(x, id);
                    g.setPos(id, new P(y, x));
                    return true;
                }
            }
        }
    }

    static void flood(int y, int x, Grid g, int id, Set<P> acc) {
        if (!inside(y, x) || g.cell.get(y).get(x) == 0 || (id > 0) != (g.cell.get(y).get(x) > 0)) return;
        P cur = new P(y, x);
        if (acc.contains(cur)) return;
        acc.add(cur);
        for (int d = 0; d < 8; d++) flood(y + ay[d], x + ax[d], g, id, acc);
    }

    static GS step(Grid g, int id) {
        if (Math.abs(id) <= N) {
            if (g.hpOf(id) <= 0) return new GS(g, true);
            List<GS> cand = new ArrayList<>(8);
            for (int d = 0; d < 8; d++) {
                Grid ng = Grid.cloneOf(g);
                boolean ok = slideKing(ng, id, d);
                cand.add(new GS(ng, ok));
            }
            int bestD = -1, bestDist = BIG, bestX = BIG;
            for (int d : (id > 0 ? prefW : prefB).get(Math.abs(id))) if (cand.get(d).cont) {
                Grid ng = cand.get(d).g;
                int pd = needPromDist(id > 0, ng.posOf(id > 0, N + 1).y);
                int px = ng.posOf(id > 0, N + 1).x;
                if (pd < bestDist || (pd == bestDist && px < bestX)) { bestD = d; bestDist = pd; bestX = px; }
            }
            g = cand.get(bestD).g;
        } else {
            Set<P> comp = new HashSet<>();
            int sy = g.posOf(id).y, sx = g.posOf(id).x;
            flood(sy, sx, g, id, comp);

            if (comp.size() > 4) {
                P best = new P(-1, -1);
                int bd = BIG, bs = BIG;
                for (P v : comp) {
                    int pd = needPromDist(id > 0, v.y);
                    int sum = 0;
                    for (int i = 1; i <= N + 1; i++) {
                        if (i == N + 1 || g.hpOf(id > 0, i) > 0) sum += dist(v, g.posOf(id > 0, i));
                    }
                    if (pd < bd || (pd == bd && sum < bs)) { best = v; bd = pd; bs = sum; }
                }
                int k = g.cell.get(best.y).get(best.x);
                if (id != k) {
                    int ky = g.posOf(k).y, kx = g.posOf(k).x;
                    int tmp = g.cell.get(sy).get(sx);
                    g.cell.get(sy).set(sx, g.cell.get(ky).get(kx));
                    g.cell.get(ky).set(kx, tmp);
                    P t2 = g.posOf(id);
                    g.setPos(id, g.posOf(k));
                    g.setPos(k, t2);
                }
            } else {
                int bestD = -1, bestVal = BIG;
                for (int d : (id > 0 ? prefW : prefB).get(N + 1)) {
                    int ny = g.posOf(id).y + ay[d];
                    int nx = g.posOf(id).x + ax[d];
                    if (inside(ny, nx) && g.cell.get(ny).get(nx) == 0) {
                        int val = 0;
                        for (int i = 1; i <= N; i++) if (g.hpOf(id > 0, i) > 0)
                            val += dist(new P(ny, nx), g.posOf(id > 0, i));
                        if (val < bestVal) { bestVal = val; bestD = d; }
                    }
                }
                if (bestD >= 0) {
                    P p = g.posOf(id);
                    g.cell.get(p.y).set(p.x, 0);
                    p.y += ay[bestD];
                    p.x += ax[bestD];
                    g.cell.get(p.y).set(p.x, id);
                }
            }
        }
        return new GS(g, needPromDist(id > 0, g.posOf(id > 0, N + 1).y) > 0);
    }

    static int parseId(String s) {
        char c = s.charAt(0);
        if (c == 'P') return N + 1;
        if (c == 'p') return -(N + 1);
        int v = Integer.parseInt(s.substring(1));
        if (c == 'K') return v;
        if (c == 'k') return -v;
        return 0;
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        H = nextInt(); W = nextInt(); N = nextInt(); K = nextInt(); dmgSelf = nextInt(); dmgOpp = nextInt();

        Grid g = new Grid();
        g.cell = new ArrayList<>();
        for (int y = 0; y < H + 2; y++) {
            g.cell.add(new ArrayList<>());
            for (int x = 0; x < W + 2; x++) g.cell.get(y).add(0);
        }

        g.wPos = new ArrayList<>();
        g.bPos = new ArrayList<>();
        for (int i = 0; i < N + 2; i++) { g.wPos.add(new P(0, 0)); g.bPos.add(new P(0, 0)); }

        g.wHp = new ArrayList<>();
        g.bHp = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) { g.wHp.add(0); g.bHp.add(0); }

        turnOrder = new ArrayList<>();
        for (int i = 0; i < (N + 1) << 1; i++) {
            int id = parseId(next());
            int y = nextInt(), x = nextInt();
            if (id > 0) g.wPos.set(Math.abs(id), new P(y, x)); else g.bPos.set(Math.abs(id), new P(y, x));
            g.cell.get(y).set(x, id);
            turnOrder.add(id);
        }

        prefW = new ArrayList<>();
        prefW.add(new ArrayList<>());
        for (int i = 1; i < N + 2; i++) {
            prefW.add(new ArrayList<>());
            String s = next();
            for (int j = 0; j < 8; j++) prefW.get(i).add(s.charAt(j) - '0');
        }

        prefB = new ArrayList<>();
        prefB.add(new ArrayList<>());
        for (int i = 1; i < N + 2; i++) {
            prefB.add(new ArrayList<>());
            String s = next();
            for (int j = 0; j < 8; j++) prefB.get(i).add(s.charAt(j) - '0');
        }

        for (int i = 1; i <= N; i++) g.wHp.set(i, nextInt());
        for (int i = 1; i <= N; i++) g.bHp.set(i, nextInt());

        while (K-- > 0) {
            boolean stop = false;
            for (int id : turnOrder) {
                GS ns = step(g, id);
                g = ns.g;
                if (!ns.cont) {
                    afterPromote(g, id > 0);
                    stop = true;
                    break;
                }
            }
            if (stop) break;
        }

        g.dump();
    }
}

