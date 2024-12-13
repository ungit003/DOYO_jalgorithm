import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] maps = new int[N][M];

        for(int i=0;i<N;i++){
            StringTokenizer numbers = new StringTokenizer(in.readLine());
            for(int j=0;j<M;j++){
                int n= Integer.parseInt(numbers.nextToken());
                maps[i][j]=n ;
            }
        }

        int[][][][] shapes ={ // I,O,L,T,Z순으로 들어있음, 회전 및 대칭 모두 들어있음
                {
                        {{0, 0}, {0, 1}, {0, 2}, {0, 3}},
                        {{0, 0}, {1, 0}, {2, 0}, {3, 0}},
                },
                {
                        {{0, 0}, {0, 1}, {1, 0}, {1, 1}},
                },
                {
                        {{0, 0}, {0, -1}, {0, 1}, {-1, 1}},
                        {{0, 0}, {0, -1}, {0, 1}, {1, 1}},

                        {{0, 0}, {0, -1}, {0, 1}, {-1, -1}},
                        {{0, 0}, {0, -1}, {0, 1}, {1, -1}},

                        {{0, 0}, {-1, 0}, {1, 0}, {-1, 1}},
                        {{0, 0}, {-1, 0}, {1, 0}, {1, 1}},

                        {{0, 0}, {-1, 0}, {1, 0}, {-1, -1}},
                        {{0, 0}, {-1, 0}, {1, 0}, {1, -1}},
                },
                {
                        {{0, 0}, {0, -1}, {0, 1}, {-1, 0}},
                        {{0, 0}, {0, -1}, {0, 1}, {1, 0}},
                        {{0, 0}, {-1, 0}, {1, 0}, {0, 1}},
                        {{0, 0}, {-1, 0}, {1, 0}, {0, -1}},

                },
                {
                        {{0, 0},{0, -1},{1, 0},{1, 1}},
                        {{0, 0},{0, -1},{-1, 0},{-1, 1}},

                        {{0, 0},{-1, 0},{0, 1},{1, 1}},
                        {{0, 0},{-1, 0},{0, -1},{1, -1}},

                }
        };
        int max_sum=0;
        for(int i=0;i<N;i++){
            int sum=0;
            for(int j=0;j<M;j++){
                for(int z=0;z<shapes.length;z++){
                    sum=cnt_max(i,j,shapes[z],maps,N,M); // 블록별 최대 숫자 구하는 함수 호출
                    if(sum>max_sum){ // 제일 큰수 찾으면
                        max_sum=sum;
                    }
                }
            }
        }
        System.out.println(max_sum);

    }
    static int cnt_max(int x,int y,int[][][] shapes,int[][] maps,int N, int M){
        int max_sum=0;
        for(int i=0;i<shapes.length;i++){ //회전 혹은 대칭되어 생기는 모든 경우 반복해서 최댓값 찾기
            int sum=0;
            boolean is_val=true;
            for(int j=0;j<shapes[i].length;j++){
                int nx= x+ shapes[i][j][0];
                int ny= y+ shapes[i][j][1];
                if(nx <0 || nx>=N || ny<0 || ny>=M){ // 범위 벗어나면 패스
                    is_val =false;
                    break;
                }
                sum +=maps[nx][ny];
            }
            if(is_val && sum >max_sum){ // 범위 벗어난적 없고 최댓값이면 값 갱신
                max_sum=sum;
            }
        }
        return max_sum;
    }
}