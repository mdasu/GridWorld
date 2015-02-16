

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;

import java.awt.Color;

/**
 * This class runs a world that contains Termites. <br />
 */
public class TermiteRunner {
    
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        makeFlowers(world, 20);
        
        /*Termite alice = new Termite();
        world.add(alice); */
        
        MyTermite bob = new MyTermite();
        bob.setColor(Color.blue);
        world.add(bob);
        
       /* MyTermite md = new MyTermite();
        md.setColor(Color.yellow);
        world.add(md); */
        
        world.show();
    }
    
    public static void makeFlowers(ActorWorld world, int n) {
        for (int i=0; i<n; i++) {
            world.add(new EternalFlower());
        }
    }
}
