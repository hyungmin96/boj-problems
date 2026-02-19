import sys
input = sys.stdin.readline

T = int(input())
for _ in range(T):

    W, N = map(int, input().strip().split())
    cur, tmp, dist = 0, 0, 0

    for i in range(N):
        x, w = map(int, input().strip().split())
        dist += (x - cur)
        cur = x

        if tmp + w == W:
            tmp, cur = 0, 0
            dist += x
        elif tmp + w > W:
            tmp = w
            dist += (2 * x)
        else:
            tmp += w

    print(dist + cur)