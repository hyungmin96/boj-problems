import sys
input = sys.stdin.readline

T = int(input())
num = int(input())
coins = []
for _ in range(num):
    coin, nums = map(int, input().split())
    coins.append((coin, nums))

memo = [[-1] * (T+1) for _ in range(num)]
def dfs(depth, cur):
    if cur == T:
        return 1
    
    if depth == num:
        return 0
    
    if memo[depth][cur] != -1:
        return memo[depth][cur]
    
    res = 0
    coin, count = coins[depth]
    
    for i in range(count+1):
        next_sum = cur + (coin * i)
        if next_sum <= T:
            res += dfs(depth + 1, next_sum)
        else:
            break

    memo[depth][cur] = res
    return res

print(dfs(0, 0))
