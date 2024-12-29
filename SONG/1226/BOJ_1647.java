import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;



public class Main {

    // Node 객체 클래스 정의
    // Comparable 인터페이스를 구현하여 Collections.sort()로 간선을 정렬할 수 있도록 하기
    static class Node implements Comparable<Node> {
        int from; //간선 시작 정점
        int to; //간선 끝 정점
        int cost; //간선의 가중치

        public Node(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o){
            return this.cost - o.cost; // 가중치 기준 오름차순 정렬
        }

    }

    static int V, E; // V= 정점의 개수, E=간선의 개수
    static int[] parents; // union-find의 부모 배열
    static ArrayList<Node> nodeList; //간선 정보를 저장할 리스트

    public static void main(String[] args) throws IOException {
//        BufferedReader in = new BufferedReader(new FileReader("input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parents = new int[V+1];
        nodeList = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(in.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            nodeList.add(new Node(from,to,cost)); // 간선 정보 저장하기
        }

        Collections.sort(nodeList); // 가중치를 기준으로 정렬


        make(); //Union-Find 초기화

        int sumCost =0; // MST의 총 가충치
        int cnt =0; // 추가된 간선수
        int maxCost=0; // 최대 가중치 값

        for (Node n : nodeList) {
            if (union(n.from, n.to)){ //사이클이 발생하지 않으면
                sumCost += n.cost;
                cnt++;
                maxCost =Math.max(n.cost,maxCost); //최대 가중치 업데이트
                if (cnt == V-1 ) break;  // 간선 수가 정점 수 -1이면 종료
            }
        }

        System.out.println(sumCost-maxCost);

    }

    // Union-Find: 초기화
    private static void make() {
        for (int i = 1; i <= V; i++) {
            parents[i] = i; // 자기 자신을 대표자로 설정
        }
    }

    // 정점의 최상위 대표자를 찾는 함수
    private static int findSet(int v){
        if (parents[v] == v) return v;

        return parents[v] = findSet(parents[v]);
    }

    // 두 집합을 병합하는 함수
    private static boolean union(int from, int to){
        int fromRoot = findSet(from); // from의 대표자
        int toRoot = findSet(to);     // to의 대표자

        if (fromRoot == toRoot) return false; //같은 집합이면 사이클 발생

        parents[toRoot] = fromRoot; // 병합: toRoot를 fromRoot 아래로 연결
        return true;

    }
}

