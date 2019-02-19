// Add two numbers 
// RAM[2] = RAM[0] + RAM[1]

@0
D=M

@1
D=D+M

@2
M=D

// Infinite loop
@6
0;JMP