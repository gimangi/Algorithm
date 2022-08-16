package BOJ_2239;

import java.util.*;

public class Main {

    static int map[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        map = new int[9][9];
        ArrayList<Position> blanks = new ArrayList<>();
        Stack<Position> stack = new Stack<>();

        for (int i=0; i<9; i++) {
            String line = sc.nextLine();
            for (int j=0; j<9; j++) {
                int num = line.charAt(j) - '0';
                map[i][j] = num;
                if (num == 0) {
                    Position pos = new Position(i, j, 0);
                    pos.cnt = blanks.size();
                    blanks.add(pos);
                }
            }
        }
        
        Position first = new Position(blanks.get(0).y, blanks.get(0).x, 0);
        first.cnt = -1;
        stack.push(first);

        while (!stack.isEmpty()) {
            Position cur = stack.pop();
            map[cur.y][cur.x] = cur.num;

            if (cur.cnt == blanks.size() - 1)
                break;

            clear(blanks, cur.cnt + 1);
            Position seek = blanks.get(cur.cnt + 1);

            for (int t=9; t>=1; t--) {
                seek.num = t;
                if (checkRow(seek) && checkCol(seek) && checkBox(seek)) {
                    Position next = new Position(seek.y, seek.x, t);
                    next.cnt = seek.cnt;
                    stack.push(next);
                }
            }
        }

        printSolution();
    }

    static void clear(ArrayList<Position> blanks, int idx) {
        for (int i=idx; i<blanks.size(); i++) {
            Position pos = blanks.get(i);
            map[pos.y][pos.x] = 0;
        }
    }

    static void printSolution() {
        for (int i=0; i<9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    static boolean checkRow(Position pos) {
        for (int i=0; i<9; i++)
            if (map[pos.y][i] == pos.num)
                return false;
        return true;
    }

    static boolean checkCol(Position pos) {
        for (int i=0; i<9; i++)
            if (map[i][pos.x] == pos.num)
                return false;
        return true;
    }

    static boolean checkBox(Position pos) {
        int sx, sy;
        sx = pos.x / 3 * 3;
        sy = pos.y / 3 * 3;
        for (int i=sy; i<sy+3; i++)
            for (int j=sx; j<sx+3; j++)
                if (map[i][j] == pos.num)
                    return false;
        return true;
    }

    static class Position {
        int y;
        int x;
        int num;

        int cnt = 0;

        Position(int y, int x, int num) {
            this.y = y;
            this.x = x;
            this.num = num;
        }
    }
}
