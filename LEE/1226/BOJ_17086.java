package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_17086 {
    static int[] dr = {-1,-1,0,1,1,1,0,-1};
    static int[] dc = {0,1,1,1,0,-1,-1,-1};
    static int[][] arr;

    static int m,n;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        m = Integer.parseInt(stk.nextToken());
        n = Integer.parseInt(stk.nextToken());
        arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        int answer = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 0) {
                    // 각 칸마다 bfs돌면서 나온 값이랑 기존 답이랑 비교해서 큰 값 저장
                    answer = Math.max(answer, search(i, j));
                }
            }
        }
        System.out.println(answer + 1);

    }
    static int search(int x, int y){
        Deque <int[]> deque = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        // 현재 좌표와 거리 저장
        deque.add(new int[]{x,y,0});
        visited[x][y] = true;


        while (!deque.isEmpty()){
            int current[] = deque.poll();
            int cr = current[0];
            int cc = current[1];
            int dist = current[2];

            for (int i = 0; i < 8; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];

                if (nr >= 0 && nc >= 0 && nr < m && nc < n){
                    if (arr[nr][nc] == 1 ) return dist;
                    if (!visited[nr][nc]){
                        deque.add(new int[]{nr,nc,dist + 1});
                        visited[nr][nc] = true;
                    }
                }
            }




        }
        return 0;
    }
}
