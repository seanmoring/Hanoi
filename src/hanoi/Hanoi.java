/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hanoi;

/**
 *
 * @author Sean Dunning
 */
import java.util.*;
import java.lang.Integer;

/**
 *
 * @author seanmoring
 */
public class Hanoi {
    
    private ArrayList<Stack<Integer>> pegs; 
    private int towerSize;
    
    private void buildPegs() {
        //Initial set up of towers
        
        // The structure of the pegs is an array list of stacks of integers. 
        // Per the description of the Towers of Hanoi problem, the last disc
        // added to a peg is the one which must be removed first, so a stack is 
        // the natural choice to represent an individual peg.
        pegs = new ArrayList<Stack<Integer>>();

        pegs.add(0, new Stack<Integer>());
        pegs.add(1, new Stack<Integer>());
        pegs.add(2, new Stack<Integer>());    
    }
    
    // The default constructor. Default number of discs is 3.
    public Hanoi() {
        this(3);
    }
    
    // Constructor to allow the user to specify the number of discs
    public Hanoi(int size) {
       towerSize = size; 
       buildPegs(); 
       
       // Per the rules of the problem, the smaller discs need to 
       // sit atop the bigger discs. So, push Integers (representative of discs)
       // onto the heap in biggest first order using a countdown loop.
       for (int i = towerSize; i > 0; i--) { 
           pegs.get(0).push(new Integer(i));
       }
    }
    
    public void solve() {
        // The object is to move the discs from the 0th tower
        // onto the 2nd tower. 
        moveDiscs(towerSize, 0, 2);    
    }
    
    public void display() {
        System.out.println("-------");
        for (int i = 0; i < 3; i++) {
            System.out.println("peg " + i + ":" + pegs.get(i));
        }
    }
    
    void moveDisc(int source, int target) {
        pegs.get(target).push(pegs.get(source).pop());
    }
    
    // The implementation is a recursive algorithm. The base case is 3 and is hard coded 
    // into the implementation. 
    // For a stack of "n" discs, the algorithm will move the top "n-1" discs to a holder
    // which is the peg that is neither the target nor the source of the n discs. Then the algorithm
    // moves the nth (or bottom disc) to the target peg. 
    void moveDiscs(int discNumber, int source, int target) {
        int helperHolder = 0;
        
        // code to set the helpper peg to be whichever peg is neither the source 
        // nor the target. 
        if (target == 2) {
            if (source == 0)
                helperHolder = 1;
            else if (source == 1)
                helperHolder = 0;
        } else if (target ==1) {
            if (source == 2)
                helperHolder = 0;
            else if (source == 0)
                helperHolder = 2;
        } else if (target == 0) {
            if (source == 2) 
                helperHolder = 1;
            else if (source == 1)
                helperHolder =2;
        }
        
        // code to implement the base case of 3 discs.
        if (discNumber == 3) {         
            moveDisc(source, target);
            display();
            moveDisc(source,helperHolder);
            display();
            moveDisc(target,helperHolder);
            display();
            moveDisc(source,target);
            display();
            moveDisc(helperHolder,source);
            display();
            moveDisc(helperHolder,target);
            display();
            moveDisc(source,target);
            display();
        }
        else {
            
            // this is the recursive heart of the solution. 
            
            //Move the top n-1 discs to the helper.
            moveDiscs(discNumber-1,source, helperHolder);
            
            //Move the bottom disc to the target.
            moveDisc(source,target);
            
            //Now, move the n-1 discs that were on the helper holder 
            //onto the target peg.
            moveDiscs(discNumber-1,helperHolder, target);
        }
    }
    
    public static void main(String args[]) {
        //Hanoi h = new Hanoi(17); (works, but takes 2 minutes to solve)
        Hanoi h = new Hanoi(5); // tower of 5 starting on peg 0
        
        h.solve();
    }
}
