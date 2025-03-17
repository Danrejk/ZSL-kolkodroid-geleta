package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String currentPlayer = "X";
    private int moves = 0;
    private Button[] cells = new Button[9];
    private TextView infoBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the cells
        cells[0] = findViewById(R.id.cell1);
        cells[1] = findViewById(R.id.cell2);
        cells[2] = findViewById(R.id.cell3);
        cells[3] = findViewById(R.id.cell4);
        cells[4] = findViewById(R.id.cell5);
        cells[5] = findViewById(R.id.cell6);
        cells[6] = findViewById(R.id.cell7);
        cells[7] = findViewById(R.id.cell8);
        cells[8] = findViewById(R.id.cell9);

        infoBox = findViewById(R.id.infoBox);

        // Set up click listeners for each cell
        for (int i = 0; i < cells.length; i++) {
            final int index = i;
            cells[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCellClick(index);
                }
            });
        }
    }

    private void onCellClick(int index) {
        Button cell = cells[index];

        // Check if the cell is already clicked
        if (!cell.getText().toString().equals("")) {
            return;
        }

        // Place the current player's mark
        cell.setText(currentPlayer);
        moves++;

        // Check for winner
        String winner = calculateWinner();
        if (!winner.equals("")) {
            infoBox.setText("Winner: " + winner);
            return;
        }

        // Check if it's a draw
        if (moves == 9) {
            infoBox.setText("Draw!");
            return;
        }

        // Change to the next player
        currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
        infoBox.setText("Next player: " + currentPlayer);
    }

    private String calculateWinner() {
        int[][] winningPatterns = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
                {0, 4, 8}, {2, 4, 6}             // Diagonals
        };

        for (int[] pattern : winningPatterns) {
            String a = cells[pattern[0]].getText().toString();
            String b = cells[pattern[1]].getText().toString();
            String c = cells[pattern[2]].getText().toString();

            if (a.equals(b) && b.equals(c) && !a.equals("")) {
                return a; // X or O
            }
        }

        return ""; // No winner yet
    }
}
