import sys
input = sys.stdin.readline

val = input().strip()
bridge = []
bridge.append(list(input().strip()))
bridge.append(list(input().strip()))

N = len(bridge[0])
dp = [[[-1] * N for _ in range(len(val))] for _ in range(2)]

def dfs(depth, flag, pos):
    if depth >= len(val):
        return 1
    
    if pos >= N:
        return 0
    
    if dp[flag][depth][pos] != -1:
        return dp[flag][depth][pos]
    
    res = 0
    dp[flag][depth][pos] = 0
    for i in range(pos, N):
        if val[depth] == bridge[flag][i]:
            res += dfs(depth+1, (flag + 1) % 2, i+1)

    dp[flag][depth][pos] = res
    return res

ret1 = dfs(0, 0, 0)

dp = [[[-1] * N for _ in range(len(val))] for _ in range(2)]
ret2 = dfs(0, 1, 0)

print(ret1+ret2)