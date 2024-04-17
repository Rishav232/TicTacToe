package Model;

import java.util.Scanner;

public class Player {
    private String name;
    private Symbol symbol;
    private PlayerType playerType;
    private static Scanner sc=new Scanner(System.in);
    
    public Player(String name, Symbol symbol, PlayerType playerType) {
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Symbol getSymbol() {
        return symbol;
    }


    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }


    public PlayerType getPlayerType() {
        return playerType;
    }


    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
    public Move playerMove(Game game)
    {
        System.out.println("Enter the row number");
        int rowInput=sc.nextInt();

        System.out.println("Enter the col number");
        int colInput=sc.nextInt();


        return new Move(this, new Cell(rowInput, colInput));
    } 
    
}
