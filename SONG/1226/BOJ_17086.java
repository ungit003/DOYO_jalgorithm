import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static int N; // row
    static int M; // col
    static int[][] ans; // 최단 경로가 들어가는 배열
    static int[][] maps;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ans = new int[N][M];
        maps = new int[N][M];

        Queue<int[]> queue = new LinkedList<>();

        // 입력 처리 및 초기 상어 위치 큐에 추가
        for (int i = 0; i < N; i++) {
            StringTokenizer line = new StringTokenizer(in.readLine());
            for (int j = 0; j < M; j++) {
                maps[i][j] = Integer.parseInt(line.nextToken());
                if (maps[i][j] == 1) {
                    queue.add(new int[]{i, j}); // 상어 위치 추가
                    ans[i][j] = 0;             // 상어 위치의 안전 거리 = 0
                } else {
                    ans[i][j] = -1;            // 빈 칸 초기화
                }
            }
        }

        bfs(queue);

        // 최댓값 계산
        int max_num = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                max_num = Math.max(max_num, ans[i][j]);
            }
        }

        System.out.println(max_num);
    }

    static void bfs(Queue<int[]> queue) {
        int[][] dxy = { {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1} }; // 8방향

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];

            for (int[] dir : dxy) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                // 범위 확인 및 방문하지 않은 빈 칸만 처리
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                if (ans[nx][ny] == -1) {
                    ans[nx][ny] = ans[x][y] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }
}