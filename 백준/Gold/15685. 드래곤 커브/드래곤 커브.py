import sys
input = sys.stdin.readline

N = int(input())
grid = [[0] * 101 for _ in range(101)]
dirs = [[0,1],[-1,0],[0,-1],[1,0]]

for _ in range(N):
    x, y, d, g = map(int, input().strip().split())
    grid[y][x] = 1
    y, x = y+dirs[d][0], x+dirs[d][1]
    grid[y][x] = 1
    tmp_list = [d]
    for i in range(g):
        size = len(tmp_list)
        for j in range(size-1, -1, -1):
            nd = (tmp_list[j] + 1) % 4
            y += dirs[nd][0]
            x += dirs[nd][1]
            grid[y][x] = 1

            tmp_list.append(nd)

ans = 0
for r in range(100):
    for c in range(100):
        if grid[r][c] == 1 and grid[r+1][c] == 1 and grid[r][c+1] == 1 and grid[r+1][c+1] == 1:
            ans += 1

print(ans)