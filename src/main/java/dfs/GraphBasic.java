package main.java.dfs;

import java.util.*;
/*
 간선이 최대 2개인 그래프, 서로 연결된 2개의 점은 동시선택이 불가능하다.
 선택할 수 있는 점의 최대 개수를 구해야한다.
 */

public class GraphBasic {
       static int N, M;
        static List<Integer>[] adj;
        static boolean[] visited;
        static int[] color;

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            N = sc.nextInt();  // 노드 개수
            M = sc.nextInt();  // 간선 개수

            adj = new ArrayList[N + 1];
            visited = new boolean[N + 1];
            color = new int[N + 1]; // 0: 미방문, 1: 그룹1, 2: 그룹2

            for (int i = 1; i <= N; i++) {
                adj[i] = new ArrayList<>();
            }

            // 그래프 입력
            for (int i = 0; i < M; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                adj[u].add(v);
                adj[v].add(u);
            }

            int maxNodes = 0;

            // 모든 노드에 대해 탐색 (그래프가 여러 개의 컴포넌트일 수도 있음)
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    maxNodes += bfs(i); // 해당 컴포넌트에서 최대 선택 가능한 노드 개수 계산
                }
            }

            System.out.println(maxNodes);
        }

        // BFS를 사용하여 이분 그래프를 확인하고, 독립 집합의 최대 크기 계산
        static int bfs(int start) {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);
            visited[start] = true;
            color[start] = 1; // 첫 번째 그룹으로 할당

            int[] groupCount = new int[3]; // [0]: 사용 안 함, [1]: 그룹1 개수, [2]: 그룹2 개수
            groupCount[1] = 1;

            while (!queue.isEmpty()) {
                int node = queue.poll();

                for (int neighbor : adj[node]) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        color[neighbor] = 3 - color[node]; // 1 ↔ 2 전환
                        groupCount[color[neighbor]]++;
                        queue.add(neighbor);
                    }
                }
            }

            // 더 큰 그룹을 선택
            return Math.max(groupCount[1], groupCount[2]);

        }
}
