# Nand2Tetris
My own code for Nand2Tetris

- [x] Project 01
    利用Nand门构建基本 logic gates 
- [x] Project 02
    利用Project 01构建好的logic Gates 来构建基本的运算单元 Half Adder, Full Adder, Inc, ALU 
- [x] Project 03 
    利用基本基于时钟的时序芯片 DFF (触发器)来构建更多基于时序的芯片(结合先前制作的组合逻辑芯片)，如 Bit, Register, RAMn, PC
- [x] Project 04 
    学习汇编语言理论，根据给Hack computer 设计的machine language写一些汇编语言程序


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

One to address the memory and one to operate 
the selected memory register.

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

RISC    (MIPS)
- more instructions
- quicker execution 
- fewer instruciotn type

Easier to make hardware but harder to make software.

CISC     (x86)
- fewer instruction 
- slower execution 
- more instruction types

Harder to make hardware but easier to make software.

Hack belongs to RISC. 


#### 第七章   虚拟机1：堆栈运算
虚拟机的基本思想：中间代码运行在虚拟机上(Virtual Mechine)，而不是真实的硬件平台上。

>高级语言程序能够在目标计算机运行之前,它必须被翻译成计算机的机器语言。这个翻译工作——也就是编译(compilation)——是相当复杂的过程。通常必须为给定的高级程序和其对应的机器语言编写专门的编译器。每种编译器编译的高级语言与变异之后的机器语言之间存在很强的依赖性。减少这种依赖性的方法之一是，将整个编译过程划分为两个几乎独立的阶段。在第一阶段，高级程序被解析出来，其命令被翻译成一种中间处理结果——既不是高级也不是低级的中间结果。在第二阶段，这些中间结果被进一步翻译成目标硬件的机器语言。

其实所谓的中间结果实则是一个虚拟机模型，在本章就是一个堆栈模型。

这里的VM语言包括四种类型命令：
- 算术命令
- 内存访问命令
- 程序流程控制命令
- 子程序调用命令

使用方法如下：
利用自己写的VM翻译器将Xxx.vm文件翻译成Xxx.asm,然后将Xxx.asm放进对应目录文件夹内,启动CPU模拟器加载Xxx.asm,然后再load对应的测试脚本,如果测试脚本能够成功通过即可.

其实在实现算术运算的 eq/gt/lt 等运算符的时候是比较巧妙的，因为我们的目的是利用栈顶的两个元素进行 eq/gt/lt 等运算之后再将结果值(true/false)写回栈顶。所以在这里我们就需要一个判断并且进行跳转来写栈顶的值。true置栈顶值为-1，false置栈顶值为0.

#### History of VMs and two-tier compilation:
- p-code
- Sun
- Cellphones
#### How close is our VM to Java’s JVM?
#### Efficiency and optimization
#### Different VM implementations:
- Stack machine
- Register machine
- Other approaches.


#### 第八章  虚拟机2：程序控制
##### Branching commands
- goto *label*
- if-goto *label*
- label *label*


##### Function commands
- function *functionName nVars*      //n个局部(Local)变量
- call *functionName nArgs*         // n个参数
- return 

- function *functionName nVars* 函数定义后面接n条push 操作作为局部变量
- call *functionName nArgs* 之前会有n次push操作，把参数压栈

### 在最底层里面，Hack只有两种指令
- A 指令
- C 指令

我们可以将汇编形式的A指令和C指令翻译成对应的01指令，只是具备一一对应的。对于VM语言，我们抽象出基于堆栈的虚拟机模型，利用最底层的汇编指令去模拟出这么一个模型，并且可以让它工作得十分好。在这个模型中，我们希望所有的操作都能够是抽象地从这个出发思考的操作，从而屏蔽掉底层的汇编操作，所以我们需要一个vm翻译器，能够将每一条基于堆栈模型的指令翻译成底层的汇编指令，那么这整个工作就能够被完成了。我们就可以说我们实现了一个基于堆栈的虚拟机了，在这个虚拟机上了运行基于该vm模型写的指令代码，其实这个虚拟机也就是一个翻译器而已。