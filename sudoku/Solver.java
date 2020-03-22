package sudoku;

import java.util.*;


public class Solver 
{
	private Grid						problem;
	private ArrayList<Grid>				solutions;
	
	
	public Solver(Grid problem)
	{
		this.problem = problem;
	}
	
	
	public void solve()
	{
		solutions = new ArrayList<>();
		solveRecurse(problem);
	}
	
		
	// 
	// FINISH THIS.
	//
	// Standard backtracking recursive solver.
	//
	private void solveRecurse(Grid grid)
	{		
		Evaluation eval = evaluate(grid);
		
		if (eval == Evaluation.ABANDON)
		{
			// Abandon evaluation of this illegal board.
			return;
		}
		else if (eval == Evaluation.ACCEPT)
		{
			// A complete and legal solution. Add it to solutions.
			solutions.add(grid);
		}
		else
		{
			// Here if eval == Evaluation.CONTINUE. Generate all 9 possible next grids. Recursively 
			// call solveRecurse() on those grids.
			ArrayList<Grid> grids = grid.next9Grids();
			if (grids != null) {
				for(Grid g : grids) {
					solveRecurse(g);
				}
			}
			
		}
	}
	
	//
	// COMPLETE THIS
	//
	// Returns Evaluation.ABANDON if the grid is illegal. 
	// Returns ACCEPT if the grid is legal and complete.
	// Returns CONTINUE if the grid is legal and incomplete.
	//
	public Evaluation evaluate(Grid grid)
	{
		if (grid.isLegal() == false) {
			return Evaluation.ABANDON;
		}
		else if (grid.isFull() == true) {
			return Evaluation.ACCEPT;
		}
		else {
			return Evaluation.CONTINUE;
		}
	}

	
	public ArrayList<Grid> getSolutions()
	{
		return solutions;
	}
	
	
	public static void main(String[] args)
	{
		Grid g = TestGridSupplier.getPuzzle1();		// or any other puzzle
		Solver solver = new Solver(g);
		System.out.println("Will solve\n" + g);
		solver.solve();
		
		// Print out your solution, or test if it equals() the solution in TestGridSupplier.
		ArrayList<Grid> s = solver.getSolutions();
		if (s.size() > 0) {
			if (s.get(0).equals(TestGridSupplier.getSolution1())) {
				System.out.println("equal");
			}
			else {
				System.out.println(s.get(0).toString());
			}
		}
		else {
			System.out.println("No Solution");
		}
		
	}
}
