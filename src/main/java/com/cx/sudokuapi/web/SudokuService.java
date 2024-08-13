package com.cx.sudokuapi.web;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class SudokuService {


    private final Map<String, int[][]> games = new HashMap<>();

    public SudokuResponse initializeGame() {
        String gameId = generateGameId();
        int[][] grid = generateInitialGrid();
        games.put(gameId, grid);
        return new SudokuResponse(gameId, grid);
    }

    public MoveResponse submitMove(MoveRequest request) {
        int[][] grid = games.get(request.getGameId());
        if (grid == null) {
            throw new IllegalArgumentException("Invalid game ID");
        }

        boolean isValid = isMoveValid(grid, request.getRow(), request.getCol(), request.getNumber());
        if (isValid) {
            grid[request.getRow()][request.getCol()] = request.getNumber();
        }

        boolean isCompleted = isGameCompleted(grid);

        return new MoveResponse(isValid, isCompleted, grid);
    }

    public SudokuResponse clearNumber(ClearRequest request) {
        int[][] grid = games.get(request.getGameId());
        if (grid == null) {
            throw new IllegalArgumentException("Invalid game ID");
        }

        grid[request.getRow()][request.getCol()] = 0;

        return new SudokuResponse(request.getGameId(), grid);
    }

    public SudokuResponse getGrid(String gameId) {
        int[][] grid = games.get(gameId);
        if (grid == null) {
            throw new IllegalArgumentException("Invalid game ID");
        }

        return new SudokuResponse(gameId, grid);
    }

    private String generateGameId() {
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }

    private int[][] generateInitialGrid() {
        int[][] grid = new int[9][9];
        // 这里可以加入生成初始数独盘面的逻辑（例如生成数独谜题）
        // 简单起见，返回一个预定义的数独盘面
        grid[0] = new int[]{5, 3, 0, 0, 7, 0, 0, 0, 0};
        grid[1] = new int[]{6, 0, 0, 1, 9, 5, 0, 0, 0};
        grid[2] = new int[]{0, 9, 8, 0, 0, 0, 0, 6, 0};
        grid[3] = new int[]{8, 0, 0, 0, 6, 0, 0, 0, 3};
        grid[4] = new int[]{4, 0, 0, 8, 0, 3, 0, 0, 1};
        grid[5] = new int[]{7, 0, 0, 0, 2, 0, 0, 0, 6};
        grid[6] = new int[]{0, 6, 0, 0, 0, 0, 2, 8, 0};
        grid[7] = new int[]{0, 0, 0, 4, 1, 9, 0, 0, 5};
        grid[8] = new int[]{0, 0, 0, 0, 8, 0, 0, 7, 9};
        return grid;
    }

    private boolean isMoveValid(int[][] grid, int row, int col, int number) {
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == number || grid[i][col] == number) {
                return false;
            }
        }

        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[boxRow + i][boxCol + j] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isGameCompleted(int[][] grid) {
        for (int[] row : grid) {
            for (int cell : row) {
                if (cell == 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
