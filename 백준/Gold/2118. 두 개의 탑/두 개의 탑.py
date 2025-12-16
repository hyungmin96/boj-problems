import sys
input = sys.stdin.readline

def solve():
    N = int(input())

    arr = [int(input()) for _ in range(N)]
    total_dist = sum(arr)

    ans, cur_dist, r = 0, 0, 0
    for l in range(N):
        while cur_dist < total_dist - cur_dist:
            cur_dist += arr[r]
            r = (r + 1) % N

        ans = max(ans, min(cur_dist, total_dist - cur_dist))
        cur_dist -= arr[l]

    return ans

print(solve())