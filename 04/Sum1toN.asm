// 1 + 2 + 3 + ... + N

// Put a number n in RAM[0]

@R0
D=M

@n
M=D

@i
M=1
@sum
M=0

(LOOP)
@i
D=M
@n
D=D-M
@STOP
D;JGT

@sum
D=M
@i
D=D+M
@sum
M=D
@i
M=M+1
@LOOP
0;JMP

(STOP)
@sum
D=M
@R1
M=D

(END)
@END
0;JMP