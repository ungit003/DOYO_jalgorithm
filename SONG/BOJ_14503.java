//BufferedReader 사용하는 방법
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        StringTokenizer start = new StringTokenizer(in.readLine());
        int x = Integer.parseInt(start.nextToken());
        int y = Integer.parseInt(start.nextToken());
        int dir = Integer.parseInt(start.nextToken());

        int[][] maps = new int[N][M];

        // 방 초기화
        for(int i=0;i<N;i++){
            StringTokenizer line = new StringTokenizer(in.readLine());
            for(int j =0;j<M;j++){
                maps[i][j]=Integer.parseInt(line.nextToken());
            }
        }
        int result= clean_room(x,y,dir,maps,N,M,0);
        System.out.println(result);
    }
    public static int clean_room(int x, int y, int dir, int[][] maps, int N, int M, int cnt) {
        //현재 칸 청소 해야 하면 청소
        if (maps[x][y]==0){
            maps[x][y]=2;
            cnt++;
        }

        //주변에 청소되지 않은칸 검사함수 호출
        int[][] dxy ={{-1,0},{0,1},{1,0},{0,-1}};
        int cases = is_clean(x,y,dir,maps,N, M,dxy); //1= 주변칸 청소되지 않은칸 X + 후진가능,
        //2= 주변칸 청소되지 않은칸 X + 후진불가능,
        //3= 청소가능한 칸 있음
        if(cases==1){
            int back_x= x+dxy[(dir+2)%4][0];
            int back_y= y+dxy[(dir+2)%4][1];
//            System.out.println("후진"+back_x+" "+back_y);
            return clean_room(back_x,back_y,dir,maps,N,M,cnt);
        } else if(cases==2){
            return cnt;
        } else if (cases==3) {
            for(int i=0;i<4;i++){
                dir= (dir + 3) % 4;
                int nx=x+dxy[dir][0];
                int ny=y+dxy[dir][1];
                if (nx<0 || nx>=N || ny<0 || ny>=M){ //범위벗어나면 패스
                    continue;
                } else if (maps[nx][ny]==0) { //청소가능한칸 있음
//                    System.out.println("직진"+nx+" "+ny);
                    return clean_room(nx,ny,dir,maps,N,M,cnt);
                }
            }

        }
        return cnt;
    }
    public static int is_clean(int x, int y,int dir, int[][] maps,int N, int M,int[][] dxy){
        for(int i=0;i<4;i++){
            int nx=x+dxy[i][0];
            int ny=y+dxy[i][1];
            if (nx<0 || nx>=N || ny<0 || ny>=M){ //범위벗어나면 패스
                continue;
            } else if (maps[nx][ny]==0) { //청소가능한칸 있음
                return 3;
            }
        }
        int back_x= x+dxy[(dir+2)%4][0];
        int back_y= y+dxy[(dir+2)%4][1];
        if (back_x <0 || back_x>=N || back_y<0 || back_y>=M || maps[back_x][back_y]==1){
            return 2;
        }
        return 1;
    }
}
