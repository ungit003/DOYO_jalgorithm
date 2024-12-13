import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        long M = Integer.parseInt(st.nextToken());
        long N = Integer.parseInt(st.nextToken());

        // 배열 실제로 할당하면 메모리 초과남
        long top=0;
        long right = N-1;
        long bottom = M-1;
        long left = 0;

        long[][] dxy={{0,1},{1,0},{0,-1},{-1,0}};
        long arrow =0; //꺾이는 횟수

        int dir = 0;
        long x =0;
        long y= 0;
        long cnt =1;
        while (cnt< M*N){
            long nx = x+dxy[dir][0];
            long ny = y+dxy[dir][1];

            if (nx < top || nx > bottom || ny < left || ny > right ){ //범위 벗어나면 패스;
                if (dir==0){
                    top++;
                } else if (dir==1) {
                    right--;
                } else if (dir==2) {
                    bottom--;
                } else if (dir==3) {
                    left++;
                }
                dir =(dir+1)%4;
                arrow +=1; //방향이 전환되는 경우가 꺾인 화살표가 사용되는 경우
                continue;
            }
            cnt+=1;
            x=nx;
            y=ny;
        }

        System.out.println(arrow);
        System.out.println((x+1)+" "+(y+1));

    }
}