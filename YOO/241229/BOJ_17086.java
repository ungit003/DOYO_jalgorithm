import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    // 8방향 이동을 위한 배열 (상, 하, 좌, 우, 왼쪽 위 대각선, 오른쪽 위 대각선, 왼쪽 아래 대각선, 오른쪽 아래 대각선)
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 첫 줄 입력 처리
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]); // 행의 수
        M = Integer.parseInt(input[1]); // 열의 수
        map = new int[N][M];
        
        // 맵 정보 입력
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
        
        int maxSafeDistance = 0;
        
        // 모든 빈 칸에 대해 안전 거리 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    int distance = bfs(i, j);
                    maxSafeDistance = Math.max(maxSafeDistance, distance);
                }
            }
        }
        
        System.out.println(maxSafeDistance);
    }
    
    // BFS를 이용한 안전 거리 계산 함수
    static int bfs(int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        
        queue.offer(new int[]{startX, startY, 0}); // x좌표, y좌표, 거리
        visited[startX][startY] = true;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1], dist = current[2];
            
            // 상어를 발견하면 현재까지의 거리 반환
            if (map[x][y] == 1) {
                return dist;
            }
            
            // 8방향 탐색
            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                // 맵 범위 내에 있고 아직 방문하지 않은 칸이면 큐에 추가
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny, dist + 1});
                    visited[nx][ny] = true;
                }
            }
        }
        
        return -1; // 상어를 찾지 못한 경우 (실제로는 발생하지 않음)
    }
}