package chaosecke;

/**
 * Dies ist die Rechenklasse des Chaoseckeprogramms. Sie nimmt die Groesse des Feldes entgegen und rechnet die Anzahl der Randberuehrungen aus.
 * 
 * @author Lukas Schramm
 * @version 1.0
 *
 */
public class ChaosEcke {
	
	private int[][] numbers;
	private int[] size = new int[2];
	private int counter = 1;
	private int numberOfUsedFields = 1;
	private int rideshift = 1;
	private int downshift = 1;
	private int[] position = {0,0};
	private boolean done = false;
	
	/**
	 * Diese Methode berechnet ausgehend von den eingelesenen Werten das Ergebnis der Berechnung und gibt bei unguenstigen Zahlen entsprechende Meldungen aus.
	 * @param x Die Breite des Feldes.
	 * @param y Die Hoehe des Feldes.
	 * @return Gibt die Anzahl an Randberuehrungen zurueck.
	 */
	public int berechne(int x, int y) {
		numbers = new int[x][y];
		size[0] = x;
		size[1] = y;
		for(int xf=0;xf<size[0];xf++) {
			for(int yf=0;yf<size[1];yf++) {
				numbers[xf][yf] = -1;
			}
		}
		numbers[0][0] = -1;
		
		while(!done) {
			if(checknext()) {
				position[0]+=rideshift;
				position[1]+=downshift;
				if(numbers[position[0]][position[1]]==-1) {
					numberOfUsedFields++;
				}
				numbers[position[0]][position[1]] = counter;
			} else {
				checktermination();
				changedirection();
			}
		}
		
		if(x<1 || y<1) {
			return -1;
		}
		
		int sum = size[0]*size[1];
		if(sum%2==0) {
			if(sum!=numberOfUsedFields*2) {
				return -2;
			} else {
				return counter;
			}
		} else {
			if(sum!=numberOfUsedFields*2-1) {
				return -2;
			} else {
				return counter;
			}
		}
	}
	
	/**
	 * Diese Methode guckt anhand der Pruefung auf eine ArrayIndexOutOfBoundsException, ob der Rand beruehrt wurde oder nicht.
	 * @return Gibt boolean zurueck, ob man in der aktuellen Richtung weiterrechnen kann.
	 */
	private boolean checknext() {
		try {
			int num = numbers[position[0]+rideshift][position[1]+downshift];
			num++;
			counter += (num*0);
			return true;
		} catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
	
	/**
	 * Diese Methode wechselt anhand der Eigenschaften des getroffenen Randes die Richtung des Zahlenstrahls.
	 */
	private void changedirection() {
		if(!done) {
			if(position[0]==0 || position[0]==size[0]-1) {
				rideshift=-rideshift;
			}
			if(position[1]==0 || position[1]==size[1]-1) {
				downshift=-downshift;
			}
			counter++;
		}
	}
	
	/**
	 * Diese Methode ueberprueft, ob man in einer Ecke angekommen ist und damit das Programm enden muss.
	 */
	private void checktermination() {
		if((position[0]==size[0]-1 && (position[1]==0||position[1]==size[1]-1)) ||
		   (position[0]==0)        && (position[1]==0||position[1]==size[1]-1)) {
			done=true;
		}		
	}
}