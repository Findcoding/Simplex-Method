# Simplex-Method
Simplex Method Implementation in Java

Suppose, we have:
  Maximize Z = 40x1 + 30x2 <br /> 
  Subject to: <br />
          x1 + x2 ≤ 12 <br /> 
          2x1 + x2 ≤ 16 <br /> 
          x1≥0; x2≥0 <br /> 
          
If you want to solve this problem then, simply run the simplex_method.java code and enter values in console as same as below: <br /> 

Choose Problem Type: <br /> 
		 1) Maximization Problem <br /> 
		 2) Minimization Problem <br /> 
Enter chosen type: 1 <br /> 
Enter No. of variables: 2 <br /> 
Enter No. of constraints: 2 <br /> 
Enter coefficients of Objective Function: <br /> 
Enter the value of x1: 40 <br /> 
Enter the value of x2: 30 <br /> 
Enter LHS coefficients of constraints(1) :  <br /> 
Enter the value of x1: 1 <br /> 
Enter the value of x2: 1 <br /> 
Choose Inequality option: <br /> 
		 1) ≤ <br /> 
		 2) ≥ <br /> 
		 3) = <br /> 
Enter chosen option: 1 <br /> 
Enter RHS coefficient of constraints(1) : 12 <br /> 
Enter LHS coefficients of constraints(2) : <br /> 
Enter the value of x1: 2 <br /> 
Enter the value of x2: 1 <br /> 
Choose Inequality option: <br /> 
		 1) ≤ <br /> 
		 2) ≥ <br /> 
		 3) = <br /> 
Enter chosen option: 1 <br /> 
Enter RHS coefficient of constraints(2) : 16 <br /> 

************ Iteration - 1 ************ <br /> 
Incoming Variable is: x1 <br /> 
Outgoing Variable is: s2 <br /> 

************ Iteration - 2 ************ <br /> 
Incoming Variable is: x2 <br /> 
Outgoing Variable is: s1 <br /> 


Final table: [[1.0, 0.0, 0.0, 20.0, 10.0, 400.0], [0.0, 0.0, 1.0, 2.0, -1.0, 8.0], [0.0, 1.0, 0.0, -1.0, 1.0, 4.0]] <br /> 

*************** Optimal Solution: ********************* <br /> 
The value of x1 is: 4.0 <br /> 
The value of x2 is: 8.0 <br /> 
The value of s1 is: 0 <br /> 
The value of s2 is: 0 <br /> 
The value of Z_max is: 400.0 <br /> 

Process finished with exit code 0 <br /> 
