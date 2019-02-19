// for (i=0;i < n;++i) 
//      arr[i] = -1


// Suppose that arr=100 and n=10

@100
D=A
@arr
M=D

// n=10
@10
D=A
@n
M=D

@i
M=0

(LOOP)
// if (i == n) goto END
@i
D=M
@n
D=D-M
@END
D;JEQ

@arr
D=M
@i
D=D+M
M=-1       // get memory data according to the A register!

@i
M=M+1

@LOOP
0;JMP

(END)
@END
0;JMP