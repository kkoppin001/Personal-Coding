.ORIG x3000
LD R6, STACK_PTR	; R6 = x6000

; int num
ADD R6, R6, #-1		; R6 = x5FFF
AND R0, R0, #0		; R0 = x0000
ADD R0, R0, #6		; R0 = x0006
STR R0, R6, #0

; set frame pointer
ADD R5, R6, #0		; R5 = x5FFF

; int result
ADD R6, R6, #-1		; R6 = x5FFE
AND R0, R0, #0		; R0 = x0000
STR R0, R6, #0

; int n (factorial(6))
ADD R6, R6, #-1		; R6 = x5FFD
LDR R0, R5, #0		; R0 = num
STR R0, R6, #0

JSR FACTORIAL

LDR R0, R6, #0		; R0 = previous return value
ADD R6, R6, #1		; R6 = x5FFD
ADD R6, R6, #1		; R6 = x5FFE

; int result
STR R0, R6, #0

ADD R6, R6, #1		; R6 = x5FFF
ADD R6, R6, #1		; R6 = x6000

; mains return value
AND R0, R0, #0		; R0 = 0
STR R0, R6, #0

HALT

FACTORIAL

ADD R6, R6, #-1		; R6 = x5FFC

; return address
ADD R6, R6, #-1		; R6 = x5FFB
STR R7, R6, #0

; previous frame pointer
ADD R6, R6, #-1		; R6 = x5FFA
STR R5, R6, #0

; save R3
ADD R6, R6, #-1		; R6 = x5FF9
STR R3, R6, #0

; save R2
ADD R6, R6, #-1		; R6 = x5FF8
STR R2, R6, #0

; save R1
ADD R6, R6, #-1		; R6 = x5FF7
STR R1, R6, #0

; save R0
ADD R6, R6, #-1		; R6 = x5FF6
STR R0, R6, #0

ADD R5, R6, #0		; R6 = x5FF6

LDR R0, R5, #7		; R0 = n
ADD R0, R0, #-1		; R0 = n - 1
BRnz BASE_CASE

ADD R6, R6, #-1		; R6 = x5FF5
STR R0, R6, #0

JSR FACTORIAL

LDR R0, R6, #0		; R0 = previous return value
ADD R6, R6, #1		; R6 = x5FF5
ADD R6, R6, #1		; R6 = x5FF4

LDR R1, R5, #7		; R1 = n

AND R2, R2, #0		; R2 = 0

MULTIPLY
ADD R2, R1, R2
ADD R0, R0, #-1
BRp MULTIPLY

; R2 = n * previous return value

STR R2, R5, #6
BRnzp POP

BASE_CASE
AND R0, R0, #0		; R0 = 0
ADD R0, R0, #1		; R0 = 1
STR R0, R5, #6

POP

; pop R0
LDR R0, R6, #0
ADD R6, R6, #1		; R6 = x5FF7

; pop R1
LDR R1, R6, #0
ADD R6, R6, #1		; R6 = x5FF8

; pop R2
LDR R2, R6, #0
ADD R6, R6, #1		; R6 = x5FF9

; pop R3
LDR R3, R6, #0
ADD R6, R6, #1		; R6 = x5FFA

; pop R5
LDR R5, R6, #0
ADD R6, R6, #1		; R6 = x5FFB

; pop R7
LDR R7, R6, #0
ADD R6, R6, #1		; R6 = x5FFC

RET

STACK_PTR .FILL x6000
.END