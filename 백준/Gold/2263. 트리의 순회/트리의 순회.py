import sys
sys.setrecursionlimit(100000)
def dfs(in_l, post_l, size, in_order, post_order, mapping):
    if(size <= 0):
        return
    
    root = post_order[size + post_l - 1]
    root_idx = mapping[root]

    left_size = root_idx - in_l
    right_size = size - left_size - 1

    print(root, end = ' ')
    dfs(in_l, post_l, left_size, in_order, post_order, mapping)
    dfs(root_idx + 1, post_l + left_size, right_size, in_order, post_order, mapping)

N = int(sys.stdin.readline())

in_order = list(map(int, sys.stdin.readline().split()))
post_order = list(map(int, sys.stdin.readline().split()))

mapping = [0] * (N + 1)
for i in range(N):
    mapping[in_order[i]] = i

dfs(0, 0, N, in_order, post_order, mapping)