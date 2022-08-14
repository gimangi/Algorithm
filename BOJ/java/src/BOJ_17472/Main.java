package BOJ_17472;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int[] dy = {-1, 0, 0, 1};
    static final int[] dx = {0, -1, 1, 0};

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        boolean[][] map = new boolean[N][M];

        // input
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int v = Integer.parseInt(st.nextToken());
                map[i][j] = v == 1;
            }
        }

        boolean[][] visitedIsland = new boolean[N][M];
        ArrayList<Island> islands = new ArrayList<>();

        // bfs -> island
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (map[i][j] && !visitedIsland[i][j])
                    islands.add(bfsIsland(map, visitedIsland, new Position(i, j)));

        ArrayList<Edge> edges = new ArrayList<>();

        // bridge
        for (int i = 0; i < islands.size(); i++) {
            for (int j = i; j < islands.size(); j++) {
                if (i != j) {
                    Edge edge = shortBridge(map, islands.get(i), islands.get(j));
                    if (edge != null)
                        edges.add(edge);
                }
            }
        }

        // kruskal -> mst
        Collections.sort(edges, (e1, e2) -> e1.weight - e2.weight);
        int[] parent = new int[islands.size()];
        for (int i = 0; i < parent.length; i++)
            parent[i] = i;
        int cnt = 0;
        int sum = 0;

        for (Edge edge : edges) {
            if (find(parent, edge.start) != find(parent, edge.end)) {
                union(parent, edge.start, edge.end);
                sum += edge.weight;

                if (++cnt == islands.size() - 1)
                    break;
            }
        }

        // check mst
        for (int i = 0; i < parent.length; i++) {
            if (find(parent, i) != 0) {
                sum = -1;
                break;
            }
        }
        System.out.println(sum);

    }

    static int find(int[] parent, int i) {
        if (parent[i] == i)
            return i;
        return find(parent, parent[i]);
    }

    static void union(int[] parent, int a, int b) {
        int parentA = find(parent, a);
        int parentB = find(parent, b);

        if (parentA < parentB)
            parent[parentB] = parentA;
        else
            parent[parentA] = parentB;
    }

    static Island bfsIsland(final boolean[][] map, final boolean[][] visited, final Position start) {
        Island island = new Island();
        Queue<Position> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            Position cur = queue.poll();
            visited[cur.y][cur.x] = true;
            island.area.add(cur);

            for (int i = 0; i < dy.length; i++) {
                int nextY = cur.y + dy[i];
                int nextX = cur.x + dx[i];
                if (nextX >= 0 && nextX < M && nextY >= 0 && nextY < N) {
                    if (!visited[nextY][nextX] && map[nextY][nextX]) {
                        Position next = new Position(nextY, nextX);
                        queue.offer(next);
                    }
                }
            }
        }

        return island;
    }

    static Edge shortBridge(final boolean[][] map, Island start, Island end) {
        PriorityQueue<Edge> edges = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
        for (int i = 0; i < start.area.size(); i++) {
            for (int j = 0; j < end.area.size(); j++) {
                Position s = start.area.get(i);
                Position e = end.area.get(j);
                if (s.y == e.y) {
                    int diff = Math.abs(s.x - e.x) - 1;
                    if (diff >= 2) {
                        int min = Math.min(s.x, e.x);
                        boolean possible = true;
                        for (int t = 1; t <= diff; t++) {
                            if (map[s.y][min + t]) {
                                possible = false;
                                break;
                            }
                        }
                        if (possible)
                            edges.offer(new Edge(start.num, end.num, diff));
                    }
                } else if (s.x == e.x) {
                    int diff = Math.abs(s.y - e.y) - 1;
                    if (diff >= 2) {
                        int min = Math.min(s.y, e.y);
                        boolean possible = true;
                        for (int t = 1; t <= diff; t++) {
                            if (map[min + t][s.x]) {
                                possible = false;
                                break;
                            }
                        }
                        if (possible)
                            edges.offer(new Edge(start.num, end.num, diff));
                    }
                }
            }
        }
        if (!edges.isEmpty())
            return edges.peek();
        else
            return null;
    }

}

class Position {
    int y;
    int x;

    Position(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class Island {

    static int numOfIslands = 0;
    int num;
    ArrayList<Position> area = new ArrayList<>();

    Island() {
        this.num = numOfIslands++;
    }
}

class Edge {
    int start;
    int end;
    int weight;

    Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}