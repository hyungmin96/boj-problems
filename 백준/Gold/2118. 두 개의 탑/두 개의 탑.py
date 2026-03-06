import sys
input = sys.stdin.readline

num = int(input())
arr = [int(input().strip()) for _ in range(num)]

r, cur, ans = 0, 0, 0
total = sum(arr)
for l in range(num):
    while(cur < total // 2):
        cur += arr[r % num]
        r += 1

    ans = max(ans, min(cur, total - cur))
    cur -= arr[l]

print(ans)