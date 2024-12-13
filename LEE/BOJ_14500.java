package boj;
// 테트로미노
import java.util.*;
import java.io.*;
public class BOJ_14500 {
    static int n,m,sum,max;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        sum = 0 ;
        max = 0 ;
        arr = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        // 배열 모든 위치에서 테트로미노 탐색
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                search(i,j,arr[i][j],1);
                visited[i][j] = false;
            }

        }
        System.out.println(max);
    }

    static void search(int r, int c, int sum, int count) {
        if (count == 4) {
            max = Math.max(sum, max);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr >=0 && nc >=0 && nr < n && nc < m ){
                if(!visited[nr][nc]){
                    // ㅗ 모양은 현재 위치에서 이동하지 않고 다음 칸 탐색
                    if (count == 2){
                        visited[nr][nc] = true;
                        search(r,c,sum+arr[nr][nc],count+1);
                        visited[nr][nc] = false;
                    }
                    visited[nr][nc] = true;
                    search(nr,nc,sum+arr[nr][nc],count+1);
                    visited[nr][nc] = false;
                }
            }
        }
    }

}

