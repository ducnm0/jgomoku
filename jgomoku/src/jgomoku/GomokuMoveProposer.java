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

import java.util.*;

class ValueMoveComparator implements Comparator{

    @Override
    public int compare(Object arg0, Object arg1) {
        ValueMove v0=(ValueMove) arg0;
        ValueMove v1=(ValueMove) arg1;

        return v1.moveValue-v0.moveValue; // reverse - largest is first
    }
    
}
public class GomokuMoveProposer {

    private List<Move> blackFoursUncapped;
    private List<Move> whiteFoursUncapped;
    private List<Move> blackFoursCapped;
    private List<Move> whiteFoursCapped;
    private List<Move> blackThreesUncapped;
    private List<Move> whiteThreesUncapped;
    private List<Move> blackThreesCapped;
    private List<Move> whiteThreesCapped;
    private List<Move> blackTwosUncapped;
    private List<Move> whiteTwosUncapped;
    private List<Move> blackTwosCapped;
    private List<Move> whiteTwosCapped;
    private List<Move> blackOnesUncapped;
    private List<Move> whiteOnesUncapped;
    private List<Move> blackOnesCapped;
    private List<Move> whiteOnesCapped;

    private List<Move> movesToPropose;

     private static int size=15;

    private List<ValueMove> mList=new ArrayList<ValueMove>();
    private int row , column;
    private int length , aux , i;

    private int blackStones;
    private int whiteStones;

    private Move[] moveHistory=new Move[20];

    private char[][] gomokuPosition;
    private boolean blackToMove;

    public GomokuMoveProposer(){
        blackFoursUncapped=new ArrayList<Move>();
        whiteFoursUncapped=new ArrayList<Move>();
        blackFoursCapped=new ArrayList<Move>();
        whiteFoursCapped=new ArrayList<Move>();
        blackThreesUncapped=new ArrayList<Move>();
        whiteThreesUncapped=new ArrayList<Move>();
        blackThreesCapped=new ArrayList<Move>();
        whiteThreesCapped=new ArrayList<Move>();
        blackTwosUncapped=new ArrayList<Move>();
        whiteTwosUncapped=new ArrayList<Move>();
        blackTwosCapped=new ArrayList<Move>();
        whiteTwosCapped=new ArrayList<Move>();
        blackOnesUncapped=new ArrayList<Move>();
        whiteOnesUncapped=new ArrayList<Move>();
        blackOnesCapped=new ArrayList<Move>();
        whiteOnesCapped=new ArrayList<Move>();
    }

    private void cleanUpLists(){
        blackFoursUncapped.clear();
        whiteFoursUncapped.clear();
        blackFoursCapped.clear();
        whiteFoursCapped.clear();
        blackThreesUncapped.clear();
        whiteThreesUncapped.clear();
        blackThreesCapped.clear();
        whiteThreesCapped.clear();
        blackTwosUncapped.clear();
        whiteTwosUncapped.clear();
        blackTwosCapped.clear();
        whiteTwosCapped.clear();
        blackOnesUncapped.clear();
        whiteOnesUncapped.clear();
        blackOnesCapped.clear();
        whiteOnesCapped.clear();
    }

    private void addBlackUncapped(int index){
        switch(blackStones){
            case 1:
                blackFoursUncapped.add(
                        new Move(
                        moveHistory[index].row ,
                        moveHistory[index].column));
                break;
            case 2:
                blackThreesUncapped.add(
                        new Move(
                        moveHistory[index].row ,
                        moveHistory[index].column));
                break;
            case 3:
                blackTwosUncapped.add(
                        new Move(
                        moveHistory[index].row ,
                        moveHistory[index].column));
                break;
            case 4:
                blackOnesUncapped.add(
                        new Move(
                        moveHistory[index].row ,
                        moveHistory[index].column));
                break;
        }
    }

    private void addWhiteUncapped(int index){
        switch(whiteStones){
            case 1:
                whiteFoursUncapped.add(
                        new Move(
                        moveHistory[index].row ,
                        moveHistory[index].column));
                break;
            case 2:
                whiteThreesUncapped.add(
                        new Move(
                        moveHistory[index].row ,
                        moveHistory[index].column));
                break;
            case 3:
                whiteTwosUncapped.add(
                        new Move(
                        moveHistory[index].row ,
                        moveHistory[index].column));
                break;
            case 4:
                whiteOnesUncapped.add(
                        new Move(
                        moveHistory[index].row ,
                        moveHistory[index].column));
                break;
        }
    }

    private void addBlackCapped(int index){
        switch(blackStones){
            case 1:
                blackFoursCapped.add(
                        new Move(
                        moveHistory[index].row ,
                        moveHistory[index].column));
                break;
            case 2:
                blackThreesCapped.add(
                        new Move(
                        moveHistory[index].row ,
                        moveHistory[index].column));
                break;
            case 3:
                blackTwosCapped.add(
                        new Move(
                        moveHistory[index].row ,
                        moveHistory[index].column));
                break;
            case 4:
                blackOnesCapped.add(
                        new Move(
                        moveHistory[index].row ,
                        moveHistory[index].column));
                break;
        }
    }

    private void addWhiteCapped(int index){
        switch(whiteStones){
            case 1:
                whiteFoursCapped.add(
                        new Move(
                        moveHistory[index].row ,
                        moveHistory[index].column));
                break;
            case 2:
                whiteThreesCapped.add(
                        new Move(
                        moveHistory[index].row ,
                        moveHistory[index].column));
                break;
            case 3:
                whiteTwosCapped.add(
                        new Move(
                        moveHistory[index].row ,
                        moveHistory[index].column));
                break;
            case 4:
                whiteOnesCapped.add(
                        new Move(
                        moveHistory[index].row ,
                        moveHistory[index].column));
                break;
        }
    }

    private void inLineStuff(boolean outOfLine){// outOfLine=true
                        // treat the case of stone lines that end
                        // at the board edge

        if(outOfLine == true){
            if(blackStones != 0){
                try{
                    if(gomokuPosition[moveHistory[length-blackStones].row]
                        [moveHistory[length-blackStones].column] == 'o'){

                        addBlackCapped(length - blackStones);
                    }
                }
                catch(IndexOutOfBoundsException e){
                }
            }

            if(whiteStones != 0){
                try{
                    if(gomokuPosition[moveHistory[length-whiteStones].row]
                        [moveHistory[length-whiteStones].column] == 'o'){

                        addWhiteCapped(length - whiteStones);
                    }
                }
                catch(IndexOutOfBoundsException e){   
                }
            }

            return;
        }

        length++;
        boolean uncapped=true;
        switch(gomokuPosition[row][column]){
            case 'o':
                if(blackStones != 0){
                    uncapped=false;
                    try{
                        if(gomokuPosition[moveHistory[length-blackStones].row]
                                [moveHistory[length-blackStones].column] == 'o'){

                            addBlackUncapped(length - blackStones);
                            uncapped=true;
                        }
                    }
                    catch(IndexOutOfBoundsException e){
                    }
                    finally{
                        if(uncapped){
                            addBlackUncapped(length);
                        }
                        else{
                            addBlackCapped(length);
                        }
                    }

                    blackStones=0;
                }
                if(whiteStones != 0){
                    uncapped=false;
                    try{
                        if(gomokuPosition[moveHistory[length-whiteStones].row]
                                [moveHistory[length-whiteStones].column] == 'o'){

                            addWhiteUncapped(length - whiteStones);
                            uncapped=true;
                        }
                    }
                    catch(IndexOutOfBoundsException e){
                    }
                    finally{
                        if(uncapped){
                            addWhiteUncapped(length);
                        }
                        else{
                            addWhiteCapped(length);
                        }
                    }

                    whiteStones=0;
                }
                break;
            case 'b':
                blackStones++;
                if(whiteStones != 0){
                    try{
                        if(gomokuPosition[moveHistory[length-whiteStones].row]
                                [moveHistory[length-whiteStones].column] == 'o'){

                            addWhiteCapped(length - whiteStones);
                        }
                    }
                    catch(IndexOutOfBoundsException e){
                    }

                    whiteStones=0;
                }
                break;
            case 'w':
                whiteStones++;
                if(blackStones != 0){
                    try{
                        if(gomokuPosition[moveHistory[length-whiteStones].row]
                                [moveHistory[length-whiteStones].column] == 'o'){

                            addBlackCapped(length - whiteStones);
                            uncapped=true;
                        }
                    }
                    catch(IndexOutOfBoundsException e){
                    }
                    
                    blackStones=0;
                }
                break;
        }
        
        moveHistory[length].row=row;
        moveHistory[length].column=column;
    }

    public List<Move> proposeMoves(char[][] gomokuPosition , boolean blackToMove){
        this.gomokuPosition=gomokuPosition;
        this.blackToMove=blackToMove;

        moveHistory=new Move[20];
        for(i=0 ; i<20 ; i++){
            moveHistory[i]=new Move();
        }

        //all lines
        for(row=0 ; row<size ; row++){
            length=0;
            blackStones=0;
            whiteStones=0;
            for(column=0 ; column<size ; column++){
                inLineStuff(false);
            }
            inLineStuff(true);
        }

        //all columns
        for(column=0 ; column<size ; column++){
            length=0;
            blackStones=0;
            whiteStones=0;
            for(row=0 ; row<size ; row++){
                inLineStuff(false);
            }
            inLineStuff(true);
        }

        //diagonals above , parallel to and including the main board matrice diagonal
        for(aux=0 ; aux<size ; aux++){
            length=0;
            blackStones=0;
            whiteStones=0;
            for(column=size-1-aux , row=0 ; column<size ; column++ , row++){
                inLineStuff(false);
            }
            inLineStuff(true);
        }

        //diagonals below and parallel to the main board matrice diagonal
        for(aux=0 ; aux<size-1 ; aux++){
            length=0;
            blackStones=0;
            whiteStones=0;
            for(row=size-1-aux , column=0 ; row<=size-1 ; row++ , column++){
                inLineStuff(false);
            }
            inLineStuff(true);
        }

        //diagonals above , parallel to and including the secondary board matrice diagonal
        for(aux=0 ; aux<size ; aux++){
            length=0;
            blackStones=0;
            whiteStones=0;
            for(row=aux , column=0 ; row>=0 ; row-- , column++){
                inLineStuff(false);
            }
            inLineStuff(true);
        }

        //diagonals below and parallel to the secondary board matrice diagonal
        for(aux=0 ; aux<size-1 ; aux++){
            length=0;
            blackStones=0;
            whiteStones=0;
            for(column=size-1-aux , row=size-1 ; column<=size-1 ; column++ , row--){
                inLineStuff(false);
            }
            inLineStuff(true);
        }

        

        Collections.sort(mList, new ValueMoveComparator());
        if(mList.size() > 12){
            mList=mList.subList(0, 11);
        }
        /*
        if(mList.size() == 0){
            mList.add(new ValueMove(7 , 7 , 0));
        }
*/
        List<Move> l=new ArrayList<Move>();
        Iterator<ValueMove> it=mList.iterator();
        ValueMove m1;
        while(it.hasNext()){
            m1=it.next();
            l.add(new Move(m1.row , m1.column));
        }
        
        return l;
    }
}
