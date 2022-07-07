import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_16236 {

    static Scanner sc = new Scanner(System.in);

    public static void main(String args[]) {
        int N = Integer.parseInt(sc.nextLine());
        int[][] map = new int[N][N];
        Coord curPos = new Coord();
        int result = 0;
        int size = 2;
        int eat = 0;

        for (int i=0; i<N; i++) {
            String[] nums = sc.nextLine().split(" ");

            for (int j=0; j<N; j++) {
                int num = Integer.parseInt(nums[j]);
                map[i][j] = num;
                if (num == 9) {
                    curPos.x = j;
                    curPos.y = i;
                }
            }
        }

        while (true) {
            // bfs
            boolean visited[][] = new boolean[N][N];
            Queue<Coord> q = new LinkedList<>();
            q.add(curPos);
            visited[curPos.y][curPos.x] = true;
            Coord atePos = null;

            while (!q.isEmpty()) {
                Coord c = q.poll();

                for (int i=0; i< DIRS.length; i++) {
                    int nextX = c.x + DIRS[i].x;
                    int nextY = c.y + DIRS[i].y;

                    if (nextY>=0 && nextY<N && nextX>=0 && nextX<N && !visited[nextY][nextX]) {
                        int look = map[nextY][nextX];
                        Coord nextPos = new Coord(nextX, nextY, c.value+1);;

                        if (look < size && look != 0 && look != 9) {
                            if (atePos == null)
                                atePos = nextPos;
                            else if (atePos.y>nextPos.y && atePos.value>=nextPos.value)
                                atePos = nextPos;
                            else if (atePos.y==nextPos.y && atePos.x>nextPos.x && atePos.value>=nextPos.value)
                                atePos = nextPos;
                        }
                        else if (look <= size){
                            q.add(nextPos);
                            visited[nextY][nextX] = true;
                        }

                    }
                }

            }

            if (atePos != null) {
                map[curPos.y][curPos.x] = 0;
                map[atePos.y][atePos.x] = 9;
                curPos.x = atePos.x;
                curPos.y = atePos.y;
                result += atePos.value;
                if (++eat == size) {
                    eat = 0;
                    size++;
                }
            }
            else
                break;
        }

        System.out.println(result);


    }

    static Coord[] DIRS = {new Coord(0, -1), new Coord(-1, 0), new Coord(1, 0), new Coord(0, 1)};
}

class Coord {
    int x;
    int y;

    // move count
    int value = 0;

    Coord() {
        this.x = 0;
        this.y = 0;
    }

    Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Coord(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
}