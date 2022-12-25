package BOJ_12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] inputs;
        Board board = new Board(N);

        // read inputs
        for (int i = 0; i < N; i++) {
            inputs = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                board.cells[i][j] = Integer.parseInt(inputs[j]);
            }
        }

        System.out.println(search(board, 5));
     }

     static int search(Board board, int count) {
        if (count == 0) return board.getMax();

        int[] results = new int[5];
        results[0] = search(board.moveLeft(), count - 1);
        results[1] = search(board.moveRight(), count - 1);
        results[2] = search(board.moveUp(), count - 1);
        results[3] = search(board.moveDown(), count - 1);
        results[4] = board.getMax();

        int max = 0;
        for (int cnt : results)
            max = Math.max(max, cnt);

        return max;
     }

    static class Board {
        final int[][] cells;

        Board(int n) {
            this.cells = new int[n][n];
        }

        Board(Board board) {
            int n = board.cells.length;
            this.cells = new int[n][n];
            for (int i = 0; i < n; i++) {
                System.arraycopy(board.cells[i], 0, this.cells[i], 0, n);
            }
        }

        public int getMax() {
            int max = 0;
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[i].length; j++) {
                    max = Math.max(cells[i][j], max);
                }
            }
            return max;
        }

        public void print() {
            for (int[] cell : cells) {
                for (int c : cell) {
                    System.out.print(c + " ");
                }
                System.out.println();
            }
        }

        private static void shift(int[] merged, int n) {
            for (int i = n - 2; i >= 0; i--) {
                for (int j = n - 1; j > i; j--) {
                    if (merged[j] == 0) {
                        merged[j] = merged[i];
                        merged[i] = 0;
                    }
                }
            }
        }

        private static void shiftInverse(int[] merged, int n) {
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (merged[j] == 0) {
                        merged[j] = merged[i];
                        merged[i] = 0;
                    }
                }
            }
        }

        // 1st -> 2nd -> 3rd ===merge==> 1st 2nd 3rd
        private static int[] merge(int[] prev) {
            int n = prev.length;
            if (n == 1)
                return prev;

            int[] merged = new int[n];
            System.arraycopy(prev, 0, merged, 0, n);

            for (int i = n - 1; i >= 0; i--) {
                for (int j = i - 1; j >= 0; j--) {
                    if (merged[j] == 0) continue;
                    if (merged[i] == merged[j]) {
                        merged[i] <<= 1;
                        merged[j] = 0;
                    }
                    break;
                }
            }
            shift(merged, n);

            return merged;
        }

        // 1st <- 2nd <- 3rd ===merge==> 1st 2nd 3rd
        private static int[] mergeInverse(int[] prev) {
            int n = prev.length;
            if (n == 1)
                return prev;

            int[] merged = new int[n];
            System.arraycopy(prev, 0, merged, 0, n);

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (merged[j] == 0) continue;
                    if (merged[i] == merged[j]) {
                        merged[i] <<= 1;
                        merged[j] = 0;
                    }
                    break;
                }
            }
            shiftInverse(merged, n);

            return merged;
        }

        public Board moveLeft() {
            int n = this.cells.length;
            Board board = new Board(this);
            for (int i = 0; i < n; i++) {
                int[] merged = mergeInverse(board.cells[i]);
                board.cells[i] = merged;
            }
            return board;
        }

        public Board moveRight() {
            int n = this.cells.length;
            Board board = new Board(this);
            for (int i = 0; i < n; i++) {
                int[] merged = merge(board.cells[i]);
                board.cells[i] = merged;
            }
            return board;
        }

        public Board moveDown() {
            int n = this.cells.length;
            Board board = new Board(this);
            for (int i = 0; i < n; i++) {
                int[] prev = new int[n];

                for (int j = 0; j < n; j++) {
                    prev[j] = board.cells[j][i];
                }
                int[] merged = merge(prev);

                for (int j = 0; j < n; j++) {
                    board.cells[j][i] = merged[j];
                }
            }
            return board;
        }

        public Board moveUp() {
            int n = this.cells.length;
            Board board = new Board(this);
            for (int i = 0; i < n; i++) {
                int[] prev = new int[n];

                for (int j = 0; j < n; j++) {
                    prev[j] = board.cells[j][i];
                }
                int[] merged = mergeInverse(prev);

                for (int j = 0; j < n; j++) {
                    board.cells[j][i] = merged[j];
                }
            }
            return board;
        }
    }
}
