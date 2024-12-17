import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // ArrayList로 2차원 배열 선언하기
        ArrayList<Integer>[] maps = new ArrayList[101];
        ArrayList<Integer> special = new ArrayList<>();

        // 노드에 이동할 수 있는 노드 번호 넣기
        for (int i = 1; i < 100; i++) {
            // 배열안에 또 ArrayList 넣기
            maps[i] = new ArrayList<Integer>();
            for (int j = 1; j < 7; j++) {
                int num = i + j;
                if (num > 100) {
                    break;
                }
                maps[i].add(num);
            }
        }


        // 사다리 길 표시
        for (int i = 1; i < N + 1; i++) {
            StringTokenizer route = new StringTokenizer((in.readLine()));
            int start = Integer.parseInt(route.nextToken());
            int end = Integer.parseInt(route.nextToken());
            maps[start].clear();
            maps[start].add(end);
            special.add(start);
        }
        // 뱀길 표시
        for (int i = 0; i < M; i++) {
            StringTokenizer route = new StringTokenizer((in.readLine()));
            int start = Integer.parseInt(route.nextToken());
            int end = Integer.parseInt(route.nextToken());
            maps[start].clear();
            maps[start].add(end);
            special.add(start);
        }

        // 출력해서 잘 들어갔나 확인
//        for (ArrayList<Integer> map : maps) {
//            System.out.println(map);
//        }

        int result = bfs(maps, special);
        System.out.println(result);

    }

    static int bfs(ArrayList<Integer>[] maps, ArrayList<Integer> special) {
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
            for (int next_pos : maps[curr[0]]) {
                if (visited[next_pos] == 1) { //이미 방문한적 있는 곳은 패스
                    continue;
                }
//                System.out.println("pos= "+next_pos+ " " + "cnt= " + curr[1]);
                visited[next_pos] = 1;
                if (special.contains(next_pos)){ //사다리를 타거나 뱀길은 cnt하지 않음
                    deque.addFirst(new int[]{next_pos, curr[1]}); // cnt하지 않으므로 최우선 탐색해야함 맨앞에 넣기
                }
                else{
                    deque.add(new int[]{next_pos, curr[1] + 1});
                }
            }
        }
        return -1;
    }
}