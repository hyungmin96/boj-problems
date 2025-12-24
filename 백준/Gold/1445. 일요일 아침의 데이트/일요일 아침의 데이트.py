import sys, heapq
input = sys.stdin.readline

N, M = map(int, input().split())
dp = [[float('inf')] * M for _ in range(N)]
cnt = [[0] * M for _ in range(N)]
grid = [[''] * M for _ in range(N)]
v = [[False] * M for _ in range(N)]

start_pos, end_pos = (-1,-1), (-1,-1)
dirs = [[-1,0],[0,1],[1,0],[0,-1]]

for i in range(N):
    value = list(input().strip())
    for j in range(len(value)):
        grid[i][j] = value[j]
        if value[j] == 'F':
            end_pos = (i, j)
        elif value[j] == 'S':
            start_pos = (i, j)
        elif value[j] == 'g':
            for d in range(4):
                nr = i + dirs[d][0]
                nc = j + dirs[d][1]
                if 0 <= nr < N and 0 <= nc < M:
                    cnt[nr][nc] = 1
            

def solution():
    pq = []
    heapq.heappush(pq, (0, 0, start_pos[0], start_pos[1])) # 통과 수, 지난 수, 행, 열
    v[start_pos[0]][start_pos[1]] = True
    
    while pq:
        pass_cnt, close_cnt, r, c = heapq.heappop(pq)
        if grid[r][c] == 'F':
            print(pass_cnt, close_cnt)
            return

        for d in range(4):
            nr = r + dirs[d][0]
            nc = c + dirs[d][1]
            tmp_pass_cnt = pass_cnt
            tmp_close_cnt = close_cnt
            if 0 <= nr < N and 0 <= nc < M and not v[nr][nc]:
                v[nr][nc] = True
                if grid[nr][nc] != 'F':
                    if grid[nr][nc] == 'g':
                        tmp_pass_cnt = pass_cnt + 1
                    elif grid[nr][nc] != 'g':
                        tmp_close_cnt += cnt[nr][nc]

                heapq.heappush(pq, (tmp_pass_cnt, tmp_close_cnt, nr, nc))

solution()


