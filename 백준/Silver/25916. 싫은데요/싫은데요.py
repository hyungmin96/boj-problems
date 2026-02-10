import sys
input = sys.stdin.readline

N, M = map(int, input().strip().split())
arr = list(map(int, input().strip().split()))

ans, cur, left = 0, 0, 0
for i in range(N):
    cur += arr[i]
    while cur > M:
        cur -= arr[left]
        left += 1

    if cur <= M:
        ans = max(ans, cur)

print(ans)