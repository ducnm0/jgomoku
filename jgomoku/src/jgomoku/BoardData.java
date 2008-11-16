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

package jgomoku;

public class BoardData {
    protected static char board[][];
    protected static int size;

    public BoardData(){
     int row , column;
        size=15;
        board=new char[15][15];
        for(row=0 ; row<15 ; row++){
            for(column=0 ; column<15 ; column++){
                board[row][column]='o';
            }
        }
 }
 public BoardData(int size){
        int row , column;

        board=new char[size][size];
        for(row=0 ; row<size ; row++){
            for(column=0 ; column<size ; column++){
                board[row][column]='o';
            }
        }
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
}
