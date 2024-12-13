import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] snail = new int[M][N];

        snail[0][0] = 1;
        int[][] dxy={{0,1},{1,0},{0,-1},{-1,0}};
        int arrow =0; //꺾이는 횟수

        int dir = 0;
        int x =0;
        int y= 0;
        int cnt =2;
        while (true){
            if (cnt==N*M+1){ //while 문 종료 조건
                break;
            }
            int nx = x+dxy[dir%4][0];
            int ny = y+dxy[dir%4][1];

            if (nx < 0 || nx>=M || ny< 0 || ny>=N ){ //범위 벗어나면 패스;
                dir +=1;
                arrow +=1; //방향이 전환되는 경우가 꺾인 화살표가 사용되는 경우
                continue;
            }
            if (snail[nx][ny] != 0){ //이미 방문한 위치면 패스
                dir +=1;
                arrow +=1;
                continue;
            }
            snail[nx][ny]=cnt;
            cnt+=1;
            x=nx;
            y=ny;
        }

        x+=1;
        y+=1;
        System.out.println(arrow);
        System.out.println(x+" "+y);



    }
}