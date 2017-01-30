package thread;

import exceptions.InvalidCoordinates;
import io.Output;
import map.Map;
import qfa.carbonit.carte_aux_tresors.Adventurer;

public class act implements Runnable {

	private Adventurer adventurer;
	private Map map;
	
	public act(Map map, Adventurer adventurer) {
		
		this.map = map;
		this.adventurer= adventurer;
	}
	
	public void run() {
			
		try {
			if (map.containsTreasure(adventurer.getPosX(), adventurer.getPosY())) {
				int value = map.loot(adventurer.getPosX(), adventurer.getPosY());
				adventurer.addTreasure(value);
				String s = (value>1) ? "s !" : " !";
				Output.writeReport("Il y a quelque chose ici..." + value + " trésor" + s);
			}
		} catch (InvalidCoordinates e) {
			System.out.println("impossible to get the treasure : " + e);
			e.printStackTrace();
		}
		
		if(!adventurer.isArrived()) {
			char movement = adventurer.getNextMove();			
			
			if (movement == 'A') Output.writeReport(adventurer.advance(map));
			
			if (movement == 'G') Output.writeReport(adventurer.rotateLeft());
			
			if (movement == 'D') Output.writeReport(adventurer.rotateRight());
		}
	}
}
