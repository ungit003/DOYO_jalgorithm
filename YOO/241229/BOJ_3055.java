import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] map;
    static int[][] dist;
    static Queue<int[]> water = new LinkedList<>();
    static Queue<int[]> hedgehog = new LinkedList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]); // 행의 수
        C = Integer.parseInt(input[1]); // 열의 수
        map = new char[R][C];
        dist = new int[R][C];

        // 지도 정보 입력
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '*') water.offer(new int[]{i, j}); // 물의 위치 저장
                if (map[i][j] == 'S') hedgehog.offer(new int[]{i, j}); // 고슴도치의 초기 위치 저장
            }
        }

        System.out.println(bfs());
    }

    static String bfs() {
        while (!hedgehog.isEmpty()) {
            // 물 확장
            int size = water.size();
            while (size-- > 0) {
                int[] cur = water.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];
                    if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue; // 지도 범위 벗어나면 무시
                    if (map[nx][ny] == '.' || map[nx][ny] == 'S') {
                        map[nx][ny] = '*'; // 물 확장
                        water.offer(new int[]{nx, ny});
                    }
                }
            }

            // 고슴도치 이동
            size = hedgehog.size();
            while (size-- > 0) {
                int[] cur = hedgehog.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];
                    if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue; // 지도 범위 벗어나면 무시
                    if (map[nx][ny] == 'D') return String.valueOf(dist[cur[0]][cur[1]] + 1); // 비버의 굴 도착
                    if (map[nx][ny] == '.') {
                        map[nx][ny] = 'S'; // 고슴도치 이동
                        dist[nx][ny] = dist[cur[0]][cur[1]] + 1; // 이동 거리 갱신
                        hedgehog.offer(new int[]{nx, ny});
                    }
                }
            }
        }
        return "KAKTUS"; // 비버의 굴에 도달할 수 없는 경우
    }
}