import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.api.utils.Timer;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.methods.Skill;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.SceneObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Rubicon on 3/8/15.
 */
@ScriptManifest(author = "Rubicon",
        category = Category.WOODCUTTING,
        description = "Cuts then burns logs, with level detection!",
        name = "JuicyFMWC",
        servers = { "PKHonor" },
        version = 1.0)
public class Launch extends Script implements Paintable {
    private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();
    Timer timer;
    Image thiev;
    public static int Cuts;

    public static int START_EXP_FM = Skill.FIREMAKING.getExperience();
    public static int START_EXP_WC = Skill.WOODCUTTING.getExperience();

    public static int getXpGained() {
        final int xpGainedFM = Skill.FIREMAKING.getExperience() - START_EXP_FM;
        final int xpGainedWC = Skill.WOODCUTTING.getExperience() - START_EXP_WC;

        return xpGainedFM + xpGainedWC;
    }
    public boolean onExecute(){
        timer = new Timer();

        try {
            URL url = new URL("http://oi59.tinypic.com/eplzip.jpg");
            thiev = ImageIO.read(url);
        }catch(IOException e){
            e.printStackTrace();
        }
        strategies.add(new Woodcutting());
        strategies.add(new Firemaking());
        //strategies.add(new Camera());

        provide(strategies);

        return true;
    }


    public void paint(Graphics g) {

        int fm = Skill.FIREMAKING.getRealLevel();
        int wc = Skill.WOODCUTTING.getRealLevel();
        if(fm > 99){ fm = 99; }
        if(wc > 99){ wc = 99; }

        g.setColor(Color.white);

        g.drawImage(thiev, 2, 340, null);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        g.drawString("1.0", 102, 391);//Version
        g.drawString("Rubicon", 97, 409);//Author
        g.drawString(timer.toString(), 118, 428);//Time Ran
        g.drawString(""+Woodcutting.getTree(), 80, 445);//Current Tree
        g.drawString(""+getXpGained(), 267, 427);//XP Gained
        g.drawString(""+Skill.WOODCUTTING.getRemaining() + "(WC) " + Skill.FIREMAKING.getRemaining() + "(FM)", 234, 409);//XP TTL
        g.drawString(""+wc + "(WC) " + fm + "(FM)", 292, 391);//Current Level


    }

}
