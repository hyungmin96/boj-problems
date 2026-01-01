import sys
input = sys.stdin.readline

N, M = map(int, input().split())
visit = [True] * (N+1)
if M > 0:
    for day in list(map(int, input().split())):
        visit[day] = False

dp = [[float('inf')] * (N+1) for _ in range(40)]
def dfs(depth, coupon):
    if depth > N:
        return 0
    
    if dp[coupon][depth] != float('inf'):
        return dp[coupon][depth]

    res = 999999999

    # 방문하지 않는 날인 경우
    if not visit[depth]:
        res = min(res, dfs(depth+1, coupon))
    else:
        # 쿠폰 사용
        if coupon >= 3:
            res = dfs(depth+1, coupon - 3)
        # 1일권 사용
        res = min(res, dfs(depth + 1, coupon) + 10000)
        # 3일권 사용
        res = min(res, dfs(depth + 3, coupon + 1) + 25000)
        # 5일권 사용
        res = min(res, dfs(depth + 5, coupon + 2) + 37000)

    dp[coupon][depth] = min(res, dp[coupon][depth])
    return dp[coupon][depth]

print(dfs(1, 0))