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

public class GameController {
    private GomokuBoard gomokuBoard;
    private UserInterface humanUserInterface;
    private boolean humanWhite;

    GameController(){
        gomokuBoard=new GomokuBoard();
        humanUserInterface=new GraphicalInterfaceController();
        humanUserInterface.setCallback(this);
    }

    GameController(boolean graphical){
        gomokuBoard=new GomokuBoard();
        if(graphical == true){
            humanUserInterface=new GraphicalInterfaceController();
            humanUserInterface.setCallback(this);
        }
        else{
            humanUserInterface=new TextInterface();
            humanUserInterface.setCallback(this);
        }
    }

    public void newGame(){
        
    }

    public void updateInterface(int row , int column ,
            boolean remove , boolean white){
        
    }

    public void saveGame(String fileName){
        
    }

    public void loadGame(String fileName){
        
    }

    public void interfaceCallbackEntryPoint(String message){
        
    }

    public void gameAiCallbackEntryPoint(int row , int column){
        
    }
}
