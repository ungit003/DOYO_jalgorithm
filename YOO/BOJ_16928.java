import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        
        int[] board = new int[101];
        boolean[] visited = new boolean[101];
        
        // 보드 초기화
        for (int i = 1; i <= 100; i++) {
            board[i] = i;
        }
        
        // 뱀, 사다리리 입력
        for (int i = 0; i < N + M; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            board[start] = end;
        }
        
        // BFS
        Queue<int[]> queue = new LinkedList<>();
        // {위치, 주사위 굴림 횟수}
        queue.offer(new int[]{1, 0}); 
        visited[1] = true;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int pos = current[0];
            int count = current[1];
            
            if (pos == 100) {
                System.out.println(count);
                return;
            }
            
            for (int i = 1; i <= 6; i++) {
                int nextPos = pos + i;
                if (nextPos <= 100 && !visited[nextPos]) {
                    visited[nextPos] = true;
                    queue.offer(new int[]{board[nextPos], count + 1});
                }
            }
        }
    }
}
