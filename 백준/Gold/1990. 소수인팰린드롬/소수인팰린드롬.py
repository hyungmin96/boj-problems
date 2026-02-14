import sys, math
from collections import deque
input = sys.stdin.readline

ans = []
a, b = map(int, input().strip().split())

def is_prime(p):
    for i in range(2, int(math.sqrt(p)+1)):
        if p % i == 0:
            return False
        
    return True

q = deque([''])
for i in range(10):
    q.append(str(i))

palindrome_list = []
while q:
    cur = q.popleft()
    if len(cur) > 8:
        continue
    if cur != '':
        palindrome_list.append(int(cur))
    for i in range(10):
        q.append(str(i) + cur + (str(i)))
palindrome_list = list(set(palindrome_list))
palindrome_list.sort()
for p in palindrome_list:
    if a > p: continue
    if b < p: break

    if is_prime(p):
        ans.append(p)

if len(ans) > 0:
    print("\n".join(map(str, ans)))
print(-1)