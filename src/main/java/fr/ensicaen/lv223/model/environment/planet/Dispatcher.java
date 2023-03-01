package fr.ensicaen.lv223.model.environment.planet;

import fr.ensicaen.lv223.model.environment.cells.Cell;
import fr.ensicaen.lv223.model.environment.cells.CellFactory;
import fr.ensicaen.lv223.model.environment.cells.CellType;
import fr.ensicaen.lv223.model.environment.cells.specials.ExtractableCell;
import fr.ensicaen.lv223.model.environment.cells.specials.FoodCell;
import fr.ensicaen.lv223.model.environment.cells.specials.LakeCell;
import fr.ensicaen.lv223.model.environment.cells.specials.MineralCell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dispatcher {

    private List<List<Cell>> cells;

    public Dispatcher(List<List<Cell>> cells){
        this.cells = cells;
    }
    public void dispatched(CellType cellType,double stock){
        double intervalle = stock;
        double res;
        Random r = new Random(System.currentTimeMillis());

        for(List<Cell> list : this.cells){
            for(Cell a : list){
                if(a.getType() == cellType){
                    res = Math.floor(intervalle * r.nextDouble() + 1.0);

                    if(intervalle == 1.0 || intervalle == 0.0){
                        dispatchConstant(cellType);
                        checkStocks(cellType,stock);
                        return;
                    }

                    intervalle = intervalle - res;
                    ((ExtractableCell) a).setQuantity(res);
                }
            }
        }
    }
    private void dispatchConstant( CellType cellType ){
        double max = 0.0;
        Cell maxCell = null;
        List<Cell> tempList = new ArrayList<>();
        for(List<Cell> list : this.cells){
            for(Cell a : list){
                if(a.getType() == cellType){
                    if(max < ((ExtractableCell) a).getQuantity() ){
                        max = ((ExtractableCell) a).getQuantity();
                        maxCell = a;
                    }
                    if(((ExtractableCell) a).getQuantity() == 0.0){tempList.add(a);}
                }
            }
        }

        tempList.add(maxCell);
        for(List<Cell> list : this.cells){
            for(Cell c : list){
                if(c.getType() == cellType){
                    if(tempList.contains(c)){
                        ((ExtractableCell) c).setQuantity((int)(max/tempList.size()));
                    }
                }
            }
        }
    }

    public void searchCellWithType(CellType cellType,double quantity ){
        for(List<Cell> lstCell : this.cells){
            for(Cell c  : lstCell){
                if(c.getType() == cellType){
                    ((ExtractableCell) c).addQuantity(quantity);
                    return;
                }
            }
        }
    }

    public void checkStocks(CellType cellType,double stock){
        double alea = 0;
        for(List<Cell> list : this.cells){
            for(Cell c : list){
                if(c.getType() == cellType){
                    alea+=((ExtractableCell) c).getQuantity();
                }
            }
        }
        searchCellWithType(cellType,(stock-alea));

    }
}
