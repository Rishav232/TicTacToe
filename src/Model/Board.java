package Model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int dimensions;
    private List<List<Cell>>cells;

    Board(int dimensions)
    {
        this.dimensions=dimensions;

        cells=new ArrayList<>();

        for(int i=0;i<dimensions;i++)
        {
            cells.add(new ArrayList<>());

            for(int j=0;j<dimensions;j++)
            {
                cells.get(i).add(new Cell(i, j));
            }
        }
    }

    public int getDimensions() {
        return dimensions;
    }

    public void setDimensions(int dimensions) {
        this.dimensions = dimensions;
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    public void setCells(List<List<Cell>> cells) {
        this.cells = cells;
    }
    public void printBoard()
    {
        for(List<Cell>cell:cells)
        {
            for(Cell c:cell)
            {
                c.printCell();
            }
            System.out.println();
        }
    }

}
