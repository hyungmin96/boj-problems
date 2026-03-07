import sys
input = sys.stdin.readline

value = input().strip()

ans = []
stk, priority, flag = [], {}, 0
priority["+"] = 0
priority["-"] = 0
priority["*"] = 1
priority["/"] = 1
for i in range(len(value)):
    cur = value[i]
    if cur == '(':
        flag += 2
    elif cur == ')':
        flag -= 2
    else:
        if 'A' <= cur <= 'Z':
            ans.append(cur)
        else:
            p = priority[cur] + flag
            while stk and p <= stk[-1][0]:
                ans.append(value[stk.pop()[1]])
            stk.append((p, i))

while stk:
    ans.append(value[stk.pop()[1]])

print("".join(map(str, ans)))