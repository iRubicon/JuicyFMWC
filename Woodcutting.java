import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.methods.Skill;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.SceneObject;

/**
 * Created by Rubicon on 3/8/15.
 */
public class Woodcutting implements Strategy{

    public static Tree getTree(){
        int FM = Skill.FIREMAKING.getRealLevel();
        int WC = Skill.WOODCUTTING.getRealLevel();
        if(FM > WC){
            if(FM < 15){
                return Tree.Normal;
            }else if(WC >= 15 && WC < 30){
                return Tree.Oak;
            }else if(WC >= 30 && WC < 45){
                return Tree.Willow;
            }else if(WC >= 45 && WC < 60){
                return Tree.Maple;
            }else if(WC >= 60 && WC < 75){
                return Tree.Yew;
            }else if(WC >= 75){
                return Tree.Magic;
            }
        }else if(WC > FM || WC == FM){
            if(FM < 15){
                return Tree.Normal;
            }else if(FM >= 15 && FM < 30){
                return Tree.Oak;
            }else if(FM >= 30 && FM < 45){
                return Tree.Willow;
            }else if(FM >= 45 && FM < 60){
                return Tree.Maple;
            }else if(FM >= 60 && FM < 75){
                return Tree.Yew;
            }else if(FM >= 75){
                return Tree.Magic;
            }
        }
        return Tree.Normal;//Failsafe, incase it cant detect the stall it will use the Bakers stall as a fallback.
    }

    public static int getID(Tree tree){

        if(tree == Tree.Normal){
            return 1280;
        }else if(tree == Tree.Oak){
            return 1281;
        }else if(tree == Tree.Willow){
            return 1308;
        }else if(tree == Tree.Maple){
            return 1307;
        }else if(tree == Tree.Yew){
            return 1309;
        }else if(tree == Tree.Magic){
            return 1306;
        }
        return 1280;//Failsafe, incase it cant detect the stall it will use the Bakers stall as a fallback.
    }
    public static void dropSeeds(){
        Item[] OakSeed = Inventory.getItems(5358);
        Item[] WillowSeed = Inventory.getItems(5313);
        Item[] MapleSeed = Inventory.getItems(5314);
        Item[] YewSeed = Inventory.getItems(5315);

        Item[] MagicSeed = Inventory.getItems(5317);
        if(OakSeed != null){
            for(Item seed : OakSeed){
                seed.drop();
            }

        }
        if(WillowSeed != null){
            for(Item seed : WillowSeed){
                seed.drop();
            }
        }
        if(MapleSeed != null){
            for(Item seed : MapleSeed){
                seed.drop();
            }
        }
        if(YewSeed != null){
            for(Item seed : YewSeed){
                seed.drop();
            }
        }
        if(MagicSeed != null){
            for(Item seed : MagicSeed){
                seed.drop();
            }
        }
    }

    public boolean activate() {
        if(Players.getMyPlayer().getAnimation() == -1 && !Inventory.isFull()){
            return true;
        }
        return false;
    }


    public void execute() {
        dropSeeds();
        SceneObject[] tree = SceneObjects.getNearest(getID(getTree()));
        try{
            if(tree != null && tree[0] != null){
                tree[0].interact(0);
                Time.sleep(1500);
            }

        }catch(ArrayIndexOutOfBoundsException e){

        }

    }




    public enum Tree {
        Normal, Oak, Willow, Maple, Yew, Magic
    }


}
