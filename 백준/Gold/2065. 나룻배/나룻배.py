import sys, heapq
input = sys.stdin.readline

M, T, N = map(int, input().strip().split())

cur, point, pq = 0, 0, [[],[]]
for i in range(N):
    time, des = map(str, input().strip().split())
    time = int(time)
    if des == 'left': 
        des = 0
    else:
        des = 1

    heapq.heappush(pq[des], [time, des, i])

ans, cur_time, cur_des = [], 0, 0
while pq[0] or pq[1]:
    cnt, tmp1, tmp2 = 0, [], []
    # 현재 위치에서 태울 수 있는 경우.
    if pq[cur_des] and cur_time >= pq[cur_des][0][0]:
        while(pq[cur_des] and cnt < M and cur_time >= pq[cur_des][0][0]):
            cnt += 1
            _, _, idx = heapq.heappop(pq[cur_des])
            tmp2.append(idx)
        
    # 왼쪽 선착장의 사람이 먼저 도착하는지 비교.
    elif not pq[1] or (pq[0] and pq[1] and pq[0][0][0] < pq[1][0][0]):
        # 현재 시간에 태울 수 있는 사람이 없는 경우.
        if cur_time < pq[0][0][0]:
            # 다음 도착하는 사람이 올 때 까지 대기.
            cur_time += max(0, (pq[0][0][0] - cur_time))

        if cur_des != 0:
            # 현재 나룻배가 왼쪽에 없는 경우.
            cur_des = (cur_des + 1) % 2
            cur_time += T

        while(pq[0] and cur_time >= pq[0][0][0] and cnt < M):
            cnt += 1
            _, _, idx = heapq.heappop(pq[0])
            tmp2.append(idx)

    # 오른쪽 선착장 사람이 먼저 도착하는 경우.
    elif not pq[0] or (pq[0] and pq[1] and pq[0][0][0] > pq[1][0][0]):
        # 현재 시간에 태울 수 있는 사람이 없는 경우.
        if cur_time < pq[1][0][0]:
            # 다음 도착하는 사람이 올 때 까지 대기.
            cur_time += max(0, (pq[1][0][0] - cur_time))

        if cur_des != 1:
            cur_des = (cur_des + 1) % 2
            cur_time += T
            
        while(pq[1] and cur_time >= pq[1][0][0] and cnt < M):
            cnt += 1
            _, _, idx = heapq.heappop(pq[1])
            tmp2.append(idx)

    # 목적지 이동
    cur_time += T
    cur_des = (cur_des + 1) % 2

    for i in range(cnt):
        ans.append((cur_time, tmp2[i]))

ans.sort(key=lambda x: (x[1]))

for i in range(len(ans)):
    print(ans[i][0])