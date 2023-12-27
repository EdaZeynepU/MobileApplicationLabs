package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final String PLAYER_1="X";
    static final String PLAYER_2="O";

    Boolean player1Turn=true;

    byte board [][]=new byte[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout table=findViewById(R.id.board);
        for(int i=0;i<3;i++){
            TableRow row=(TableRow) table.getChildAt(i);
            for(int j=0;j<3;j++){
                Button button = (Button) row.getChildAt(j);
                button.setOnClickListener(new CellListener(i,j));
            }
        }
    }
    public boolean isValidMove(int row,int col){
        return board[row][col]==0;
    }

    public int gameEnded(int row,int col){
        int symbol=board[row][col];
        boolean win=true;
        for(int i=0;i<3;i++){
            if(board[i][col]!=symbol){
                win=false;
            }
        }
        if(win){
            return symbol;
        }
        win=true;
        //check rows
        for(int i=0;i<3;i++){
            if(board[row][i]!=symbol){
                win=false;
            }
        }
        if(win){
            return symbol;
        }
        win=true;
        //check diagonals
        if (row == col) {
            for (int i = 0; i < 3; i++) {
                if (board[i][i] != symbol) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return symbol;
            }
        }
        if (row + col == 2) {
            for (int i = 0; i < 3; i++) {
                if (board[i][2 - i] != symbol) {
                    win = false;
                    break;
                }
            }
            if (win) {
                return symbol;
            }
        }
        return -1;
    }



    class CellListener implements View.OnClickListener{
        int row,col;
        public CellListener(int row, int col){
            this.row=row;
            this.col=col;
        }



        @Override
        public void onClick(View v) {
            if(!isValidMove(row,col)){
                Toast.makeText(MainActivity.this, "Cell is already occupied",
                        Toast.LENGTH_SHORT).show();
                return;

            }
            if(player1Turn){
                ((Button)v).setText(PLAYER_1);
                board[row][col]=1;
            }else{
                ((Button)v).setText(PLAYER_2);
                board[row][col]=2;
            }

            if(gameEnded(row,col)==-1){
                player1Turn=!player1Turn;
            }else if(gameEnded(row,col)==0){
                Toast.makeText(MainActivity.this, "It's a Draw", Toast.LENGTH_SHORT).show();
            }else if(gameEnded(row,col)==1){
                Toast.makeText(MainActivity.this, "PLayer1 Wins", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "PLayer2 Wins", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean("player1Turn",player1Turn);
        byte[] singleBoard=new byte[9];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(board[i], 0, singleBoard, 3 * i, 3);// I changed here because there is an error
        }

        outState.putByteArray("board",singleBoard);
        super.onSaveInstanceState(outState);
    }



    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        player1Turn = savedInstanceState.getBoolean("player1Turn");
        byte[] singleBoard = savedInstanceState.getByteArray("board");

        for(int i=0;i<9;i++){
            board[i/3][i%3] = singleBoard[i];
        }



        TableLayout table=findViewById(R.id.board);
        for(int i=0;i<3;i++){
            TableRow row= (TableRow) table.getChildAt(i);
            for(int j=0;j<3;j++){
                Button button= (Button) row.getChildAt(j);
                if(board[i][j]==1){
                    button.setText(PLAYER_1);
                }else if(board[i][j]==2){
                    button.setText(PLAYER_2);
                }
            }
        }
    }


}

