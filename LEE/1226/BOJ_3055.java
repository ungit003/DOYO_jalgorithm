package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_3055 {
    static int r,c;
    static char[][] arr;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    // 물의 위치
    static char[][] water;
    // 고슴도치 좌표
    static Deque<int[]> deque = new LinkedList<>();
    // 물의 좌표
    static Deque<int[]> waterDeque = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        r = Integer.parseInt(stk.nextToken());
        c = Integer.parseInt(stk.nextToken());
        // 고슴도치 시작 좌표
        int sr = 0;
        int sc = 0;

        arr = new char[r][c];
        water = new char[r][c];
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                char ch = line.charAt(j);
                arr[i][j] = ch;
                if (ch == 'S') {
                    sr = i;
                    sc = j;
                } else if (ch == '*') {
                    water[i][j] = '*';
                    waterDeque.add(new int[]{i,j});
                }
            }
        }
        int result = escape(sr,sc);
        System.out.println(result == -1 ? "KAKTUS" : result + 1);
    }
    static int escape(int sr, int sc) {
        boolean[][] visited = new boolean[r][c];
        visited[sr][sc] = true;
        // 시작 좌표랑 거리 같이 저장
        deque.add(new int[]{sr, sc, 0});
        // 물을 먼저 확산시키고 고슴도치 이동
        while (!deque.isEmpty()) {
            int size = waterDeque.size();
            for (int i = 0; i < size; i++) {
                int[] current = waterDeque.poll();
                int wr = current[0];
                int wc = current[1];
                for (int d = 0; d < 4; d++) {
                    int nr = wr + dr[d];
                    int nc = wc + dc[d];
                    // 빈 곳이고 물이 아닌 곳만 확산
                    if (0 <= nr && nr < r && 0 <= nc && nc < c && arr[nr][nc] == '.' && water[nr][nc] != '*') {
                        water[nr][nc] = '*';
                        waterDeque.add(new int[]{nr, nc});
                    }
                }
            }

            size = deque.size();
            for (int i = 0; i < size; i++) {
                int[] current = deque.poll();
                int cr = current[0];
                int cc = current[1];
                int dist = current[2];

                for (int d = 0; d < 4; d++) {
                    int nr = cr + dr[d];
                    int nc = cc + dc[d];
                    // 방문 안했고 물이 아니고 빈 곳이면 이동
                    if (0 <= nr && nr < r && 0 <= nc && nc < c && !visited[nr][nc] && water[nr][nc] != '*') {
                        if (arr[nr][nc] == '.' ) {
                            visited[nr][nc] = true;
                            deque.add(new int[]{nr, nc, dist + 1});
                        } else if (arr[nr][nc] == 'D') {
                            return dist;
                        }
                    }
                }
            }


        }

        return -1;
    }
}