 /*
  *Copyright 2008 Cristian Cola
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

public class TextInterface implements UserInterface{
    private TextBoardData board=null;
    private GameController gc;
    private int size=0;

    public TextInterface(){
        size=15;
        board=new TextBoardData();
        printBoard();
    }

    public TextInterface(int size){
        board=new TextBoardData(size);
        printBoard();
    }

    public void printBoard(){
       System.out.print(" ");
       for(int i=0;i<size;i++){
           if(i<10)
            System.out.print("  "+(i+1));
           else
              System.out.print(" "+(i+1));
       }
       System.out.println();
       for(int j=0;j<size;j++){
           if(j<9)System.out.print((j+1)+" ");
           else System.out.print((j+1)+"");
        for(int i=0;i<size;i++){
           if(board.getValue(j,i)=='o')
                System.out.print("|  ");
           else if(board.getValue(j, i)=='w')
               System.out.print("|X ");
           else
               System.out.print("|O ");
           if(i==size-1)System.out.print("|"+(j+1));
        }
        System.out.println();
        System.out.print("  ");
        for(int i=0;i<size;i++){
            System.out.print("---");
       }
        System.out.println();
       }
        System.out.print(" ");
        for(int i=0;i<size;i++){
           if(i<10)
            System.out.print("  "+(i+1));
           else
              System.out.print(" "+(i+1));
       }
        System.out.println();
    }
    
    @Override
    public void printText(String text){
      
    }

    @Override
    public void removeBlack(int row , int column){
        
    }

    @Override
    public void removeWhite(int row , int column){

    }

    @Override
    public void moveBlack(int row , int column){
        
    }

    @Override
    public void moveWhite(int row , int column){
        
    }

    public String getUserInput(){

        return "";
    }

    @Override
    public void setCallback(GameController gc){
        this.gc=gc;
    }

    @Override
    public void getBlackMove(int whiteMoveRow , int whiteMoveColumn){

    }

    @Override
    public void getWhiteMove(int blackMoveRow , int blackMoveColumn){

    }

    @Override
    public void waitAiMove(boolean blackMove , int row , int column){
        
    }

    @Override
    public void gameFinished(boolean blackMove , int row , int column){
        
    }

}
