package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1647 {
    static int totalWeight;
    static List<int[]>[] list;
    static boolean[] visited;
    // 이어진 간선 중 가장 큰 비용이 드는 길
    static int maxWeight;
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(stk.nextToken());
        int n = Integer.parseInt(stk.nextToken());
        int [][] graph = new int[n][3];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3;j++) {
                graph[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        list = new ArrayList[m+1];
        visited = new boolean[m+1];

        for (int i = 0; i < m+1; i++) {
            list[i] = new ArrayList<>();
        }
        for (int[] edge: graph){
            int a = edge[0];
            int b = edge[1];
            int w = edge[2];

            list[a].add(new int[] {b,w});
            list[b].add(new int[] {a,w});
        }
//        prim(1);
//        System.out.println(totalWeight - maxWeight);
        for (int i = 0; i < m+1; i++) {
            System.out.println(list[i]);
        }

    }

    static void prim(int start){
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(o -> o[1]));
        pq.add(new int[] {start,0});

        while (!pq.isEmpty()){
            int[] current = pq.poll();
            int node = current[0];
            int weight = current[1];

            if (visited[node]) continue;

            visited[node] = true;
            totalWeight += weight;
            if (maxWeight <= weight) maxWeight = weight;

            for (int[] next : list[node]){
                if(!visited[next[0]]){
                    pq.add(next);
                }
            }

        }
    }
}
