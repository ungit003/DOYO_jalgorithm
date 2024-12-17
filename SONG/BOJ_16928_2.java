import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static int[] maps = new int[101];

    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 사다리 + 뱀 길 표시
        for (int i = 0; i < N + M; i++) {
            StringTokenizer route = new StringTokenizer((in.readLine()));
            int start = Integer.parseInt(route.nextToken());
            int end = Integer.parseInt(route.nextToken());
            maps[start] =end;
        }

        int result = bfs();
        System.out.println(result);

    }

    static int bfs() {
        int[] visited = new int[101];
        Deque<int[]> deque = new LinkedList<>();
        int[] start = {1, 0};
        deque.add(start);
        visited[1] = 1;
        while (!deque.isEmpty()) {
            int[] curr = deque.poll();
            if (curr[0] == 100) {
                return curr[1];
            }

            for (int i=6;i>0;i--) {
                int next_pos= curr[0]+i;
                if (next_pos >100){ // 100 넘어가면 넘어가기
                    continue;
                }
                if (visited[next_pos] == 1) { //이미 방문한적 있는 곳은 패스
                    continue;
                }
//                System.out.println("pos= "+next_pos+ " " + "cnt= " + curr[1]);
                visited[next_pos] = 1;
                if (maps[next_pos] !=0){ // 0 아 아닌 값은 사다리길 아니면 뱀길
                    visited[maps[next_pos]] = 1; //지름길로 이동한 경로도 방문표시
                    deque.add(new int[]{maps[next_pos], curr[1]+1});
                }
                else{
                    deque.add(new int[]{next_pos, curr[1] + 1});
                }
            }
        }
        return -1;
    }
}