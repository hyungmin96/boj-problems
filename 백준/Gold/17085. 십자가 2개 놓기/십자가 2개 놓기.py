
answer = 1


def check(depth, size, flag, v):
    r = int(depth / M)
    c = int(depth % M)

    for i in range(0, size + 1):
        v[r+i][c] = flag
        v[r-i][c] = flag
        v[r][c+i] = flag
        v[r][c-i] = flag

def isPossible(depth, size, grid, v):
    r = int(depth / M)
    c = int(depth % M)

    if r - size < 0 or c - size < 0 or r + size >= N or c + size >= M:
        return False
    
    for i in range(0, size + 1):
        if grid[r + i][c] != '#' or grid[r - i][c] != '#' or grid[r][c + i] != '#' or grid[r][c - i] != '#':
            return False
        
        if v[r + i][c] or v[r - i][c] or v[r][c + i] or v[r][c - i]:
            return False

    return True
            
def dfs(depth, grid, v, cur, cnt):
    global answer
    if cnt >= 2 or depth == N * M - 1:
        answer = max(answer, cur)
        return    
    
    size = 1
    while(isPossible(depth, size, grid, v)):
        check(depth, size, True, v)
        dfs(depth + 1, grid, v, cur * (size * 4 + 1), cnt + 1)
        check(depth, size, False, v)
        size += 1

    dfs(depth + 1, grid, v, cur, cnt)

N, M = map(int, input().split())

grid = [[] for _ in range(N)]
v = [[False for _ in range(M)] for _ in range(N)]

for r in range(N):
    grid[r] = input()

dfs(0, grid, v, 1, 0)
print(answer)
