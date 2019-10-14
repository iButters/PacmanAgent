package de.fh.stud.p1;

import de.fh.pacman.enums.PacmanTileType;
import de.fh.util.Vector2;

import java.util.List;

public class BaumTest {

	public static void main(String[] args) {
		//Anfangszustand nach Aufgabe
		PacmanTileType[][] view = {
				{PacmanTileType.WALL,PacmanTileType.WALL,PacmanTileType.WALL,PacmanTileType.WALL},
				{PacmanTileType.WALL,PacmanTileType.EMPTY,PacmanTileType.DOT,PacmanTileType.WALL},
				{PacmanTileType.WALL,PacmanTileType.DOT,PacmanTileType.WALL,PacmanTileType.WALL},
				{PacmanTileType.WALL,PacmanTileType.WALL,PacmanTileType.WALL,PacmanTileType.WALL}
		};
		//Startposition des Pacman
		int posX = 1, posY = 1;
		/*
		 * TODO Praktikum 1 [3]: Baut hier basierend auf dem gegebenen 
		 * Anfangszustand (siehe view, posX und posY) den Suchbaum auf.
		 */
		Knoten knoten = new Knoten(view, posX, posY);
		List<Knoten> kinder = knoten.expand();

		for(int i = 0; i < kinder.size() - 1; i++){
			System.out.print(kinder.get(i).toString());
		}
	}
}
