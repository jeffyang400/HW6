package sudoku;

import java.util.*;


public class Grid 
{
	private int[][]						values;
	

	//
	// DON'T CHANGE THIS.
	//
	// Constructs a Grid instance from a string[] as provided by TestGridSupplier.
	// See TestGridSupplier for examples of input.
	// Dots in input strings represent 0s in values[][].
	//
	public Grid(String[] rows)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
		{
			String row = rows[j];
			char[] charray = row.toCharArray();
			for (int i=0; i<9; i++)
			{
				char ch = charray[i];
				if (ch != '.')
					values[j][i] = ch - '0';
			}
		}
	}
	
	
	//
	// DON'T CHANGE THIS.
	//
	public String toString()
	{
		String s = "";
		for (int j=0; j<9; j++)
		{
			for (int i=0; i<9; i++)
			{
				int n = values[j][i];
				if (n == 0)
					s += '.';
				else
					s += (char)('0' + n);
			}
			s += "\n";
		}
		return s;
	}


	//
	// DON'T CHANGE THIS.
	// Copy ctor. Duplicates its source. You’ll call this 9 times in next9Grids.
	//
	Grid(Grid src)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
			for (int i=0; i<9; i++)
				values[j][i] = src.values[j][i];
	}
	
	private int[] findNextEmptyCell() {
		int[] XY = null;
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				if (values[j][i] == 0) {
					XY = new int[2];
					XY[0] = j;
					XY[1] = i;
					return XY;
				}
			}
		}
		return null;
		
	}
	
	//
	// COMPLETE THIS
	//
	//
	// Finds an empty member of values[][]. Returns an array list of 9 grids that look like the current grid,
	// except the empty member contains 1, 2, 3 .... 9. Returns null if the current grid is full. Don’t change
	// “this” grid. Build 9 new grids.
	// 
	//
	// Example: if this grid = 1........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//
	// Then the returned array list would contain:
	//
	// 11.......          12.......          13.......          14.......    and so on     19.......
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	//
	public ArrayList<Grid> next9Grids()
	{		
		int xOfNextEmptyCell;
		int yOfNextEmptyCell;
		
		int[] XY = findNextEmptyCell();
		if (XY == null) {
			return null;
		}
		yOfNextEmptyCell = XY[0];
		xOfNextEmptyCell = XY[1];
		
		// Construct array list to contain 9 new grids.
		ArrayList<Grid> grids = new ArrayList<Grid>();

		// Create 9 new grids as described in the comments above. Add them to grids.
		for (int n = 1; n < 10; n++) {
			Grid g = new Grid(this);
			g.values[yOfNextEmptyCell][xOfNextEmptyCell] = n;
			grids.add(g);
		}
		
		return grids;
	}
	
	private boolean checkBlock(int row, int col, HashSet<Integer> s) {
		for (int j = row; j < row + 3; j++) {
			for (int i = col; i < col + 3; i++) {
				if (values[j][i] != 0 && s.add(values[j][i]) == false) {
					return false;
				}
			}
		}
		return true;
	}
	
	//
	// COMPLETE THIS
	//
	// Returns true if this grid is legal. A grid is legal if no row, column, or
	// 3x3 block contains a repeated 1, 2, 3, 4, 5, 6, 7, 8, or 9.
	//
	
	public boolean isLegal()
	{
		HashSet<Integer> numbers = new HashSet<Integer>();
		
		// Check every row. If you find an illegal row, return false.
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				if(values[j][i] != 0 && numbers.add(values[j][i]) == false) {
					return false;
				}
			}
			numbers.clear();
		}
		
		// Check every column. If you find an illegal column, return false.
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				if (values[i][j] != 0 && numbers.add(values[i][j]) == false) {
					return false;
				}
			}
			numbers.clear();
		}
		
		// Check every block. If you find an illegal block, return false.
		for (int j = 0; j < 9; j+=3) {
			for (int i = 0; i < 9; i+=3) {
				if(checkBlock(j, i, numbers) == false) {
					return false;
				}
				numbers.clear();
			}
		}
		
		// All rows/cols/blocks are legal.
		return true;
	}

	
	//
	// COMPLETE THIS
	//
	// Returns true if every cell member of values[][] is a digit from 1-9.
	//
	public boolean isFull()
	{
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				if(values[j][i] > 9 || values[j][i] < 1) {
					return false;
				}
			}
		}
		return true;
	}
	

	//
	// COMPLETE THIS
	//
	// Returns true if x is a Grid and, for every (i,j), 
	// x.values[i][j] == this.values[i][j].
	//
	public boolean equals(Object x)
	{
		Grid xGrid = (Grid) x;
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				if (xGrid.values[j][i] != this.values[j][i]) {
					return false;
				}
			}
		}
		return true;
	}
}
