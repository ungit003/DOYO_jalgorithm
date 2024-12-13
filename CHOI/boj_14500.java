import java.util.*;

public class Boj_14500 {
    static int[][] grid;
    static int n, m, maxSum = 0;

    static void matchPattern(int[][] pattern) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int sum = 0;
                boolean isValid = true;

                for (int[] p : pattern) {
                    int x = i + p[0];
                    int y = j + p[1];
                    if (x < 0 || x >= n || y < 0 || y >= m) {
                        isValid = false;
                        break;
                    }
                    sum += grid[x][y];
                }

                if (isValid) {
                    maxSum = Math.max(maxSum, sum);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();
        grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        int[][][] patterns = {
            {{0, 0}, {0, 1}, {0, 2}, {0, 3}},
            {{0, 0}, {1, 0}, {2, 0}, {3, 0}},
            {{0, 0}, {0, 1}, {1, 0}, {1, 1}},
            {{0, 0}, {1, 0}, {2, 0}, {2, 1}},
            {{0, 0}, {1, 0}, {2, 0}, {0, 1}},
            {{0, 0}, {0, 1}, {0, 2}, {1, 2}},
            {{0, 2}, {1, 2}, {2, 2}, {2, 1}},
            {{2, 0}, {2, 1}, {1, 1}, {0, 1}},
            {{0, 0}, {1, 0}, {1, 1}, {2, 1}},
            {{1, 0}, {1, 1}, {0, 1}, {0, 2}},
            {{0, 1}, {1, 1}, {1, 0}, {2, 0}},
            {{0, 0}, {0, 1}, {1, 1}, {1, 2}},
            {{0, 0}, {0, 1}, {0, 2}, {1, 1}},
            {{0, 1}, {1, 0}, {1, 1}, {1, 2}},
            {{0, 1}, {1, 0}, {1, 1}, {2, 1}},
            {{1, 0}, {1, 1}, {1, 2}, {0, 1}},
        };

        for (int[][] pattern : patterns) {
            matchPattern(pattern);
        }

        System.out.println(maxSum);
    }
}
