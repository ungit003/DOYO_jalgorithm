package boj;
// 달팽이 3
import java.io.*;
import java.util.*;

public class BOJ_1959_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long r = sc.nextLong();
        long c = sc.nextLong();
        // 꺽이는 횟수
        long count = 0;
        // 도착지점 행 좌표
        long finalRow = 0;
        // 도착지점 열 좌표
        long finalCol = 0;

        // 규칙
        if (r > c) {

            count = (c - 1) * 2 + 1;

            if (c % 2 == 0) {
                finalRow = (c / 2 + 1);
                finalCol = (c / 2);
            } else {
                finalRow = (c / 2 + 1) + (r - c);
                finalCol = (c / 2 + 1);
            }
        }

        else if (r == c) {
            count = (r - 1) * 2;
            if (r % 2 == 0) {
                finalRow = (r / 2 + 1);
                finalCol = (r / 2);
            } else {
                finalRow = (r / 2 + 1);
                finalCol = (r / 2 + 1);
            }
        }

        else {
            count = (r - 1) * 2;
            if (r % 2 == 0) {
                finalRow = (r / 2 + 1);
                finalCol = (r / 2);
            } else {
                finalRow = (r / 2 + 1);
                finalCol = (r / 2 + 1) + (c - r);
            }
        }


        System.out.println(count);
        System.out.println(finalRow + " " + finalCol);
    }
}
