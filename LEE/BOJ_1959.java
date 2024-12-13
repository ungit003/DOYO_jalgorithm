package boj;

import java.util.*;
import java.io.*;
public class BOJ_1959 {
    static int dir;
    static long x,y,r,c,count;
    static int[][] rule= {{1,-1},{-1,-1},{-1,1}, {1,1}};
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        r = sc.nextInt();
        c = sc.nextInt();
        long total = r*c;
        long rightX = 0;
        long rightY = c-1;
        long downX = r-1;
        long downY = c-1;
        long leftX = r-1;
        long leftY = 0;
        long upX = 1;
        long upY = 0;

        for (int i = 0; i < total; i++) {
            long nr = x + dr[dir];
            long nc = y + dc[dir];
            if(nr >=0 && nc >= 0 && nr < r && nc < c){
                if(nr == rightX &&nc == rightY){
                    x = nr;
                    y = nc;
                    count++;
                    dir = 1;
                    rightX += rule[0][0];
                    rightY += rule[0][1];

                } else if (nr == downX & nc == downY) {
                    x = nr;
                    y = nc;
                    count++;
                    dir = 2;
                    downX += rule[1][0];
                    downY += rule[1][1];

                } else if (nr == leftX & nc == leftY) {
                    x = nr;
                    y = nc;
                    count++;
                    dir = 3;
                    leftX += rule[2][0];
                    leftY += rule[2][1];


                }else if(nr == upX & nc == upY){
                    x = nr;
                    y = nc;
                    count++;
                    dir = 0;
                    upX += rule[3][0];
                    upY += rule[3][1];


                }
            }


            x = nr;
            y = nc;


        }

        System.out.println(count);
        System.out.println(x);
        System.out.println(y);


    }

}

