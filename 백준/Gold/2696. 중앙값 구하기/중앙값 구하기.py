import sys, heapq
input = sys.stdin.readline

ret = []
T = int(input())
for _ in range(T):
    M = int(input())
    arr = []
    while M > 0:
        tmp = list(map(int, input().strip().split()))
        M -= len(tmp)
        arr += tmp
    
    ans = []
    mid, min_q, max_q = arr[0], [], [] # min_q 원소는 가장 큰 값을 빼기위해 - 처리
    for i in range(len(arr)):
        if i > 0:
            if mid < arr[i]:
                heapq.heappush(max_q, arr[i])
            else:
                heapq.heappush(min_q, -arr[i])

        if (i+1) % 2 != 0:
            # 홀수번째 인 경우
            if len(min_q) == 0 and len(max_q) == 0:
                ans.append(mid)
            else:
                if len(min_q) == len(max_q):
                    ans.append(mid)
                else:
                    if len(min_q) > len(max_q):
                        heapq.heappush(max_q, mid)
                        mid = -heapq.heappop(min_q)
                    else:
                        heapq.heappush(min_q, -mid)
                        mid = heapq.heappop(max_q)

                    ans.append(mid)
    
    ret.append((len(ans), ans))


for length, arr in ret:
    print(length)
    s, e = 0, 10
    while length > 0:
        print(" ".join(map(str, arr[s:e])))
        length -= 10
        s += 10
        e += 10