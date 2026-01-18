import sys, heapq
input = sys.stdin.readline


N, M = map(int, input().strip().split())
grid = [[''] * (M + 1) for _ in range(N+1)]
for r in range(1, N+1):
    value = list(map(int, input().strip().split()))
    for c in range(1, M+1):
        grid[r][c] = value[c-1]

dirs = [[0,1],[0, -1],[1,0],[-1,0]]
start_pos = list(map(int, input().strip().split()))
end_pos = list(map(int, input().strip().split()))

start_pos[2] -= 1
end_pos[2] -= 1

def bfs():
    pq = []
    dp = [[[987654321] * 4 for _ in range(M+1)] for _ in range(N+1)]
    dp[start_pos[0]][start_pos[1]][start_pos[2]] = 0
    heapq.heappush(pq, (0, start_pos[0], start_pos[1], start_pos[2]))

    while pq:
        cnt, cur_r, cur_c, cur_d = heapq.heappop(pq)
        if cnt > dp[cur_r][cur_c][cur_d]: continue
        if cur_r == end_pos[0] and cur_c == end_pos[1] and cur_d == end_pos[2]:
            return cnt
        
        # 해당 위치에서 방향 전환
        nd = -1
        if cur_d == 0 or cur_d == 1:
            nd = [2, 3]
        else:
            nd = [0,1]

        for d in nd:
            if dp[cur_r][cur_c][d] > cnt + 1:
                dp[cur_r][cur_c][d] = cnt + 1
                heapq.heappush(pq, (cnt+1, cur_r, cur_c, d))

        # 1 ~ 3만큼 이동
        for k in range(1, 4):
            nr = cur_r + (dirs[cur_d][0] * k)
            nc = cur_c + (dirs[cur_d][1] * k)

            if 1 <= nr <= N and 1 <= nc <= M and grid[nr][nc] == 0:
                if dp[nr][nc][cur_d] > cnt + 1:
                    dp[nr][nc][cur_d] = cnt + 1
                    heapq.heappush(pq, (cnt+1, nr, nc, cur_d))
            else:
                break
    return -1

print(bfs())