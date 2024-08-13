package com.cx.sudokuapi.web;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sudoku")
public class SudokuController {

    private final SudokuService sudokuService;

    public SudokuController(SudokuService sudokuService) {
        this.sudokuService = sudokuService;
    }

    @PostMapping("/init")
    public ResponseEntity<SudokuResponse> initGame() {
        return ResponseEntity.ok(sudokuService.initializeGame());
    }

    @PostMapping("/submit")
    public ResponseEntity<MoveResponse> submitMove(@RequestBody MoveRequest request) {
        return ResponseEntity.ok(sudokuService.submitMove(request));
    }

    @PostMapping("/clear")
    public ResponseEntity<SudokuResponse> clearNumber(@RequestBody ClearRequest request) {
        return ResponseEntity.ok(sudokuService.clearNumber(request));
    }

    @GetMapping("/grid")
    public ResponseEntity<SudokuResponse> getGrid(@RequestParam String gameId) {
        return ResponseEntity.ok(sudokuService.getGrid(gameId));
    }


}
