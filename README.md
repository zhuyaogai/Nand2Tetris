# Nand2Tetris
My own code for Nand2Tetris

- [x] Project 01
    利用Nand门构建基本 logic gates 
- [x] Project 02
    利用Project 01构建好的logic Gates 来构建基本的运算单元 Half Adder, Full Adder, Inc, ALU 
- [x] Project 03 
    利用基本基于时钟的时序芯片 DFF (触发器)来构建更多基于时序的芯片(结合先前制作的组合逻辑芯片)，如 Bit, Register, RAMn, PC


--- 

数字的表示方式应该是使用补码。因为可以使负数的加法变得简单，并且0的表示是唯一的。某一个正数的相反数的补码是该数的反码加1.
---



#### 什么是触发器？
> 用触发器是因为触发器能保存数据，保存电路状态；触发器是在时钟边沿触发，用时钟同步是让整个电路能同步整齐划一的工作；乘法器的计算部分是组合逻辑，不需要触发器，计算后的结果可以用触发器保存起来。

电路系统中具有两种电路：
- 组合逻辑: 输出只是当前输入的函数，无储存功能，其实就是一个计算过程
- 时序逻辑: 能够储存数据，例如一个寄存器。(依赖于时钟clock)

计算机如果只是会计算，但是不能存储，是无法实现更加复杂功能的。

为什么需要触发器？
> 简而言之就是同步化。设计电路的时候你可以精确地知道在哪一个时刻获得什么结果，这样功能设计变得有可能。如果没有触发器，组合逻辑链有长有短，天知道我这个输入进去，测到的是哪个时候的输出。不可预测，就谈不上设计。把组合电路塞到两个触发器之间，我就可以明确的知道，下一个时钟，我在触发器的输出端，得到的是什么。（当然这个也是依赖于时钟周期）

#### 时钟(Clock)

在计算机中需要模拟时间的流逝，因此这里产生了一个主时钟，其硬件实现一般来说是基于振荡器，其在两个信号值之间来回变换(0-1, 低电平-高电平)

The hardware must handle the physical time delays associated with calculating and moving data from one chip to another.

---
Hack Programming (Hack 是本课程制作的计算机 16 bits 字长)
- Working with registers and memory 
- Branching     (condition)
- Variables 
- Iteration     (loop)
- Pointers    (array and so on)
- Input/Output    (Screen and keyboard)

以上是几乎所有 Programming Language 都会具备的特性。

当你构造一个计算机的时候，你需要考虑指令集(Instruction Set)--->即是说这个机器可以执行哪一些指令
当你设计low level programming language或者指令集(Instruction Set)时，你需要考虑计算机的构造。

图灵机 ---> 冯诺依曼体系结构
所以说一个计算机的设计必定是遵循一定模型的，有基本的模块构建起来，但是具体是怎么实现的是可以设计的，而本课程设计的 Hack Machine 基于以下结构：

Hack Machine 的机器语言支持两种指令
- 16-bit A-instructions    @value  --> 改变A register 和 M register
- 16-bit C-instructions

Hack Program = Sequence of instructions written in the Hack machine language.

Hack Registers
- A register
- D register
- M register

Well-written low-level code is
- Short
- Efficient
- Elegant
- Self-describing

Technical tips
- Use symbolic valiables and labels
- Use sensible variable and label names
- Variables: lower-case
- Use indentation 
- Start with pseudo code 

make your program good-looking and easy to read.