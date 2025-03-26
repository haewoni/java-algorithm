package main.java.dfs;

import java.util.LinkedList;
import java.util.Queue;

/*
 백준1926 그림 찾기
1. 아이디어
- BFS, visited
- 이중 for문
2. 시간복잡도
- O(V+E)
3. 자료구조
- 그래프 전체 지도 : int[][]
- 방문 boolean[][]
- Queue(BFS)
 ~12:30
 */
public class Picture {
    public int[] solution(int[][] paper) {
        int n = paper.length; // 종이의 세로 길이
        int m = paper[0].length; // 세로 길이
        boolean[][] visited = new boolean[n][m];

        int pictureCount =0;
        int maxArea = 0;

        // 방향 배열 ??
        int[] dx = {-1,1,0,0};
        int[] dy = {0,0,-1,1};

        for(int i=0;i<n;i++){
            for(int j=0;i<m;j++){
                if(paper[i][j] == 1 && !visited[i][j]) {
                    pictureCount++;
                    int area = bfs(paper, visited , i , j, dx, dy);

                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return new int[]{pictureCount, maxArea};
    }

    // 상하좌우 탐색하며 그림의 넓이 구하기
    private int bfs(int[][] paper, boolean[][] visited, int x, int y, int[] dx, int[] dy) {
        Queue<int[]> queue = new LinkedList<>(); //FIFO
        visited[x][y] = true;  // 시작 위치 방문 처리
        queue.add(new int[]{x,y}); // 시작 위치 큐에 넣기

        int area = 1; // 시작점 포함 넓이 1부터 시작

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for(int i=0;i<4; i++){
                int nx = cx+dx[i];
                int ny = cy+dy[i];

                if(nx>=0 && ny>=0 && nx < paper.length && ny < paper[0].length ){
                    if (!visited[x][y] && paper[nx][ny] == 1 ){
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx,ny});
                        area++;
                    }
                }
            }
        }
        return area;
    }
}
