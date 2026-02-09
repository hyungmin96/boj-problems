import sys
input = sys.stdin.readline

ans = []
T = int(input().strip())
for _ in range(T):
    W, N = map(int, input().strip().split())
    arr = []
    for _ in range(N):
        d, w = map(int, input().strip().split())
        arr.append((d, w))

    total_dist, idx, cur = 0, 0, 0
    while idx < len(arr):
        d, w = arr[idx]
        if idx < len(arr) - 1 and cur + w < W:
            cur += w
        else:
            if cur + w > W:
                idx -= 1

            cur = 0
            total_dist += d * 2
        idx += 1

    ans.append(total_dist)

print("\n".join(map(str, ans)))
