import sys
input = sys.stdin.readline

N = int(input())

answer = 0
grid = []
for i in range(N):
    lst = list(map(int, input().split()))
    for j in range(N):
        answer = max(answer, lst[j])
    grid.append(lst)


def left(grid):
    global N, answer
    for row in range(N):
        c = 0
        while c+1 < N:
            if grid[row][c] != 0:
                next_c = c+1
                while c+1 < N and next_c < N:
                    if grid[row][c] == grid[row][next_c]:
                        grid[row][next_c] = 0
                        grid[row][c] *= 2
                        answer = max(answer, grid[row][c])
                        break
                    elif grid[row][next_c] == 0:
                        next_c += 1
                    else:
                        break
            c+=1

    for row in range(N):
        cnt = 0
        for i in range(N):
            if grid[row][i] == 0:
                cnt += 1
            else:
                tmp = grid[row][i]
                grid[row][i] = 0
                grid[row][i-cnt] = tmp

def right(grid):
    global N,answer
    for row in range(N):
        c = N - 1
        while c-1 >= 0:
            if grid[row][c] != 0:
                next_c = c-1
                while c-1 >= 0 and next_c >= 0:
                    if grid[row][c] == grid[row][next_c]:
                        grid[row][next_c] = 0
                        grid[row][c] *= 2
                        answer = max(answer, grid[row][c])
                        break
                    elif grid[row][next_c] == 0:
                        next_c -= 1
                    else:
                        break
            c-=1

    for row in range(N):
        cnt = 0
        for i in range(N-1, -1, -1):
            if grid[row][i] == 0:
                cnt += 1
            else:
                tmp = grid[row][i]
                grid[row][i] = 0
                grid[row][i+cnt] = tmp

def down(grid):
    global N, answer
    for col in range(N):
        r = N - 1
        while r-1 >= 0:
            if grid[r][col] != 0:
                next_r = r-1
                while r-1 >= 0 and next_r >= 0:
                    if grid[r][col] == grid[next_r][col]:
                        grid[next_r][col] = 0
                        grid[r][col] *= 2
                        answer = max(answer, grid[r][col])
                        break
                    elif grid[next_r][col] == 0:
                        next_r -= 1
                    else:
                        break
            r-=1

    for col in range(N):
        cnt = 0
        for i in range(N-1, -1, -1):
            if grid[i][col] == 0:
                cnt += 1
            else:
                tmp = grid[i][col]
                grid[i][col] = 0
                grid[i+cnt][col] = tmp

def up(grid):
    global N, answer
    for col in range(N):
        r = 0
        while r+1 < N:
            if grid[r][col] != 0:
                next_r = r+1
                while r+1 < N and next_r < N:
                    if grid[r][col] == grid[next_r][col]:
                        grid[next_r][col] = 0
                        grid[r][col] *= 2
                        answer = max(answer, grid[r][col])
                        break
                    elif grid[next_r][col] == 0:
                        next_r += 1
                    else:
                        break
            r+=1

    for col in range(N):
        cnt = 0
        for i in range(N):
            if grid[i][col] == 0:
                cnt += 1
            else:
                tmp = grid[i][col]
                grid[i][col] = 0
                grid[i-cnt][col] = tmp

def change_grid(d, grid):
    if d == 0:
        up(grid)
    elif d == 1:
        right(grid)
    elif d == 2:
        down(grid)
    else:
        left(grid)

def copy(grid):
    tmp = [[0] * N for _ in range(N)]
    for r in range(N):
        for c in range(N):
            tmp[r][c] = grid[r][c]

    return tmp

def dfs(depth, grid):
    if depth == 5:
        return
    
    for d in range(4):
        tmp = copy(grid)
        change_grid(d, tmp)
        dfs(depth + 1, tmp)
        
dfs(0, grid)
print(answer)