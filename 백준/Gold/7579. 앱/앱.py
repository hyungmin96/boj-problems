import sys
input = sys.stdin.readline
sys.setrecursionlimit(100001)

N, M = map(int, input().split())
mem_list = list(map(int, input().split()))
cost_list = list(map(int, input().split()))

dp = [[0] * (10001) for _ in range(N+1)]

# dp[종료할 앱][비용] = 얻을 수 있는 최대 메모리
ans = 987654321
for i in range(1, N+1): # 앱
    mem = mem_list[i-1]
    cost = cost_list[i-1]
    for j in range(10001): # 비용
        dp[i][j] = max(dp[i][j], dp[i-1][j])
        if j - cost >= 0:
            dp[i][j] = max(dp[i][j], dp[i-1][j-cost] + mem)

        if dp[i][j] >= M:
            ans = min(ans, j)

print(ans)