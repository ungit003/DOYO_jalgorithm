import java.io.*;
import java.util.*;

public class Main {
    // 테트로미노의 모든 가능한 모양 정의
    static int[][][] tetrominos = {
        {{0,0}, {0,1}, {0,2}, {0,3}},
        {{0,0}, {1,0}, {2,0}, {3,0}},
        {{0,0}, {1,0}, {0,1}, {1,1}},
        {{0,0}, {1,0}, {2,0}, {2,1}},
        {{0,1}, {1,1}, {2,1}, {2,0}},
        {{0,0}, {0,1}, {1,1}, {2,1}},
        {{0,0}, {0,1}, {1,0}, {2,0}},
        {{0,0}, {1,0}, {1,1}, {1,2}},
        {{0,2}, {1,0}, {1,1}, {1,2}},
        {{0,0}, {0,1}, {0,2}, {1,2}},
        {{0,0}, {1,0}, {0,1}, {0,2}},
        {{0,0}, {1,0}, {1,1}, {2,1}},
        {{0,1}, {1,1}, {1,0}, {2,0}},
        {{0,1}, {0,2}, {1,0}, {1,1}},
        {{0,0}, {0,1}, {1,1}, {1,2}},
        {{0,0}, {0,1}, {0,2}, {1,1}},
        {{0,1}, {1,0}, {1,1}, {1,2}},
        {{0,0}, {1,0}, {1,1}, {2,0}},
        {{0,1}, {1,0}, {1,1}, {2,1}}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[][] board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        System.out.println(calculateMaxSum(board, N, M));
    }
    
    static int calculateMaxSum(int[][] board, int N, int M) {
        int maxSum = 0;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int[][] tetromino : tetrominos) {
                    int sum = 0;
                    boolean isValid = true;
                    
                    for (int[] block : tetromino) {
                        int ni = i + block[0];
                        int nj = j + block[1];
                        
                        if (ni >= 0 && ni < N && nj >= 0 && nj < M) {
                            sum += board[ni][nj];
                        } else {
                            isValid = false;
                            break;
                        }
                    }
                    
                    if (isValid) {
                        maxSum = Math.max(maxSum, sum);
                    }
                }
            }
        }
        
        return maxSum;
    }
}
