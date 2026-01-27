package Graph.Number_of_Islands;

import java.util.*;

public class numOfIslands {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // ---------------------- Test Case 1 ----------------------
        // Example 1 (should be 1)
        char[][] grid1 = new char[][]{
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };

        System.out.println("Test 1:");
        System.out.println("  grid = ");
        printGrid(grid1);
        int ans1 = sol.numIslands(copyGrid(grid1));
        System.out.println("  result = " + ans1);
        System.out.println("  expect = 1");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 2 ----------------------
        // Example 2 (should be 3)
        char[][] grid2 = new char[][]{
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };

        System.out.println("Test 2:");
        System.out.println("  grid = ");
        printGrid(grid2);
        int ans2 = sol.numIslands(copyGrid(grid2));
        System.out.println("  result = " + ans2);
        System.out.println("  expect = 3");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 3 ----------------------
        // All water (should be 0)
        char[][] grid3 = new char[][]{
            {'0','0','0'},
            {'0','0','0'}
        };

        System.out.println("Test 3:");
        System.out.println("  grid = ");
        printGrid(grid3);
        int ans3 = sol.numIslands(copyGrid(grid3));
        System.out.println("  result = " + ans3);
        System.out.println("  expect = 0");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 4 ----------------------
        // All land (one big island) (should be 1)
        char[][] grid4 = new char[][]{
            {'1','1','1'},
            {'1','1','1'},
            {'1','1','1'}
        };

        System.out.println("Test 4:");
        System.out.println("  grid = ");
        printGrid(grid4);
        int ans4 = sol.numIslands(copyGrid(grid4));
        System.out.println("  result = " + ans4);
        System.out.println("  expect = 1");
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 5 ----------------------
        // Single cell land (should be 1)
        char[][] grid5 = new char[][]{
            {'1'}
        };

        System.out.println("Test 5:");
        System.out.println("  grid = ");
        printGrid(grid5);
        int ans5 = sol.numIslands(copyGrid(grid5));
        System.out.println("  result = " + ans5);
        System.out.println("  expect = 1");
        System.out.println("-----------------------------------");
    }

    // Because your solution mutates grid (marks visited to '0'),
    // we copy before calling so we can still print the original.
    private static char[][] copyGrid(char[][] grid) {
        char[][] copy = new char[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            copy[i] = Arrays.copyOf(grid[i], grid[i].length);
        }
        return copy;
    }

    private static void printGrid(char[][] grid) {
        for (char[] row : grid) {
            System.out.println("  " + Arrays.toString(row));
        }
    }
}

class Solution {
    public int numIslands(char[][] grid) {
        int rowLen = grid.length;
        int count = 0;
        
        // set up a direction nested arr
        int[][] direction = new int[][]{
            {1, 0}, {0, 1}, {-1, 0}, {0, -1} // arr literal needs to use {}
        };

        for (int i = 0; i < rowLen; i++) {
            int colLen = grid[i].length;
            for (int j = 0; j < colLen; j++) {
                char curr = grid[i][j];
                if (curr == '1') {
                    count++;

                    Deque<int[]> dq = new ArrayDeque<>(); // store BFS point
                    grid[i][j] = '0'; // mark the current point as '0'
                    dq.offer(new int[]{ i, j });
    
                    while (!dq.isEmpty()) {
                        int[] currPoint = dq.poll();
                    
                        for (int k = 0; k < 4; k++) {
                            int[] currDirect = direction[k];

                            int nextR = currPoint[0] + currDirect[0];
                            int nextC = currPoint[1] + currDirect[1];
                            if (nextR < 0 || nextR > rowLen - 1 || nextC < 0 || nextC > colLen - 1)
                                continue;
                            if (grid[nextR][nextC] == '0') continue;

                            grid[nextR][nextC] = '0'; // once decide enque, mark it for performance
                            // stay in Deque, will let front of queue still process them!
                            dq.offer(new int[]{ nextR, nextC });
                        }
                    }
                }
            }
        }
        return count;
    }
}
