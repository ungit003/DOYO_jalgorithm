package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class BOJ_2239 {
    static int[][] map;
    // 0인 좌표
    static List<int[]> zeros;
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      map = new int[9][9];
      zeros = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            String numbers = br.readLine();
            for (int j = 0; j < 9; j++) {
                int num = numbers.charAt(j) - '0';
                map[i][j] = num;
                if (num == 0) zeros.add(new int[]{i,j});
            }
        }
        dfs(0);

    }
    // 같은 행에 똑같은 숫자 있는지 체크
    static boolean rowCheck(int row, int num){
        for (int i = 0; i < 9; i++) {
            if(map[row][i] == num) return false;
        }
        return true;
    }
    // 같은 열에 똑같은 숫자 있는지 체크
    static boolean colCheck(int col, int num){
        for (int i = 0; i < 9; i++) {
            if (map[i][col] == num) return false;
        }
        return true;
    }
    // 3*3 정사각형 내에 똑같은 숫자 있는지 체크
    static boolean squareCheck(int row, int col, int num){
        // 0,0 0,3 0,6 3,0 3,3 3,6 6,0 6,3 6,6을 기준으로 정사각형이 만들어짐
        // 0,3,6을 기준으로 x,y 좌표 저장
        int x = row / 3 * 3;
        int y = col / 3 * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[x+i][y+j] == num) return false;
            }
        }
        return true;
    }
    static void dfs(int count){
        if (count == zeros.size()){
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();

            }
            System.exit(0);
        }
        int x = zeros.get(count)[0];
        int y = zeros.get(count)[1];
        for (int num = 1; num <= 9; num++) {
            if (rowCheck(x,num) && colCheck(y,num) && squareCheck(x,y,num)){
                map[x][y] = num;
                dfs(count+1);
                map[x][y] = 0; // 백트래킹 잘못된 숫자 집어넣으면 돌아가서 다시 0으로 바꾸기
            }
        }


    }

}
