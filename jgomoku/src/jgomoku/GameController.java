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

public class GameController {
    private GomokuBoard gomokuBoard;
    private UserInterface humanUserInterface;
    private boolean humanWhite;
    private GomokuGameHistory gameHistory;

    GameController(){
        humanUserInterface=new GraphicalInterfaceController(15 , 15);
        humanUserInterface.setCallback(this);
    }

    GameController(boolean graphical){
        if(graphical == true){
            humanUserInterface=new GraphicalInterfaceController(15 , 15);
            humanUserInterface.setCallback(this);
        }
        else{
            humanUserInterface=new TextInterface();
            humanUserInterface.setCallback(this);
        }
    }

    public void newGame(boolean blackHuman , boolean whiteHuman){
        gomokuBoard=new GomokuBoard(15);
        humanUserInterface.printText("waiting for black move");
    }

    public void updateInterface(int row , int column ,
            boolean remove , boolean white){
        
    }

    private void saveGame(String fileName){
        
    }

    private void loadGame(String fileName){
        GomokuGameHistory ggh;

        ggh=new GomokuGameHistory();
        if(ggh.loadGame(fileName)){
            gameHistory=ggh;
            gomokuBoard=new GomokuBoard(gameHistory);
        }
        else{
            humanUserInterface.printText("game file loading failed");
        }
    }

    //the callback function from gomokuai , graphicalinterface or textinterface
    public void sendPlayerInput(String input){
        StringTokenizer st=new StringTokenizer(input);
    }
}
