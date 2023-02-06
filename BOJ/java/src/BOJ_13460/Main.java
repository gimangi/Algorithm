package BOJ_13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int N, M;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        State initState = readInputs();
        int result = search(initState);
        System.out.println(result);
    }

    static int search(State initState) {
        Queue<State> queue = new LinkedList<>();
        queue.offer(initState);

        while (!queue.isEmpty()) {
            State state = queue.poll();
            if (state.count > 9) continue;

            for (Direction dir : Direction.values()) {
                State nextState = moveBalls(dir, state);

                if (state.equalState(nextState)) {
                    continue;
                }

                if (nextState.isFail()) {
                    continue;
                }

                if (nextState.isSuccess()) {
                    return nextState.count;
                }

                queue.offer(nextState);
            }
        }

        return -1;
    }

    static State readInputs() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);

        map = new char[N][M];

        Ball red = null, blue = null;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                char ch = line.charAt(j);

                if (ch == 'B') {
                    blue = new Ball(i, j);
                    map[i][j] = '.';
                }
                else if (ch == 'R') {
                    red = new Ball(i, j);
                    map[i][j] = '.';
                }
                else
                    map[i][j] = ch;
            }
        }

        return new State(red, blue, 0);
    }

    static State moveBalls(final Direction dir, final State curState) {
        Ball red = curState.red, blue = curState.blue;
        Ball first = red, second = blue;
        boolean blueFirst = false;

        if (curState.red.x == curState.blue.x) {
            if (dir == Direction.UP) {
                if (red.y > blue.y) {
                    first = blue;
                    second = red;
                    blueFirst = true;
                }
            }
            else if (dir == Direction.DOWN) {
                if (red.y < blue.y) {
                    first = blue;
                    second = red;
                    blueFirst = true;
                }
            }
        }
        if (curState.red.y == curState.blue.y) {
            if (dir == Direction.LEFT) {
                if (red.x > blue.x) {
                    first = blue;
                    second = red;
                    blueFirst = true;
                }
            }
            else if (dir == Direction.RIGHT) {
                if (red.x < blue.x) {
                    first = blue;
                    second = red;
                    blueFirst = true;
                }
            }
        }

        Ball firstMoved = new Ball(first.move(dir, second));
        Ball secondMoved = new Ball(second.move(dir, firstMoved));

        if (blueFirst)
            return new State(secondMoved, firstMoved, curState.count + 1);
        return new State(firstMoved, secondMoved, curState.count + 1);
    }

    static class State {

        Ball red;
        Ball blue;
        int count;

        public State(Ball red, Ball blue, int count) {
            this.red = red;
            this.blue = blue;
            this.count = count;
        }

        boolean equalState(State other) {
            return this.red.equalPosition(other.red) && this.blue.equalPosition(other.blue);
        }

        boolean isFail() {
            return map[blue.y][blue.x] == 'O';
        }

        boolean isSuccess() {
            return map[red.y][red.x] == 'O' && map[blue.y][blue.x] != 'O';
        }
    }

    static class Ball extends Pos {

        public Ball(Pos pos) {
            super(pos.y, pos.x);
        }

        public Ball(int y, int x) {
            super(y, x);
        }

        boolean equalPosition(Ball other) {
            return this.x == other.x && this.y == other.y;
        }

        boolean equalPosition(int y, int x) {
            return this.y == y && this.x == x;
        }

        Pos move(Direction dir, Ball another) {
            if (dir == Direction.LEFT) return parallelMove(-1, another);
            else if (dir == Direction.RIGHT) return parallelMove(1, another);
            else if (dir == Direction.UP) return verticalMove(-1, another);
            else return verticalMove(1, another);
        }

        private Pos verticalMove(int dy, Ball another) {
            int nextY = y;
            while (true) {
                nextY += dy;
                if (!(nextY >= 0 && nextY < N)) break;
                if (map[nextY][x] == 'O') {
                    return new Pos(nextY, x);
                }
                if (another.equalPosition(nextY, x)) break;
                if (!(map[nextY][x] == '.' || map[nextY][x] == 'O'))  break;
            }
            return new Pos(nextY - dy, x);
        }

        private Pos parallelMove(int dx, Ball another) {
            int nextX = x;
            while(true) {
                nextX += dx;
                if (!(nextX >= 0 && nextX < M)) break;
                if (map[y][nextX] == 'O') {
                    return new Pos(y, nextX);
                }
                if (another.equalPosition(y, nextX)) break;
                if (!(map[y][nextX] == '.' || map[y][nextX] == 'O')) break;
            }
            return new Pos(y, nextX - dx);
        }
    }

    static class Pos {
        final int y;
        final int x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    enum Direction {
        LEFT, RIGHT, UP, DOWN
    }
}
