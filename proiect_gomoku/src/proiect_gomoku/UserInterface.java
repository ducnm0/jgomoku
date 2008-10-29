package proiect_gomoku;

interface UserInterface {
    void setCallback(GameController gameController);
    void getUserInput(String input);
    void moveWhite(int row , int column);
    void moveBlack(int row , int column);
    void removeWhite(int row , int column);
    void removeBlack(int row , int column);
    void printText(String text);
}
