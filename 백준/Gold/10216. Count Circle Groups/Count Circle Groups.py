import sys
input = sys.stdin.readline

T = int(input().strip())
ans = []

for _ in range(T):
    N = int(input().strip())
    p, arr, tmp = [i for i in range(N)], [], set()
    for _ in range(N):
        arr.append(tuple(map(int, input().strip().split())))


    def find(a):
        if a == p[a]:
            return a
        
        p[a] = find(p[a])
        return p[a]
    
    def union(a, b):
        a = find(a)
        b = find(b)

        if a != b:
            if a < b:
                p[b] = a
            else:
                p[a] = b

    for i in range(N):
        for j in range(i+1, N):
            y1, x1, r1 = arr[i]
            y2, x2, r2 = arr[j]

            if (y1-y2)**2 + (x1-x2) ** 2 <= (r1+r2)**2:
                union(i, j)

    for i in range(len(p)):
        tmp.add(find(p[i]))

    ans.append(len(tmp))

print("\n".join(map(str, ans)))