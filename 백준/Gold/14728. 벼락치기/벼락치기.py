import sys
input = sys.stdin.readline

N, T = map(int, input().split())
study_list = [list(map(int, input().split())) for _ in range(N)]

dp = [0] * (T + 1)
for time_cost, score in study_list:
    for current_time in range(T, time_cost - 1, -1):
        dp[current_time] = max(dp[current_time], dp[current_time - time_cost] + score)

print(dp[T])