package Graph.Surrounded_Regions;

import java.util.*;

public class surroundRegions {
    public static void main(String[] args) {
        Solution sol = new Solution();

        // ---------------------- Test Case 1 ----------------------
        // Example 1 from LeetCode
        char[][] board1 = {
                {'X','X','X','X'},
                {'X','O','O','X'},
                {'X','X','O','X'},
                {'X','O','X','X'}
        };
        System.out.println("Test 1:");
        System.out.println("  input:");
        printBoard(board1);

        sol.solve(board1);

        System.out.println("  output:");
        printBoard(board1);
        System.out.println("  expect:");
        printBoard(new char[][]{
                {'X','X','X','X'},
                {'X','X','X','X'},
                {'X','X','X','X'},
                {'X','O','X','X'}
        });
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 2 ----------------------
        // All O's on edge -> should remain O (nothing captured)
        Solution sol2 = new Solution();
        char[][] board2 = {
                {'O','O','O'},
                {'O','O','O'},
                {'O','O','O'}
        };
        System.out.println("Test 2:");
        System.out.println("  input:");
        printBoard(board2);

        sol2.solve(board2);

        System.out.println("  output:");
        printBoard(board2);
        System.out.println("  expect (same as input):");
        printBoard(new char[][]{
                {'O','O','O'},
                {'O','O','O'},
                {'O','O','O'}
        });
        System.out.println("-----------------------------------");

        // ---------------------- Test Case 3 ----------------------
        // One captured region in the middle
        Solution sol3 = new Solution();
        char[][] board3 = {
                {'X','X','X','X','X'},
                {'X','O','O','O','X'},
                {'X','O','X','O','X'},
                {'X','O','O','O','X'},
                {'X','X','X','X','X'}
        };
        System.out.println("Test 3:");
        System.out.println("  input:");
        printBoard(board3);

        sol3.solve(board3);

        System.out.println("  output:");
        printBoard(board3);
        System.out.println("  expect (all inner O captured):");
        printBoard(new char[][]{
                {'X','X','X','X','X'},
                {'X','X','X','X','X'},
                {'X','X','X','X','X'},
                {'X','X','X','X','X'},
                {'X','X','X','X','X'}
        });
        System.out.println("-----------------------------------");
    }

    private static void printBoard(char[][] board) {
        for (char[] row : board) {
            System.out.println("  " + Arrays.toString(row));
        }
    }
}

class Solution {
    /**
    central thought is do two times BFS, since detect one time BFS on any '0' will not be able to know
    is it connect to edge, so:
    1. one time on the edge, BFS on all '0' and mark them safe
    2. one time whole grid traversal to flip mark back => '0', and flip rest '0' => 'X'
     */
    public void solve(char[][] board) {
        int[][] directions = new int[][]{
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
        };

        // travel along the edge
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                char curr = board[i][j];

                if (curr == '#') continue; // skip on the marked

                // for row: only first & last; for col: only first & last
                if (curr == 'O' && ((i == 0 || i == board.length - 1) || 
                (j == 0 || j == board[i].length - 1))) {
                    Deque<int[]> dq = new ArrayDeque<>();
                    dq.offer(new int[]{ i, j });
                    board[i][j] = '#';

                    while (!dq.isEmpty()) {
                        int[] currPoi = dq.poll();
                        int currX = currPoi[0], currY = currPoi[1];

                        for (int d = 0; d < 4; d++) {
                            // four directions
                            int[] currD = directions[d];

                            int nextX = currX + currD[0], nextY = currY + currD[1];
                            if (nextX >= 0 && nextX < board.length && nextY >= 0 
                            && nextY < board[nextX].length && board[nextX][nextY] == 'O') {
                                board[nextX][nextY] = '#'; // mark
                                dq.offer(new int[]{ nextX, nextY }); // enqueue
                            }
                        }
                    }
                }

            }
        }

        // iterate whole grid again
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++) {
                char curr = board[i][j];

                if (curr == '#') board[i][j] = 'O'; // flip makred
                else if (curr == 'O') board[i][j] = 'X'; // flip still unaffected
            }
        }
    }
}