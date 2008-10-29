package proiect_gomoku;

public class GameController {
    private GomokuBoard gomokuBoard;
    private UserInterface humanUserInterface;
    private boolean humanWhite;

    GameController(){
        gomokuBoard=new GomokuBoard();
        humanUserInterface=new GraphicalInterface();
    }

    GameController(boolean graphical){
        gomokuBoard=new GomokuBoard();
        if(graphical == true){
            humanUserInterface=new GraphicalInterface();
        }
        else{
            humanUserInterface=new TextInterface();
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
