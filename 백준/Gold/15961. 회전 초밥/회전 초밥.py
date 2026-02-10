import sys
input = sys.stdin.readline

N, D, K, C = map(int, input().strip().split())
arr = []
for _ in range(N):
    arr.append(int(input().strip()))

ans, v, left, length = 0, {}, 0, 0

v[C] = 1
for i in range(N + K - 1):
    if not arr[i % N] in v:
        v[arr[i % N]] = 0
    v[arr[i % N]] += 1
    length += 1

    if length >= K:
        ans = max(ans, len(v))

        v[arr[left]] -= 1
        if v[arr[left]] == 0:
            del v[arr[left]]
        left += 1
        length -= 1

print(ans)