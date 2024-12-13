package boj;
// 로봇청소기
import java.util.*;
import java.io.*;
public class BOJ_14503  {
    static int n,m,dir,count;
    static int[][] arr;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        n = Integer.parseInt(stk.nextToken());
        m = Integer.parseInt(stk.nextToken());
        count = 1;
        arr = new int[n][m];
        stk = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(stk.nextToken());
        int c = Integer.parseInt(stk.nextToken());
        dir = Integer.parseInt(stk.nextToken());
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(stk.nextToken());
            }

        }
        clean(r,c,dir);
        System.out.println(count);
    }

    static void clean(int r, int c, int d){
        arr[r][c] = 2;
        for (int i = 0; i < 4; i++) {
            d = (d+3)%4;
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr >= 0 && nc >= 0 && nr < n && nc < m){
                // 벽일 경우 넘어가고
                if (arr[nr][nc] == 1){
                    continue;
                }
                // 청소되지 않은 칸일 경우 count 1 증가하고, 현재 호출 스택 종료
                if(arr[nr][nc] == 0){
                    count++;
                    clean(nr,nc,d);
                    return;
                }

            }
        }
        int backDir = (d+2) % 4; // 뒤쪽 방향
        int nr2 = r + dr[backDir];
        int nc2 = c + dc[backDir];
        if (nr2 >= 0 && nc2 >= 0 && nr2 < n && nc2 < m){
            // 뒤쪽 방향의 칸이 벽일 경우 종료
            if(arr[nr2][nc2] == 1){
                return;
            // 아닐 경우 다시 clean 메서드 호출
            }else {
                clean(nr2,nc2,d);
            }

        }

    }


}
