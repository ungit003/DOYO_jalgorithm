import java.util.Scanner;

public class Boj_1959_1st_trial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong(), M = sc.nextLong();  // long 타입으로 변경
        long d_N = N / 2, d_M = M / 2;
        long quotient;
        long[] remainders = new long[2];  // long 타입으로 변경
        if (N <= M) {
            quotient = d_N;
            if (N % 2 == 0) {
                quotient--;  // N이 짝수일 때 하나 줄임
            }
            remainders[0] = N - 2 * quotient;  // N에 남은 나머지 값 계산
            remainders[1] = M - 2 * quotient;  // M에 남은 나머지 값 계산
        } else {
            quotient = d_M;
            if (M % 2 == 0) {
                quotient--;  // M이 짝수일 때 하나 줄임
            }
            remainders[0] = N - 2 * quotient;  // N에 남은 나머지 값 계산
            remainders[1] = M - 2 * quotient;  // M에 남은 나머지 값 계산
        }

        // 결과 출력
        if (remainders[0] == 1 && remainders[1] == 1) {
            System.out.println(quotient * 4);
            System.out.println((quotient + 1) + " " + (quotient + 1));
        } else if (remainders[0] == 1) {
            System.out.println(quotient * 4);
            System.out.println((quotient + 1) + " " + (quotient + remainders[1]));
        } else if (remainders[0] == 2 && remainders[1] >= 2) {
            System.out.println(quotient * 4 + 2);
            System.out.println((quotient + 2) + " " + (quotient + 1));
        } else if (remainders[0] >= 2 && remainders[1] == 2) {
            System.out.println(quotient * 4 + 3);
            System.out.println((quotient + 2) + " " + (quotient + 1));
        } else {
            System.out.println(quotient * 4 + 1);
            System.out.println((quotient + remainders[0]) + " " + (quotient + 1));
        }

        sc.close();
    }
}
