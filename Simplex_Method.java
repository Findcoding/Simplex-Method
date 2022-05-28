import java.util.*;
import java.io.*;

/**
 * Code by Bijendar Prasad | IIITD'23
 */

/**
 * This is a necessary condition for solving the problem:
 * the numbers on the right parts of the constraint system must be non-negative.
 */

/**
 * Simplex method for LPPs with ≥ and = constraints needs a modified approach.
 * This is known as Big-M method, but Big M method raises difficulty when the problem is solved on a digital computer.
 * Convert minimization into maximization problem.
 */

public class simplex_method {

    static float[][] table;
    static String[] row_variables;        // all variables in a row
    static String[] basic_variables;       // contain basic variables

    static boolean problemType = false;      // Problem Type

    static boolean canPrint = true;

    public static void initialize() {

        Scanner input = new Scanner(System.in);

        System.out.println("Choose Problem Type:\n" +"\t\t 1) Maximization Problem \n" + "\t\t 2) Minimization Problem");
        System.out.print("Enter chosen type: ");

        int type = input.nextInt();

        /**
        String prob_type = Input.next();
        int type = 0;

        try {
            type = Integer.parseInt(prob_type);
        } catch (NumberFormatException ex) {
            System.out.println("Wrong input!!!");
            input.close();
        }
         */

        while (type > 2 || type <= 0) {
            System.out.println("Invalid Input");
            System.out.print("Enter chosen type: ");
            type = input.nextInt();
        }

        problemType = type == 1;    // Assign max/min type...

        System.out.print("Enter No. of variables: ");
        int n = input.nextInt();

        System.out.print("Enter No. of constraints: ");
        int m = input.nextInt();

        table = new float[m+1][n+m+2];

        System.out.println("Enter coefficients of Objective Function:");
        table[0][0] = 1;
        int[] objective_arr = new int[n];

        for (int i=0; i<n; i++) {
            System.out.print("Enter the value of " + "x" + (i+1) + ": ");
            objective_arr[i] = problemType ? input.nextInt() : - input.nextInt();
        }

        for (int i=0; i<objective_arr.length; i++) {
            table[0][i+1] = -objective_arr[i];
        }

        for (int j=0; j<m; j++) {
            System.out.println("Enter LHS coefficients of constraints(" + (j+1) + ") : ");

            table[j+1][0] = 0;

            for (int i=0; i<n; i++) {
                System.out.print("Enter the value of " + "x" + (i+1) + ": ");
                table[j+1][i+1] = input.nextInt();
            }

            System.out.println("Choose Inequality option: \n" + "\t\t 1) ≤ \n" + "\t\t 2) ≥ \n" + "\t\t 3) = ");
            System.out.print("Enter chosen option: ");
            int choice = input.nextInt();

            while (choice > 3 || choice <= 0) {
                System.out.println("Invalid Input");
                System.out.print("Enter chosen option: ");
                choice = input.nextInt();
            }

            if(choice == 1) {
                table[j+1][n+j+1] = 1;   // slack variable added
            } else if(choice == 2) {
                table[j+1][n+j+1] = -1;  // surplus variable added
            } else {
                table[j+1][n+j+1] = 0;
            }


            System.out.print("Enter RHS coefficient of constraints(" + (j+1) + ") : ");
            int b_val = input.nextInt();
            table[j+1][table[0].length-1] = b_val;

            if(b_val < 0) {    // negative value...
                for (int i=0; i<table[0].length; i++) {
                    table[j+1][i] = -table[j+1][i];
                }
            }

        }

        System.out.println();


        System.out.println(Arrays.deepToString(table));      //

        fill_variables(n, m);

        System.out.println(Arrays.toString(row_variables));
        System.out.println(Arrays.toString(basic_variables));


        System.out.println();

        optimize_table();

        System.out.println();

        System.out.println("Final table: " + Arrays.deepToString(table));

        System.out.println();

        if(canPrint) {
            print_solution();
        }


    }


    public static void optimize_table() {

        int iter = 1;

        while (ifminExists()) {
            int index = min_index();

            float min_ratio = Float.MAX_VALUE;
            int min_index = 0;

            boolean state = false;

            for (int j=1; j<table.length; j++) {

                if (table[j][index] > 0) {           // must be >= 0
                    state = true;
                    float ratio = table[j][table[0].length-1] / table[j][index];

                    if(ratio < min_ratio) {
                        min_ratio = ratio;
                        min_index = j;
                    }
                }
            }

            if(!state) {
                System.out.println("******* This system has unbounded solution *******");
                canPrint = false;
                break;

            } else {

                System.out.println("************ Iteration - " + iter + " ************");
                System.out.println("Incoming Variable is: " + row_variables[index]);
                System.out.println("Outgoing Variable is: " + basic_variables[min_index]);
                System.out.println();

                basic_variables[min_index] = row_variables[index];   // swap basic variables...

                row_operation(index, min_index);       // row operation in table...

                iter++;
            }


        }
    }


    public static void row_operation(int index, int min_index) {

        float num = table[min_index][index];

        for (int i=0; i<table[0].length; i++) {
            table[min_index][i] = table[min_index][i] / num;
        }

//        System.out.println(Arrays.deepToString(table));

        for (int i=0; i<table.length; i++) {
            if (i != min_index) {
                float cal = -table[i][index];

                for (int j=0; j<table[0].length; j++) {
                    table[i][j] = cal*table[min_index][j] + table[i][j];
                }

            }
        }

//        System.out.println(Arrays.deepToString(table));


    }


    public static void print_solution() {

        System.out.println("*************** Optimal Solution: *********************");

        for (int i=1; i<row_variables.length-1; i++) {
            boolean state = false;

            for (int j=1; j<basic_variables.length; j++) {
                if(row_variables[i].equals(basic_variables[j])) {
                    System.out.println("The value of " + row_variables[i] + " is: " + table[j][table[0].length-1]);
                    state = true;
                    break;
                }

            }

            if(!state) {
                System.out.println("The value of " + row_variables[i] + " is: " + 0);
            }
        }

        if (problemType) {
            System.out.println("The value of Z_max is: " + table[0][table[0].length-1]);
        } else {
            System.out.println("The value of Z_min is: " + -table[0][table[0].length-1]);
        }


    }


    public static void fill_variables(int n, int m) {

        basic_variables = new String[m+1];
        basic_variables[0] = "c";

        for (int i=0; i<m; i++) {
            basic_variables[i+1] = "s" + (i+1);
        }

        row_variables = new String[n+m+2];
        row_variables[0] = "z";
        for (int i=0; i<n; i++) {
            row_variables[i+1] = "x" + (i+1);
        }

        for (int i=0; i<m; i++) {
            row_variables[n+i+1] = "s" + (i+1);
        }

        row_variables[n+m+1] = "b";

    }


    public static boolean ifminExists() {
        boolean state = false;

        for (int i=0; i<table[0].length; i++) {
            if (table[0][i] < 0) {
                state = true;
                break;
            }
        }

        return state;
    }


    public static int min_index() {

        int index = 0;
        float min = Float.MAX_VALUE;

        for (int i=0; i<table[0].length; i++) {
            if (table[0][i] < min) {
                index = i;
                min = table[0][i];
            }
        }

//        System.out.println(min);

        return index;
    }

    public static void main(String[] args) {

        initialize();

    }
}
