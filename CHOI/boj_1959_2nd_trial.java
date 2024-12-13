import java.util.Scanner;
// 달팽이3
public class Boj_1959_2nd_trial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong(), M = sc.nextLong();  // long 타입으로 변경
        long d_N = N / 2, d_M = M / 2;
        long quotient = Math.min(N, M) / 2;
        if ((N <= M && N % 2 == 0) || (N > M && M % 2 == 0)) {quotient--;}
        long[] remainders = {N - 2 * quotient, M - 2 * quotient};

        int weights = (remainders[0]==1)?0 : (remainders[1]==1)?1 : (remainders[0]==2)?2 : 3;
        System.out.println(quotient * 4 + weights);

        if (remainders[0]>=2 && remainders[1]>=2) {
            System.out.println((quotient + 2) + " " + (quotient + 1));
        } else {
            System.out.println((quotient + remainders[0]) + " " + (quotient + remainders[1]));
        }

        sc.close();
    }
}
