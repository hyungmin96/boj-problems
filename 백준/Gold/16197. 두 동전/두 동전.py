import sys
from collections import deque
input = sys.stdin.readline

N, M = map(int, input().strip().split())
grid = [[] for _ in range(N)]
coins = []
for r in range(N):
    val = input().strip()
    for c in range(M):
        if val[c] == 'o':
            coins.append((r, c))
        grid[r].append(val[c])

dirs = [[-1,0],[0,1],[1,0],[0,-1]]
v = [[[[False] * M for _ in range(N)] for _ in range(M)] for _ in range(N)]
def bfs(r1, c1, r2, c2):
    q = deque()

    v[r1][c1][r2][c2] = True
    q.append((r1, c1, r2, c2, 0))

    while q:
        cur_r1, cur_c1, cur_r2, cur_c2, cnt = q.popleft()
        if cnt >= 10: continue
        for d in range(4):
            flag1, flag2 = False, False
            nr1, nc1, nr2, nc2 = cur_r1 + dirs[d][0], cur_c1 + dirs[d][1], cur_r2 + dirs[d][0], cur_c2 + dirs[d][1]
            
            if 0 > nr1 or nr1 >= N or 0 > nc1 or nc1 >= M:
                flag1 = True
            if 0 > nr2 or nr2 >= N or 0 > nc2 or nc2 >= M:
                flag2 = True

            if flag1 and flag2:
                continue

            if flag1 or flag2:
                return cnt+1
            
            if grid[nr1][nc1] == '#':
                nr1, nc1 = cur_r1, cur_c1
            if grid[nr2][nc2] == '#':
                nr2, nc2 = cur_r2, cur_c2

            if v[nr1][nc1][nr2][nc2]: continue
            v[nr1][nc1][nr2][nc2] = True
            q.append((nr1, nc1, nr2, nc2, cnt + 1))

    return -1

print(bfs(coins[0][0], coins[0][1], coins[1][0], coins[1][1]))