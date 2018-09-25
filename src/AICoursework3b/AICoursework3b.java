/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AICoursework3b;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Matthew
 */
public class AICoursework3b {
    static ArrayList <int[]> uniqueCases = new ArrayList<>();
    static ArrayList <int[]> queue = new ArrayList<>();
    
    static int count =0;
    
    static void branchSearch(int A,int B,int C,int X,int Y,int Z){
        
        // this function explores a selected node to dicover its fringes/child nodes
        for(int i=0;i<=12;i++){ //cycles through the 12 possible movementsthat can be done in a turn
            switch(i){
                case 1:
                    //fill jug one
                    if(A<X){
                        int[] temp = {X,B,C};
                        queue.add(temp); //adds new value to the queue
                    }
                    break;
                case 2:
                    //fill jug 2
                    if(B<Y){
                        int[] temp = {A,Y,C};
                        queue.add(temp); //adds new value to the queue
                    }
                    break;
                case 3:
                    //fill jug 3
                    if(C<Z){
                        int[] temp = {A,B,Z};
                        queue.add(temp); //adds new value to the queue
                    }
                    break;
                case 4:
                    //empty jug 1
                    if(A>0){
                        int[] temp = {0,B,C};
                        queue.add(temp); //adds new value to the queue
                    }
                    break;
                case 5:
                    //empty jug 2
                    if(B>0){
                        int[] temp = {A,0,C};
                        queue.add(temp); //adds new value to the queue
                    } 
                    break;
                case 6:
                    //empty jug 3
                    if(C>0){
                        int[] temp = {A,B,0};
                        queue.add(temp); //adds new value to the queue
                    }
                    break;
                case 7:
                    //pour jug 1 into 2
                    if(B !=Y && A !=0){
                        if (B+A <= Y){
                            int[] temp = {0,B+A,C};
                            queue.add(temp); //adds new value to the queue
                        }
                        if (B+A > Y){
                            int[] temp = {A-(Y-B),Y,C};
                            queue.add(temp); //adds new value to the queue
                        }
                    }
                    break;
                case 8:
                    //pour jug 2 into 3
                    if(C !=Z && B !=0){
                        if (B+C <= Z){
                            int[] temp = {A,0,B+C};
                            queue.add(temp); //adds new value to the queue
                        }
                        if (B+C > Z){
                            int[] temp = {A,B-(Z-C),Z};
                            queue.add(temp); //adds new value to the queue
                        }
                    }
                    break;
                case 9:
                    //pour jug 1 into 3
                    if(C !=Z && A !=0){
                        if (A+C <= Z){
                            int[] temp = {0,B,A+C};
                            queue.add(temp); //adds new value to the queue
                        }
                        if (A+C > Z){
                            int[] temp = {A-(Z-C),B,Z};
                            queue.add(temp); //adds new value to the queue
                        }
                    }
                    break;
                case 10:
                    //pour jug 2 into 1
                    if(A !=X && B !=0){
                        if (B+A <= X){
                            int[] temp = {B+A,0,C};
                            queue.add(temp); //adds new value to the queue
                        } 
                        if (B+A > X){
                            int[] temp = {X,B-(X-A),C};
                            queue.add(temp); //adds new value to the queue
                        }
                    }
                    break;
                case 11:
                    //pour jug 3 into 1
                    if(A !=X && C !=0){
                        if (C+A <= X){
                            int[] temp = {C+A,B,0};
                            queue.add(temp); //adds new value to the queue
                        }
                        if (C+A > X){
                            int[] temp = {X,B,C-(X-A)};
                            queue.add(temp); //adds new value to the queue
                        }
                    }
                    break;
                case 12:
                    //pour jug 3 into 2
                    if(B !=Y && C !=0){
                        if (C+B <= Y){
                            int[] temp = {C+B,0,C};
                            queue.add(temp); //adds new value to the queue
                        }
                        if (C+B > Y){
                            int[] temp = {A,Y,C-(Y-B)};
                            queue.add(temp); //adds new value to the queue
                        }
                    }
                    break;
            }
        }    
    }
    
    
    
    static boolean isUnique(int A, int B,int C) {
        
        //this function acts as checker to see if the selected value in the queue is in the array list of uniqueCases or not
        
        boolean caseIsUnique = true; //sets the value of caseIsUnique to true
        for(int i=0;i<uniqueCases.size();i++){
            if((uniqueCases.get(i)[0] == A ) && (uniqueCases.get(i)[1] == B ) && (uniqueCases.get(i)[2] == C )){
                caseIsUnique =false; //if the selected value in queue is already in unique cases set caseIsUnique to true
            }
        }
        return caseIsUnique; //return value of caseIsUnique
    }
    public static void main(String[] args) {

        
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter the capacity of jug 1: ");
        int X = reader.nextInt(); // Scans the next token to get capacity of jug1
        System.out.println("Enter the capacity of jug 2: ");
        int Y = reader.nextInt(); // Scans the next token to get capacity of jug1
        System.out.println("Enter the capacity of jug 3: ");
        int Z = reader.nextInt(); // Scans the next token to get capacity of jug1
        int[] start = {0,0,0};
        queue.add(start); //adds the first case to the queue (0,0,0)
        while(queue.size() >0){
            for(int i=0;i<queue.size();i++){ //cycle through the queue array list
                if(isUnique(queue.get(i)[0],queue.get(i)[1],queue.get(i)[2]) ==false){
                    queue.remove(0); //if selected value in the queue array is not unique remove it from the queue
                    i--; //select next first value in the queue array list
                }
                else{ 
                    //if the selected queue value is unique
                    uniqueCases.add(queue.get(i)); //add queue value to the uniqueCases array list
                    branchSearch(queue.get(i)[0],queue.get(i)[1],queue.get(i)[2],X,Y,Z); //explores the selected queue node to discover its children/fringe nodes
                    queue.remove(0); //remove selected value from the queue array list
                    count++; //add one to the count
                    i--; //select next first value in the queue array list
                }
                }
            }
        System.out.println("The number of unique cases is: "+count); //prints number of unique cases
        //outputs complete unique cases array list in human readable form i.e. with square brackets for the  array 
        for(int j=0;j<uniqueCases.size();j++){
            System.out.print("["+uniqueCases.get(j)[0]);
            System.out.print(","+uniqueCases.get(j)[1]+",");
            if(j%15 != 0 || j == 0){
                System.out.print(uniqueCases.get(j)[2]+"],");
            }
            else{
                System.out.println(uniqueCases.get(j)[2]+"],");
            }
        }
        }
        
    
}
