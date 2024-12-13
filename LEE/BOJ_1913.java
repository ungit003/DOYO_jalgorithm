package boj;
// 달팽이
import java.util.*;
import java.io.*;
public class BOJ_1913 {
    public static void main(String[] args) throws IOException {
        int[] dr = {0,1,0,-1};
        int[] dc = {1,0,-1,0};
        // 델타로 한 사이클 돌 때 가는 횟수
        int[] rule = {1,2,2,2};
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int targetNum = sc.nextInt();
        int [][] arr = new int[n][n];
        int start = n / 2;
        int r =start, c = start;
        int targetR = 0 , targetC = 0;
        arr[r][c] = 1;
        int num = 2;

        while (num <= n*n){
            r -= 1;
            arr[r][c] = num;
            if (num == targetNum){
                targetR = r;
                targetC = c;
            }
            num++;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < rule[i]; j++) {
                    r += dr[i];
                    c += dc[i];
                    arr[r][c] = num;

                    if (num == targetNum){
                        targetR = r;
                        targetC = c;
                    }
                    num++;
                }
            }
            // 한 사이클 돌면 횟수 2씩 증가하고 다시 한 바퀴
            for (int i = 0; i < 4; i++) {
                rule[i] += 2;


            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        if (targetNum ==1){
            System.out.println((start+1) + " " + (start+1));
        }else {
            System.out.println((targetR + 1) + " " + (targetC + 1));
        }


    }
}
