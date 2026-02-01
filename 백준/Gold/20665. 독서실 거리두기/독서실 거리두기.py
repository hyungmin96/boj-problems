import sys, heapq
input = sys.stdin.readline

def convert_minute(val):
    hour = int(val[:2]) * 60
    minute = int(val[2:])

    return hour + minute


N, T, P = map(int, input().strip().split())
enter_q, state_q, v = [],[], set()

for _ in range(T):
    s, e = map(str, input().strip().split())
    start_time, end_time = convert_minute(s), convert_minute(e)

    heapq.heappush(enter_q, (start_time, end_time))

cnt = 0
while enter_q:
    enter_time, exit_time = heapq.heappop(enter_q)
    while len(state_q) > 0 and state_q[0][0] <= enter_time:
        v.remove(state_q[0][1])
        heapq.heappop(state_q)

    dist, cur_idx = 0, 1
    for i in range(1, N+1):
        if i in v: continue
        cur_dist = 987654321
        for j in range(len(state_q)):
            cur_dist = min(cur_dist, abs(state_q[j][1] - i))

        if cur_dist > dist:
            dist = cur_dist
            cur_idx = i

    if cur_idx == P:
        cnt += exit_time-enter_time

    v.add(cur_idx)
    heapq.heappush(state_q, (exit_time, cur_idx))

ans = convert_minute("2100") - convert_minute("0900") - cnt
print(ans)
