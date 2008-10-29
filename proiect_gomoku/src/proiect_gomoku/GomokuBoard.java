 /*
  *Copyright 2008 Cristian Achim
  *
  * Licensed under the Apache License, Version 2.0 (the "License");
  * you may not use this file except in compliance with the License.
  * You may obtain a copy of the License at
  *
  * http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing,
  * software distributed under the License is distributed on an "AS IS" BASIS,
  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  * See the License for the specific language governing permissions and
  * limitations under the License.
  *
  */
package proiect_gomoku;

public class GomokuBoard {
    private static char board[][];
    private static int size;
    char winner;

    GomokuBoard(){
        int row , column;
        size=15;
        board=new char[15][15];
        for(row=0 ; row<15 ; row++){
            for(column=0 ; column<15 ; column++){
                board[row][column]='o';
            }
        }
        winner='o';
    }

    GomokuBoard(int size){
        int row , column;
        board=new char[size][size];
        for(row=0 ; row<size ; row++){
            for(column=0 ; column<size ; column++){
                board[row][column]='o';
            }
        }
        winner='o';
    }

    
    public boolean moveWhite(int row , int column){
        if(board[row][column] == 'o'){
            board[row][column]='w';
            return true;
        }
        return false;
    }

    public boolean moveBlack(int row , int column){
        if(board[row][column] == 'o'){
            board[row][column]='b';
            return true;
        }
        return false;
    }

    public boolean removeWhite(int row , int column){
        if(board[row][column] == 'w'){
            board[row][column]='o';
            return true;
        }
        return false;
    }

    public boolean removeBlack(int row , int column){
        if(board[row][column] == 'b'){
            board[row][column]='b';
            return true;
        }
        return false;
    }

    public char checkWinner(char side){
        char aux[][]=new char[size][size];
        int row,column;
        char s='o';
        int limit=size - 5;
        for(row=0 ; row<size ; row++){
            for(column=0 ; column<size ; column++){
                aux[row][column]=board[row][column];
            }
        }

        for(row=0 ; row<size ; row++){
            for(column=0 ; column<size ; column++){
                if(aux[row][column] == 'z'){
                    continue;
                }
                if(side == 'o'){
                    s=aux[row][column];
                }
                if(s != 'o' && s == side){
                    {
                        if(column <= limit){
                            if(check_horizontal_winner(aux , row , column)){
                                winner=s;
                                return s;
                            }
                        }
                        if(row <= limit){
                            if(check_vertical_winner(aux , row , column)){
                                winner=s;
                                return s;
                            }
                        }
                        if(row <= limit && column <= limit){
                            if(check_diagonal_winner(aux , row , column)){
                                winner='s';
                                return s;
                            }
                        }
                    }
                }
            }
        }

        return 'o';
    }

    private boolean check_horizontal_winner(char aux[][] , int row , int column){
        int length=1;
        char s=aux[row][column];
        
        while(column < size){
            column++;
            if(aux[row][column] != s){
                break;
            }
            length++;
            aux[row][column]='z';
        }
        if(length == 5){
            return true;
        }

        return false;
    }
    
    private boolean check_vertical_winner(char aux[][] , int row , int column){
        int length=1;
        char s=aux[row][column];

        while(row < size){
            row++;
            if(aux[row][column] != s){
                break;
            }
            length++;
            aux[row][column]='z';
        }
        if(length == 5){
            return true;
        }

        return false;
    }

    private boolean check_diagonal_winner(char aux[][] , int row , int column){
        int length=1;
        char s=aux[row][column];

        while(row < size && column < size){
            row++;
            column++;
            if(aux[row][column] != s){
                break;
            }
            length++;
            aux[row][column]='z';
        }
        if(length == 5){
            return true;
        }

        return false;
    }
}
