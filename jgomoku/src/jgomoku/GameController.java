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

public class GameController/* extends TimerTask*/{
    private GomokuGame gomokuGame;
    private UserInterface humanUserInterface;
    private boolean blackHuman , whiteHuman;
    private GomokuGameHistory gameHistory;
    private boolean waitMove;
    private boolean waitBlack;
    private boolean doingReplay;
    private Thread t;
    
    GameController(){
        humanUserInterface=new GraphicalInterfaceController(15 , 15);
        humanUserInterface.setCallback(this);
        waitMove=false;
        doingReplay=false;
    }

    GameController(boolean graphical){
        if(graphical == true){
            humanUserInterface=new GraphicalInterfaceController(15 , 15);
        }
        else{
            humanUserInterface=new TextInterface();
        }
        humanUserInterface.setCallback(this);
        waitMove=false;
        doingReplay=false;
    }

    public void newGame(boolean blackHuman , boolean whiteHuman){
        gomokuGame=new GomokuGame(15);
        if(blackHuman){
            humanUserInterface.printText("waiting for black move");
        }
        else{
            humanUserInterface.printText("waiting for ai move");
        }
        if(t != null){
            if(t.isAlive()){
                t.interrupt();
            }
        }
        this.blackHuman=blackHuman;
        this.whiteHuman=whiteHuman;
        this.waitMove=true;
        this.waitBlack=true;
        if(! blackHuman){
            t=new Thread(new GomokuAi(gomokuGame.exportPositionToAi() , true , this , 2));
            t.start();
        }
    }
    
    public boolean removeOldGame(){
        if(this.waitMove || this.doingReplay){
            this.waitMove=false;
            this.doingReplay=false;

            return true;
        }

        return false;
    }

    private void saveGame(String fileName){
        if(this.gomokuGame.saveGame(fileName)){
            this.humanUserInterface.printText("game saved");
        }
        else{
            this.humanUserInterface.printText("game saving failed");
        }
    }

    private void loadGame(String fileName){
        GomokuGameHistory ggh;

        ggh=new GomokuGameHistory();
        if(ggh.loadGame(fileName)){
            gameHistory=ggh;
            gomokuGame=new GomokuGame(gameHistory);
            doingReplay=true;
            humanUserInterface.setReplayMode();
        }
        else{
            humanUserInterface.printText("game file loading failed");
        }
    }

    private void makeMove(int row , int column){
        if(! this.waitMove){
            System.out.println("error not waiting move");
            return;
        }
        if(waitBlack && blackHuman){
            if(gomokuGame.moveBlack(row, column)){
                if(gomokuGame.isGameOver()){
                    humanUserInterface.printText("black won the game");
                    humanUserInterface.gameFinished(true , row , column);
                    doingReplay=true;
                    waitMove=false;
                    waitBlack=false;
                }
                else if(whiteHuman){
                    humanUserInterface.printText("waiting for white move");
                    humanUserInterface.getWhiteMove(row , column);
                    waitBlack=false;
                }
                else{
                    humanUserInterface.printText("waiting for ai move");
                    humanUserInterface.waitAiMove(true , row , column);
                    t=new Thread(new GomokuAi(gomokuGame.exportPositionToAi() , false , this , 2));
                    waitBlack=false;
                    t.start();
                }
                //waitBlack=false;-moved into to if elses
            }
            else{
                humanUserInterface.printText("illegal move");
            }
        }
        else if(! waitBlack && whiteHuman){
            if(gomokuGame.moveWhite(row, column)){
                if(gomokuGame.isGameOver()){
                    humanUserInterface.printText("white won the game");
                    humanUserInterface.gameFinished(false , row , column);
                    doingReplay=true;
                    waitMove=false;
                    waitBlack=true;
                }
                else if(blackHuman){
                    humanUserInterface.printText("waiting for black move");
                    humanUserInterface.getBlackMove(row , column);
                    waitBlack=true;
                }
                else{
                    humanUserInterface.printText("waiting for ai move");
                    humanUserInterface.waitAiMove(false , row , column);
                    t=new Thread(new GomokuAi(gomokuGame.exportPositionToAi() , false , this , 2));
                    waitBlack=true;
                    t.start();
                }
                //waitBlack=true;-moved into the if elses
            }
            else{
                humanUserInterface.printText("illegal move");
            }
        }
        else if(waitBlack && ! blackHuman){
            if(gomokuGame.moveBlack(row, column)){
                if(gomokuGame.isGameOver()){
                    humanUserInterface.printText("black won the game");
                    humanUserInterface.gameFinished(true , row , column);
                    doingReplay=true;
                    waitMove=false;
                    waitBlack=false;
                }
                else if(whiteHuman){
                    humanUserInterface.printText("waiting for white move");
                    humanUserInterface.getWhiteMove(row, column);
                    waitBlack=false;
                }
                else{
                    humanUserInterface.printText("waiting for ai move");
                    t=new Thread(new GomokuAi(gomokuGame.exportPositionToAi() , false , this , 2));
                    humanUserInterface.waitAiMove(true, row, column);
                    waitBlack=false;
                    t.start();
                }
                //waitBlack=false;-moved into the if elses
            }
            else{
                humanUserInterface.printText("illegal ai move");
            }
        }
        else if(! waitBlack && ! whiteHuman){
            if(gomokuGame.moveWhite(row, column)){
                if(gomokuGame.isGameOver()){
                    humanUserInterface.printText("white won the game");
                    humanUserInterface.gameFinished(false, row, column);
                    doingReplay=true;
                    waitMove=false;
                    waitBlack=true;
                }
                else if(blackHuman){
                    humanUserInterface.printText("waiting for black move");
                    humanUserInterface.getBlackMove(row, column);
                    waitBlack=true;
                }
                else{
                    humanUserInterface.printText("waiting for ai move");
                    t=new Thread(new GomokuAi(gomokuGame.exportPositionToAi() , true , this , 2));
                    humanUserInterface.waitAiMove(false, row, column);
                    waitBlack=true;
                    t.start();
                }
                //waitBlack=true;-moved to the if elses
            }
            else{
                humanUserInterface.printText("illegal ai move");
            }
        }
        else{
            humanUserInterface.printText("weird boolean error");
        }
    }

    private void previousMove(){
        if(doingReplay){
            Move m=gomokuGame.previousMove();
            if(m == null){
                humanUserInterface.printText("reached first move");
            }
            else{
                if(m.isBlack){
                    humanUserInterface.removeBlack(m.row, m.column);
                }
                else{
                    humanUserInterface.removeWhite(m.row, m.column);
                }
            }
        }
        else{
            humanUserInterface.printText("error no game replay");
        }
    }

    private void nextMove(){
        if(doingReplay){
            Move m=gomokuGame.nextMove();
            if(m == null){
                humanUserInterface.printText("reached last move");
            }
            else{
                if(m.isBlack){
                    humanUserInterface.moveBlack(m.row, m.column);
                }
                else{
                    humanUserInterface.moveWhite(m.row, m.column);
                }
            }
        }
        else{
            humanUserInterface.printText("error no game replay");
        }
    }

    //the callback function from gomokuai , graphicalinterface or textinterface
    public void sendPlayerInput(String input){
        String token;
        StringTokenizer st=new StringTokenizer(input);
        int value1 , value2;
        boolean blackHuman , whiteHuman;
        /*
         * all posible input commands:
         * 
         * move row column
         * previous
         * next
         * new blackplayer whiteplayer ; whiteplayer,blackplayer=human | computer
         * load filelocationandnamestring
         * save filelocationandnamestring
         */
        
        if(! st.hasMoreTokens()){
            return;
        }
        token=st.nextToken();

        if(token.equals("move")){
            if(! st.hasMoreTokens()){
                return;
            }
            token=st.nextToken();

            try{
                value1=Integer.parseInt(token);
            }
            catch(Exception e){
                e.printStackTrace();
                return;
            }
            if(! st.hasMoreTokens()){
                return;
            }
            token=st.nextToken();

            try{
                value2=Integer.parseInt(token);
            }
            catch(Exception e){
                e.printStackTrace();
                return;
            }

            makeMove(value1 , value2);
        }
        else if(token.equals("previous")){
            previousMove();
        }
        else if(token.equals("next")){
            nextMove();
        }
        else if(token.equals("new")){
            if(! st.hasMoreTokens()){
                return;
            }
            token=st.nextToken();

            if(token.equals("human")){
                blackHuman=true;
            }
            else if(token.equals("computer")){
                blackHuman=false;
            }
            else{
                return;
            }
            if(! st.hasMoreTokens()){
                return;
            }
            token=st.nextToken();

            if(token.equals("human")){
                whiteHuman=true;
            }
            else if(token.equals("computer")){
                whiteHuman=false;
            }
            else{
                return;
            }

            newGame(blackHuman , whiteHuman);
        }
        else if(token.equals("load")){
            if(! st.hasMoreTokens()){
                return;
            }
            token=st.nextToken();

            loadGame(token);
        }
        else if(token.equals("save")){
            if(! st.hasMoreTokens()){
                return;
            }
            token=st.nextToken();

            saveGame(token);
        }
    }
}
