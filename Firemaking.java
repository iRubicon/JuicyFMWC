import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.Player;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Rubicon on 3/10/15.
 */
public class Firemaking implements Strategy {

    private int[] logs = {1512, 1522, 1520, 1518, 1516, 1514};


    public boolean isLog(Item item){
            for(int log : logs){
                if(log == item.getId()){
                    return true;
                }
            }
            return false;

    }

    public boolean activate() {

        if(Inventory.isFull()){
            Woodcutting.dropSeeds();
        }
        if(Inventory.isFull()){
            return true;
        }
        return false;
    }


    private int getItemID(Woodcutting.Tree tree){
        if(tree == Woodcutting.Tree.Normal){
            return 1512;
        }else if(tree == Woodcutting.Tree.Oak){
            return 1522;
        }else if(tree == Woodcutting.Tree.Willow){
            return 1520;
        }else if(tree == Woodcutting.Tree.Maple){
            return 1518;
        }else if(tree == Woodcutting.Tree.Yew){
            return 1516;
        }else if(tree == Woodcutting.Tree.Magic){
            return 1514;
        }
        return 1512;//Failsafe, incase it cant detect the stall it will use the Bakers stall as a fallback.
    }

    public void execute() {
        Item Tinderbox = Inventory.getItem(591);

        for(Item log : Inventory.getItems()){
            if(isLog(log)){
                Inventory.combine(log.getId(), Tinderbox.getId());
                Time.sleep(1000, 1500);
				//Buggy.
            }
        }


    }

}
