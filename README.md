# Turing Machine Interpreter

This Java application was made for *SE3310 - Theoretical Foundations of Software Engineering* at Western University.

The aim of this program is to create a Turing machine interpreter, which takes a set of transitions in the form of a text file and computes a reject/accept state for different input tapes.


Transitions are defined as follows:
```
t *current state* *input symbol* *next state* *write symbol* *head movement direction*
```
example:
```
t 0 0 1 1 R
```

Final Accepted states are defined as follow:
```
f *final state 1* *final state 2* *final state 3* ... *final state n*
```
for example:
```
f 3 9 14
```
indicates your final accept state are 3, 9, and 14.

Input tapes are specified with a prefix of i:
```
i 10101
```

The Turing machine interpreter then runs the transition rules on the input tape sequence, and outputs **ACCEPT** or **REJECT**.
