import sys
input = sys.stdin.readline
oper_w = {
    "+" : 0,
    "-" : 0,
    "*" : 1,
    "/" : 1
}
ans, stk, val, rank = [],[], input().strip(), 0
for ch in list(val):
    if ch in oper_w:
        w = oper_w[ch] + rank
        while len(stk) > 0 and w <= stk[-1][0]:
            ans.append(stk.pop()[1])
        
        stk.append((w, ch))
    else:
        if ch == '(':
            rank += 2
        elif ch == ')':
            rank -= 2
        else:
            ans.append(ch)

while stk:
    ans.append(stk.pop()[1])

print("".join(map(str, ans)))