; Kalli Koppin Calculator
.ORIG x3000
CONTINUE_PROGRAM
      JSR CLEAR_REGISTERS
      JSR CLEAR_ARRAY
      AND R0, R0, #0
      ST R0, num_R

      ; Get first number
      LEA R0, PROMPT1
      PUTS
      JSR READ_INPUT
      LD R1, TEMPORARY NUMBER
      ST R1, NUM_X

      ; reset temp variable
      AND R0, R0, #0
      ST R0, TEMPORARY NUMBER

      ; Get second number
      LEA R0, PROMPT2
      PUTS
      JSR READ_INPUT
      LD R2, TEMPORARY NUMBER
      ST R2, NUM_Y

      AND R0, R0, #0
      ST R0, TEMPORARY NUMBER

      ; prompt for operator
      LEA R0, PROMPT3
      PUTS 
      GETC                
      OUT
      AND R6, R6, #0
      LD R1, NUM_X
      LD R2, NUM_Y
      LEA R5, CODES_ARRAY    ; load CODES_ARRAY address

      LDR R3, R5, #0        ; loads first symbol from R7 into R3
      ADD R4, R0, R3        ; add contents of R0 (user input) and R3
      BRz ADDITION

      LDR R3, R5, #1
      ADD R4, R0, R3
      BRz SUBTRACT

      LDR R3, R5, #2
      ADD R4, R0, R3
      BRz MULTIPLY

      LDR R3, R5, #3
      ADD R4, R0, R3
      BRz DIVISION_F

OpComplete
      LEA R0, result1
      PUTS
      ADD R6, R6, #0
      BRn PRINT_NEG
      ST R6, NUM_R
      JSR SIZE
      JSR NUMBER_TO_ASCII_CONVERSION
      LEA R0, OUTPUTARRAY
      PUTS
      LEA R0, END_OF_STRING
      PUTS
      GETC
      OUT
      LD R5, MINUS_30
      ADD R0, R0, R5
      Brz CONTINUE_PROGRAM
      ; End of program
        HALT

num_R         .FILL     #0
NUM_X         .FILL     #0
NUM_Y         .FILL     #0
TEMPORARY NUMBER      .FILL     #0
PROMPT1       .STRINGZ  "\nInput first number: "
PROMPT2       .STRINGZ  "\nInput second number: "
PROMPT3       .STRINGZ  "\nInput operation (+, -, *, /): "
result1       .STRINGZ  "\nResult = "
END_OF_STRING       .STRINGZ  "\nContinue? Yes = 0, No = 1: "

CODES_ARRAY    .FILL     #-43  ; '+'
              .FILL     #-45  ; '-'
              .FILL     #-42  ; '*'
              .FILL     #-47  ; '/'

READ_INPUT
  ST R7, saveR7
  LEA R3, inputArray      ; R3 <- pointer to inputArray
  AND R4, R4, #0          ; init R4 to 0
  AND R5, R5, #0          ; init R5 to 0
  LoopREAD_INPUT GETC
    ADD R6, R0, #-10      ; check for return key
    BRz DoneREAD_INPUT
    OUT                   ; print character to screen
    STR R0, R3, #0        ; store char into inputArray
    ADD R4, R4, #1        ; inc++ size value
    ST R4, inputSize      ; store R4 into inputSize variable
    ADD R3, R3, #1        ; inc++ array index
    ADD R5, R4, #-4       ; stop when 4 char long
    BRn LoopREAD_INPUT     ; loop back for next character
  DoneREAD_INPUT
    LEA R3, inputArray    ; R3 <- pointer to inputArray
    LEA R4, constArray    ; R4 <- pointer to constArray
    LD R5, MINUS_30        ; R5 <- contains hex 30
    LD R6, inputSize      ; R6 <- contains length of input
    ADD R6, R6, #-1       ; length - 1
  LoopREAD_INPUT2
    LDR R1, R3, #0        ; load ASCII digit from inputArray
    ADD R1, R1, R5        ; R1 <- contains integer
    ADD R2, R4, R6        ; R2 <- contins address of constant
    LDR R2, R2, #0        ; R2 <- load the number from the arrary
    JSR READ_INPUTExpand   ; R1 <- Integer, R2 <- Constant
    LD R1, num_R          ; Load mult result into R1
    LD R0, TEMPORARY NUMBER       ; Load the current num into R0
    ADD R0, R0, R1        ; Add together
    ST R0, TEMPORARY NUMBER       ; Store the sum back into the temp num
    ADD R3, R3, #1        ; increment pointer in inputArray
    ADD R6, R6, #-1       ; decrement size
    BRzp LoopREAD_INPUT2   ; loop back and continue to MULTIPLY out
  LD R7, saveR7
  RET

MINUS_30       .FILL     x-30
inputArray    .BLKW     #5

READ_INPUTExpand
  ST R6, saveR6
  ST R4, saveR4
  AND R6, R6, #0
  AND R4, R4, #0
  ADD R4, R4, R2  ; duplicate R2 so it remains unchanged
  Mult
    ADD R6, R6, R1
    ADD R4, R4, #-1
  BRp Mult
  ST R6, num_R
  LD R4, saveR4
  LD R6, saveR6
  RET

NUMBER_TO_ASCII_CONVERSION ; prints number in R1
  ST R7, saveR7
  LD R3, outputSize       ; load size into R3

  LEA R6, outputArray     ; load address of output into R6
  Convert_Loop
    LEA R5, constArray    ; load address of constArray -> R5
    ADD R3, R3, #-1       ; decrement size of num
    ADD R5, R5, R3        ; R5 <- R5 + R3
    LDR R2, R5, #0        ; load const from constArray at index R5
    LD R1, num_R          ; load the number into R1
    ST R3, saveR3
    ST R4, saveR4
    ST R5, saveR5
    ST R6, saveR6
    JSR Division          ; divide
    LD R3, saveR3
    LD R4, saveR4
    LD R5, saveR5
    LD R6, saveR6
    LD R0, convert_quot   ; load quotient into R0
    LD R5, num_remainder  ; load remainder into R5
    LD R4, plus30         ; load ASCII converter into R4
    ADD R0, R0, R4        ; Convert quotient into ASCII
    STR R0, R6, #0        ; store R0 (ASCII) into index at R6
    ADD R6, R6, #1        ; increment index at R6
    ADD R3, R3, #0        ; break on R3 (size)
    ST R5, num_R
    BRp Convert_Loop
  ADD R0, R0, #0
  ST R0, outputSize
  LD R7, saveR7
  RET
plus30        .FILL     x30
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; LOOPS
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
ADDITION
  ADD R6, R2, R1
  JSR OpComplete

Subtract
  NOT R2, R2
  ADD R2, R2, #1
  ADD R6, R1, R2
  JSR OpComplete

MULTIPLY
  AND R6, R6, #0  ; Quotient
  AND R3, R3, #0  ; init Y
  ADD R3, R2, #0  ; add Y to R3
  Mul
    ADD R6, R6, R1
    ADD R3, R3, #-1
  Brp Mul
  JSR OpComplete

DIVISION_F
  JSR Division
  JSR OpComplete

Division
  AND R3, R3, #0  ; init R3
  ADD R3, R1, #0  ; add X to R3
  AND R4, R4, #0  ; init Y
  ADD R4, R2, #0  ; add Y to R4
  AND R6, R6, #0  ; Quotient
  NOT R4, R4
  ADD R4, R4, #1  ; Invert R4
  Div
    ADD R3, R3, R4  ; X = X - Y
    BRn EndDiv
    ADD R6, R6, #1
    ST R3, num_remainder
    Br Div
  EndDiv
    ST R6, convert_quot     ; special case
  RET

PRINT_NEG
  ST R7, saveR7
  NOT R6, R6
  ADD R6, R6, #1
  LEA R0, negSym
  PUTS
  LD R7, saveR7
  RET
negSym  .STRINGZ  "-"
outputArray   .BLKW     #5

SIZE
  ST R7, saveR7
  LD R1, num_R          ; store R6 (number) into R1
  LEA R3, constArray
  AND R4, R4, #0        ; R4 is going to be index
  ADD R4, R4, #4
  SIZELoop
    ADD R2, R3, R4        ; contains value from constArray
    LDR R2, R2, #0
    ST R4, saveR4
    ST R3, saveR3
    JSR Division          ; divide Result and Const
    LD R4, saveR4
    LD R3, saveR3
    ADD R6, R6, #0        ; make R6 relative
    BRp SizeDetermined    ; if positive, we found size!
    ADD R4, R4, #-1       ; else decrement index
    BRnzp SIZELoop   ; try again
  SizeDetermined
    ADD R4, R4, #1        ; size = index + 1
    ST R4, outputSize
  LD R7, saveR7
  RET

outputSize    .FILL     #0
outputQuot    .FILL     #0

CLEAR_REGISTERS
  AND R1, R1, #0
  AND R2, R2, #0
  AND R3, R3, #0
  AND R4, R4, #0
  AND R5, R5, #0
  AND R6, R6, #0
  RET

CLEAR_ARRAY
  LEA R0, outputArray
  LD R2, size5
  ClearLoop
    AND R1, R1, #0
    STR R1, R0, #0
    ADD R0, R0, #1
    ADD R2, R2, #-1
    BRp ClearLoop
  RET
size5         .FILL     #5

constArray    .FILL     #1
              .FILL     #10
              .FILL     #100
              .FILL     #1000
              .FILL     #10000
num_remainder .FILL     #0
inputSize     .FILL     #0
convert_quot  .FILL     #0
minus99       .FILL     #-99
minus999      .FILL     #-999
minus9999     .FILL     #-9999
saveR3        .FILL     #0
saveR4        .FILL     #0
saveR5        .FILL     #0
saveR6        .FILL     #0
saveR7        .FILL     #0
 .END
