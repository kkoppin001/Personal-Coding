.ORIG x3000
; Main function

MAIN
   LD R6, STACK_BASE
   ADD R6, R6, #-1
   ADD R5, R6, #0 
   ; first node pointer
   AND R0, R0, #0
   STR R0, R6, #0
   ;FUNCTION SELECTION BUFFER
   ADD R6, R6, #-1
   STR R0, R0, #0
   ;CALLING ADDVALUE
   ADD R0, R6, #1
   ADD R6, R6, #-1
   STR R0, R6, #0

BRANCHING_LOOP
LEA R0, PROMPT_STR
PUTS
GETC
OUT
   

LD R1, A_OFFSET
NOT R1, R1
ADD R1, R1, #1
ADD R2, R1, R0
BRz ADD_0

LD R1, P_OFFSET
NOT R1, R1
ADD R1, R1, #1
ADD R2, R1, R0
BRz PRINT_0


LD R1, R_OFFSET
NOT R1, R1
ADD R1, R1, #1
ADD R2, R1, R0 
BRz REMOVE_0

LD R1, Q_OFFSET
NOT R1, R1
ADD R1, R1, #1
ADD R2, R1, R0 
BRz QUIT


ADD_0
; ONCE THE USER TYPES A VALUE
LEA R0, ADD_PROMPT
PUTS
TRAP x40
ADD R6, R6, #-1
STR R0, R6, #0
JSR ADDVALUE
BRnzp BRANCHING_LOOP
   
PRINT_0
; ONCE THE USER TYPES A VALUE
TRAP x40
ADD R6, R6, #-1
STR R0, R6, #0
JSR PRINTLIST
BRnzp BRANCHING_LOOP

REMOVE_0
; ONCE THE USER TYPES A VALUE
LEA R0, REM_PROMPT
TRAP x40
ADD R6, R6, #-1
STR R0, R6, #0
JSR REMOVEVALUE
BRnzp BRANCHING_LOOP

QUIT
HALT

LOWER_S .FILL x0073
STACK_BASE .FILL x6000
PROMPT_STR .STRINGZ "\nAvailable options:\np - Print linked list\na - Add value to linked list\nr - Remove value from linked list\nq - Quit\nChoose an option: "
ADD_PROMPT .STRINGZ "\nType a number to add: "
REM_PROMPT .STRINGZ "Type a number to remove: "
A_OFFSET .FILL x61
P_OFFSET .FILL x70
Q_OFFSET .FILL x71
R_OFFSET .FILL x72

; =============================================================================
; printList(x5FFF) should find node address at x5FFF and start printing each node
; Use TRAP x41 to output the value of each node.
; =============================================================================
PRINTLIST

LDR R2, R5, #0
BRz PRINTLIST_DONE

; Push return space
ADD R6, R6, #-1
; Register backups
ADD R6, R6, #-1
STR R7, R6, #0
ADD R6, R6, #-1
STR R5, R6, #0
ADD R6, R6, #-1
STR R3, R6, #0
ADD R6, R6, #-1
STR R2, R6, #0
ADD R6, R6, #-1
STR R1, R6, #0
ADD R6, R6, #-1
STR R0, R6, #0

LDR R2, R5, #0
AND R7, R7, #0 ; first node flag

PRINT_LOOP

BRz NOT_FIRST_NODE
    ; Output the value of the current node without arrow if it's the first node
    LDR R3, R2, #0       ; Load the value of the current node
    OUT                  ; Output the value
    ADD R7, R7, #1       ; Set flag to indicate it's not the first node
    BRnzp CONTINUE_PRINT_LOOP


NOT_FIRST_NODE
LEA R0, ARROW_STR    ; Load the address of "->" string
PUTS   
LDR R3, R2, #0
OUT

CONTINUE_PRINT_LOOP
 LDR R2, R2, #1 
 BRz PRINTLIST_DONE
 BR PRINT_LOOP
; PRINTLIST code section here
; Remindr: R6 should return to the same place it was before calling the function.
PRINTLIST_DONE
; Pop local variables
ADD R6, R6, #3
; Load each back up register
LDR R0, R6, #0
ADD R6, R6, #1
LDR R1, R6, #0
ADD R6, R6, #1
LDR R2, R6, #0
ADD R6, R6, #1
LDR R3, R6, #0
ADD R6, R6, #1
LDR R5, R6, #0
ADD R6, R6, #1
LDR R7, R6, #0
ADD R6, R6, #1
; Pop return space (it's void so nothing returned)
ADD R6, R6, #1
RET
ARROW_STR .STRINGZ "->"
; =============================================================================
; addValue(x5FFF, int added) should traverse to the end of the list and then add new node.
; If x5FFF contains x0000 then make a new list starting at x8000 and overwrite x5FFF.
; =============================================================================
ADDVALUE
; Push return space
ADD R6, R6, #-1
; Register backups
ADD R6, R6, #-1
STR R7, R6, #0
ADD R6, R6, #-1
STR R5, R6, #0
ADD R6, R6, #-1
STR R3, R6, #0
ADD R6, R6, #-1
STR R2, R6, #0
ADD R6, R6, #-1
STR R1, R6, #0
ADD R6, R6, #-1
STR R0, R6, #0
ADD R5, R6, #-1

; ADDVALUE code section here
; first thing you should do is load the two parameters from stack
LDR R0, R6, #8 ; INPUT POINTER ADDRESS
LDR R1, R6, #7

LDR R2, R0, #0 ; GETS ACTUAL FIRST NODE POINTER
BRnp ADDVALUE_NOT_EMPTY
LD R3, STARTER_NODE
STR R3, R0, #0
; ADD VALUE TO NODE
STR R1, R3, #0
; ADD EMPTY LINK TO NECT NODE
AND R1, R1, #0
STR R1, R3, #1
BRnp ADDVALUE_RETURN

ADDVALUE_NOT_EMPTY
;atleats one value, find end of list, put new user input
LD R2, STARTER_NODE
LDR R3, R2, #1

END
BRnzp END_LIST  ; Repeat the loop
ADD R2, R3, #0       ; Move to the next node
LDR R3, R2, #1       ; Load the link from the next node
BRnzp END_LIST  ; Repeat the loop

BRz END_LIST

END_LIST
LEA R5, NEW_NODE     ; Load the effective address of the new node structure
    STR R1, R5, #0       ; Store the user's input in the value field of the new node
    AND R1, R1, #0       ; Clear R1 (optional, if needed)
    STR R1, R5, #1       ; Set the link of the new node to 0 (since it's the last node)

    ; Link the new node after the last node
    STR R5, R2, #1       ; Update the link of the last node to point to the new node



ADDVALUE_RETURN

; Same ACHTUNG as before
ADDVALUE_DONE
; Pop local variables
ADD R6, R6, #0
; Load each back up register
LDR R0, R6, #0
ADD R6, R6, #1
LDR R1, R6, #0
ADD R6, R6, #1
LDR R2, R6, #0
ADD R6, R6, #1
LDR R3, R6, #0
ADD R6, R6, #1
LDR R5, R6, #0
ADD R6, R6, #1
LDR R7, R6, #0
ADD R6, R6, #1
; Pop return space (it's void so nothing returned)
ADD R6, R6, #1
RET
STARTER_NODE .FILL x8000
NEW_NODE .BLKW #2
; =============================================================================
; removeValue(x5FFF, int removed) searches for first node with value removed and
; deletes it. You gotta keep track of the previous node while traversing.
; =============================================================================
REMOVEVALUE
; Push return space
ADD R6, R6, #-1
; Register backups
ADD R6, R6, #-1
STR R7, R6, #0
ADD R6, R6, #-1
STR R5, R6, #0
ADD R6, R6, #-1
STR R3, R6, #0
ADD R6, R6, #-1
STR R2, R6, #0
ADD R6, R6, #-1
STR R1, R6, #0
ADD R6, R6, #-1
STR R0, R6, #0

; REMOVEVALUE code section here
; first thing you should do is load the two stack inputs
; Load the address of the first node into R2
    LDR R2, R5, #0
    ; Load the value to be removed into R3
    LDR R3, R6, #0

    ; Initialize R4 to store the address of the previous node
    AND R4, R4, #0

REMOVE_LOOP
    ; Check if the current node contains the value to be removed
    LDR R5, R2, #0       ; Load the value of the current node
    NOT R5, R5
    ADD R5, R5, #1       ; Negate the value
    ADD R5, R5, R3       ; Compare it with the value to be removed
    BRz FOUND_VALUE      ; If equal, go to FOUND_VALUE

    ; Move to the next node
    ADD R4, R2, #0       ; Save the address of the current node as the previous node
    LDR R2, R2, #1       ; Load the link of the current node
    ; If the link is 0, we have reached the end of the list
    BRz REMOVEVALUE_DONE

    ; Repeat the loop for the next node
    BR REMOVE_LOOP

FOUND_VALUE
    ; Remove the node containing the value
    ; If R4 is zero, the node to be removed is the first node
    BRz REMOVE_FIRST_NODE

    ; If R4 is not zero, adjust the link of the previous node to skip over the node to be removed
    ADD R5, R2, #1       ; Load the link of the node to be removed
    STR R5, R4, #1       ; Update the link of the previous node to skip over the node to be removed
    BRnzp REMOVEVALUE_DONE

REMOVE_FIRST_NODE
    ; Update the start of the linked list to skip over the first node
    LDR R5, R2, #1       ; Load the link of the first node
    STR R5, R5, #0       ; Update the start of the linked list to skip over the first node


REMOVEVALUE_DONE
; Pop local variables
ADD R6, R6, #0
; Load each back up register
   LDR R0, R6, #0
    ADD R6, R6, #1   
    LDR R1, R6, #0  
    ADD R6, R6, #1  
    LDR R2, R6, #0  
    ADD R6, R6, #1   
    LDR R3, R6, #0   
    ADD R6, R6, #1   
    LDR R5, R6, #0   
    ADD R6, R6, #1   
    LDR R7, R6, #0   
    ADD R6, R6, #1   
; Pop return space (it's void so nothing returned)
ADD R6, R6, #1
RET

.END