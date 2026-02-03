import sys
input = sys.stdin.readline

N = int(input().strip())
P = list(map(int, input().strip().split()))
S = list(map(int, input().strip().split()))

ans, arr, cards = 0, [i for i in range(N)], [i for i in range(N)]
while True:
    flag = True
    for i in range(N):
        if P[cards[i]] != i % 3:
            flag = False
            break

    if flag:
        print(ans)
        break

    ans += 1
    for i in range(N):
        arr[S[i]] = cards[i]
    cards = arr[:]

    if cards == [i for i in range(N)]:
        print(-1)
        break
