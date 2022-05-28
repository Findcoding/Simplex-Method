# Simplex-Method
Simplex Method Implementation in Java

Suppose, we have:
  Maximize Z = 40x1 + 30x2 <br /> 
  Subject to: <br />
          x1 + x2 ≤ 12
          2x1 + x2 ≤ 16
          x1≥0; x2≥0
          
Then, After running the simplex_method.java file, Enter values in console as:

Choose Problem Type:
		 1) Maximization Problem 
		 2) Minimization Problem
Enter chosen type: 1
Enter No. of variables: 2
Enter No. of constraints: 2
Enter coefficients of Objective Function:
Enter the value of x1: 40
Enter the value of x2: 30
Enter LHS coefficients of constraints(1) : 
Enter the value of x1: 1
Enter the value of x2: 1
Choose Inequality option: 
		 1) ≤ 
		 2) ≥ 
		 3) = 
Enter chosen option: 1
Enter RHS coefficient of constraints(1) : 12
Enter LHS coefficients of constraints(2) : 
Enter the value of x1: 2
Enter the value of x2: 1
Choose Inequality option: 
		 1) ≤ 
		 2) ≥ 
		 3) = 
Enter chosen option: 1
Enter RHS coefficient of constraints(2) : 16

************ Iteration - 1 ************
Incoming Variable is: x1
Outgoing Variable is: s2

************ Iteration - 2 ************
Incoming Variable is: x2
Outgoing Variable is: s1


Final table: [[1.0, 0.0, 0.0, 20.0, 10.0, 400.0], [0.0, 0.0, 1.0, 2.0, -1.0, 8.0], [0.0, 1.0, 0.0, -1.0, 1.0, 4.0]]

*************** Optimal Solution: *********************
The value of x1 is: 4.0
The value of x2 is: 8.0
The value of s1 is: 0
The value of s2 is: 0
The value of Z_max is: 400.0

Process finished with exit code 0
