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

interface UserInterface {

    public void setReplayMode();
    void setCallback(GameController gameController);
    void moveWhite(int row , int column);
    void moveBlack(int row , int column);
    void removeWhite(int row , int column);
    void removeBlack(int row , int column);
    void printText(String text);
    void getBlackMove(int whiteMoveRow , int whiteMoveColumn);
    void getWhiteMove(int blackMoveRow , int blackMoveColumn);
    void waitAiMove(boolean blackMove , int row , int column);
    void gameFinished(boolean blackMove , int row , int column);
}
