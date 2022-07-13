import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10026 {

    static int N;
    static char picture[][];
    static boolean visited[][];

    static boolean visitedWeak[][];

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        picture = new char[N][N];
        visited = new boolean[N][N];
        visitedWeak = new boolean[N][N];

        for (int i=0; i<N; i++) {
            String line = br.readLine();
            for (int j=0; j<line.length(); j++)
                picture[i][j] = line.charAt(j);
        }

        int cnt = 0;
        for (int i=0; i<N; i++)
            for (int j=0; j<N; j++) {
                if (!visited[i][j])
                    cnt++;
                bfs(i, j);
            }

        int cntWeak = 0;
        for (int i=0; i<N; i++)
            for (int j=0; j<N; j++) {
                if (!visitedWeak[i][j])
                    cntWeak++;
                bfsWeak(i, j);
            }

        System.out.println(cnt + " " + cntWeak);
    }

    static void bfs(int row, int col) {
        if (visited[row][col])
            return;

        char color = picture[row][col];
        visited[row][col] = true;

        if (row > 0 && picture[row-1][col] == color)
            bfs(row-1, col);
        if (row < N-1 && picture[row+1][col] == color)
            bfs(row+1, col);
        if (col > 0 && picture[row][col-1] == color)
            bfs(row, col-1);
        if (col < N-1 && picture[row][col+1] == color)
            bfs(row, col+1);

    }

    static void bfsWeak(int row, int col) {
        if (visitedWeak[row][col])
            return;

        char color = picture[row][col];
        visitedWeak[row][col] = true;

        if (row > 0 && rgWeak(picture[row-1][col], color))
            bfsWeak(row-1, col);
        if (row < N-1 && rgWeak(picture[row+1][col], color))
            bfsWeak(row+1, col);
        if (col > 0 && rgWeak(picture[row][col-1], color))
            bfsWeak(row, col-1);
        if (col < N-1 && rgWeak(picture[row][col+1], color))
            bfsWeak(row, col+1);
    }

    static boolean rgWeak(char c1, char c2) {
        if (c1 == c2)
            return true;
        else if (c1 == 'R' && c2 != 'B')
            return true;
        else if (c1 == 'G' && c2 != 'B')
            return true;
        else if (c2 == 'R' && c1 != 'B')
            return true;
        else if (c2 == 'G' && c1 != 'B')
            return true;
        return false;
    }
}
