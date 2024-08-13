package com.cx.sudokuapi.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SudokuResponse {
    private String gameId;
    private int[][] grid;

    // Constructors, Getters, and Setters
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class MoveRequest {
    private String gameId;
    private int row;
    private int col;
    private int number;

    // Constructors, Getters, and Setters
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class MoveResponse {
    private boolean valid;
    private boolean completed;
    private int[][] grid;


    // Constructors, Getters, and Setters
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ClearRequest {
    private String gameId;
    private int row;
    private int col;

    // Constructors, Getters, and Setters
}
