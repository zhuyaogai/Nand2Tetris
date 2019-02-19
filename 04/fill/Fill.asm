// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.

// 屏幕最大地址
@24575
D=A
@R0
M=D

// 当前地址
@SCREEN
D=A
@R1
M=D

(LOOP)
@KBD
D=M
@FILL
D;JGT

@CLEAR
0;JMP

(FILL)
// 判断屏幕是否为满
    @0
    D = M
    @1
    D = D - M
    @LOOP
    D;JEQ

@R1
D=M
A=M
M=-1

@R1
M=D+1

@FILL
0;JMP

(CLEAR)
@R1
D=M
@SCREEN
D=D-A
@LOOP
D;JLE


@R1
D=M
A=M
M=0

@R1
M=D-1

@CLEAR
0;JMP