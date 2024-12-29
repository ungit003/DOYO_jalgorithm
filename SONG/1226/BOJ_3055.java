import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[][] map;
    static int R;
    static int C;
    static int[][] dxy ={{0,1},{1,0},{0,-1},{-1,0}};

    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = in.readLine().toCharArray();
        }

        // 잘 들어갔는지 확인
        /*
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
         */
        String result ="KAKTUS";
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j]=='S'){
                    result = bfs(i,j);
                }
            }
        }

        System.out.println(result);

    }
    static String bfs(int x, int y){
        int water_cnt =0; // 물이 찬 횟수
        Queue<int[]> q = new LinkedList<>();
        int[][] visited = new int[R][C];
        int[] start = {x,y,0};
        q.add(start);

        while (!q.isEmpty()){
            int[] curr = q.poll(); // row, col, cnt
//            System.out.println("x="+curr[0] + " " + "y=" + curr[1]);
            if (curr[2] == water_cnt){ // 물로 채우고 시작
                water_full();
                water_cnt++;
            }
            for (int[] xy : dxy) {
                int nx = curr[0] +xy[0];
                int ny = curr[1] +xy[1];
                if (nx<0 || nx>=R || ny<0 || ny>=C || visited[nx][ny]==1) continue; //범위 벗어나면 패스
                if (map[nx][ny]=='.'){
                    visited[nx][ny]=1;
                    q.add(new int[] {nx,ny,curr[2]+1});

                }
                else if (map[nx][ny]=='D') { //종료 조건
                    return Integer.toString(curr[2]+1);
                }
            }
        }


        return "KAKTUS";
    }

    // 물채우기 함수
    static void water_full(){
        ArrayList<int[]> water = new ArrayList<>(); // 다음에 물이찰 좌표들 들어가는 리스트
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j]=='*'){
                    for (int[] xy : dxy) {
                        int nx = i+xy[0];
                        int ny = j+xy[1];
                        if (nx<0 || nx>=R || ny<0 || ny>=C) continue; //범위 벗어나면 패스
                        if (map[nx][ny]=='.'){
                            water.add(new int[] {nx,ny});
                        }
                    }
                }
            }
        }
        for (int[] w : water) {
            map[w[0]][w[1]] ='*';
        }
    }

}


