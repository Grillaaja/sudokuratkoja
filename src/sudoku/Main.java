package sudoku;

import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) {
		//Määritellään peli 9 taulukkoa sisältävällä objektilla
		//jossa jokaisessa on 9 numeroa. 0 tarkoittaa puuttuvaa numeroa
		
		int[][] peli = {
				  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 0, 0 } 
				};
		long alku = System.currentTimeMillis();
		if(ratkoSudoku(peli)) {
			long loppu = System.currentTimeMillis();
			System.out.println(loppu - alku + "ms");
		}else {
			System.out.println("Peliä ei voi ratkoa");
		}
	}
	
	private static boolean ratkoSudoku(int[][] peli) {
		for (int r = 0; r < peli.length; r++) {
	        for (int s = 0; s < peli.length; s++) {
	            if (peli[r][s] == 0) {
	                for (int i = 1; i <= 9; i++) {
	                    peli[r][s] = i;
	                    if (onkoMahdollinen(peli, r, s) && ratkoSudoku(peli)) {
	                        return true;
	                    }
	                    peli[r][s] = 0;
	                }
	                return false;
	            }
	        }
	    }
		for (int r = 0; r < peli.length; r++) {
	        for (int s = 0; s < peli.length; s++) {
	            System.out.print(peli[r][s] + " ");
	        }
	        System.out.println();
	    }
	    return true;
	    
	}
	
	private static boolean onkoMahdollinen(int[][] peli, int rivi, int sarake) {
	    return (riviRajoitteet(peli, rivi)
	    	      && sarakeRajoitteet(peli, sarake) 
	    	      && alueRajoitteet(peli, rivi, sarake));
	}
	
	private static boolean riviRajoitteet(int[][] peli, int rivi) {
		boolean[] rajoitteet = new boolean[peli.length];
	    return IntStream.range(0, peli.length)
	      .allMatch(sarake -> tarkistaRajoitteet(peli, rivi, sarake, rajoitteet));
	}
	
	private static boolean sarakeRajoitteet(int[][] peli, int sarake) {
		boolean[] rajoitteet = new boolean[peli.length];
	    return IntStream.range(0, peli.length)
	      .allMatch(rivi -> tarkistaRajoitteet(peli, rivi, sarake, rajoitteet));
	}
	
	private static boolean alueRajoitteet(int[][] peli, int rivi, int sarake) {
		boolean[] rajoitteet = new boolean[peli.length];
	    int alueRiviAlku = (rivi / 3) * 3;
	    int alueRiviLoppu = alueRiviAlku + 3;

	    int alueSarakeAlku = (sarake / 3) * 3;
	    int alueSarakeLoppu = alueSarakeAlku + 3;

	    for (int r = alueRiviAlku; r < alueRiviLoppu; r++) {
	        for (int s = alueSarakeAlku; s < alueSarakeLoppu; s++) {
	            if (!tarkistaRajoitteet(peli, r, s, rajoitteet)) return false;
	        }
	    }
	    return true;
	}
	
	private static boolean tarkistaRajoitteet(int[][] peli, int rivi, int sarake, boolean[] rajoitteet) {
		if (peli[rivi][sarake] != 0) {
	        if (!rajoitteet[peli[rivi][sarake] - 1]) {
	            rajoitteet[peli[rivi][sarake] - 1] = true;
	        }
	        else {
	        	return false;
	        }
	    }
	    return true;
	}

}
