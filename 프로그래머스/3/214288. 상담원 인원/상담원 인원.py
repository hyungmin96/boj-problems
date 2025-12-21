import heapq
def solution(k, n, reqs):
    answer = 0
    n -= k
    arr = [0] + [1 for _ in range(k)]
    def get_time(arr):
        ret = 0
        q = [[] for _ in range(k+1)]
        for next_s, time, k_idx in reqs:
            while q[k_idx] and q[k_idx][0] <= next_s:
                heapq.heappop(q[k_idx])
                    
            if len(q[k_idx]) >= arr[k_idx]:
                ret += q[k_idx][0] - next_s
                heapq.heappush(q[k_idx], q[k_idx][0] + time)
                heapq.heappop(q[k_idx])
            else:
                heapq.heappush(q[k_idx], next_s + time)
        return ret
            
    def dfs(depth, idx, arr):
        if depth == n:
            return get_time(arr)
        
        ret = float('inf')
        for i in range(idx, k+1):
            arr[i] += 1
            ret = min(ret, dfs(depth + 1, i, arr))
            arr[i] -= 1
        
        return ret
    return dfs(0, 1, arr)