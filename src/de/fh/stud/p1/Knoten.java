package de.fh.stud.p1;

import de.fh.util.Vector2;
import de.fh.pacman.enums.PacmanTileType;

import java.util.ArrayList;
import java.util.List;

public class Knoten {
	/*
	 * TODO Praktikum 1 [1]: Erweitert diese Klasse um die notwendigen
	 * Attribute, Methoden und Konstruktoren um die möglichen verschiedenen Weltzustände darstellen und
	 * einen Suchbaum aufspannen zu können.
	 */

	public static final int NORD  = 1;
	public static final int OST  = 2;
	public static final int SUED  = 3;
	public static final int WEST  = 4;

	private Knoten Vorgaenger;
	private List<Knoten> Knoten;

	private PacmanTileType[][] Welt;
	private void SetWelt(PacmanTileType[][] welt){
		for(int i = 0; i < welt.length; i++){
			for(int j= 0; j < welt[0].length; j++){
				this.Welt[i][j] = welt[i][j];
			}
		}
	}

	public PacmanTileType[][] GetWelt(){
		return this.Welt;
	}

	private Vector2 Position;
	public Vector2 GetPosition(){
		return this.Position;
	}

	private int BewegungsRichtung;

	public Knoten(PacmanTileType[][] welt, int posX, int posY){
		this.Vorgaenger = null;
		this.Position = new Vector2(posX, posY);
		this.BewegungsRichtung = -1;

		this.Welt = new PacmanTileType[welt.length][welt[0].length];
		SetWelt(welt);
	}

	public Knoten(Knoten vorgaenger, int bewegungsRichtung){
		this.Vorgaenger = vorgaenger;

		if(bewegungsRichtung < NORD || bewegungsRichtung > WEST)
			throw new IllegalArgumentException("Keine gültige Bewegungsrichtung");
		this.BewegungsRichtung = bewegungsRichtung;

		this.Welt = new PacmanTileType[vorgaenger.GetWelt().length][vorgaenger.GetWelt()[0].length];
		SetWelt(vorgaenger.GetWelt());

		this.Position = BerechneNeuePosition(vorgaenger.Position, bewegungsRichtung);

		this.Welt[this.Position.getX()][this.Position.getY()] = PacmanTileType.EMPTY;
	}

	public List<Knoten> expand() {
		/*
		 * TODO Praktikum 1 [2]: Implementiert in dieser Methode das Expandieren des Knotens.
		 * Die Methode soll die neu erzeugten Knoten (die Kinder des Knoten) zurückgeben.
		 */
		List<Knoten> kinder = new ArrayList<Knoten>();

		for(int i = 1; i <= 4; i++){
			Vector2 neuePosition = BerechneNeuePosition(this.Position, i);
			if(this.Welt[neuePosition.getX()][neuePosition.getY()] != PacmanTileType.EMPTY
					&& this.Welt[neuePosition.getX()][neuePosition.getY()] != PacmanTileType.DOT)
				continue;

			Knoten neuerKnoten = new Knoten(this, 1);
			if(neuerKnoten != null){
				kinder.add(neuerKnoten);
			}
		}

		return kinder;
	}

	public boolean isKnotenGleich(Knoten knoten) {
		Knoten vergleichsKandidat = knoten;

		if(this.GetPosition().getX() == vergleichsKandidat.GetPosition().getX())
			if(this.GetPosition().getY() == vergleichsKandidat.GetPosition().getY())
				if (this.Welt.equals(vergleichsKandidat.Welt)){
					return true;
				}

		return false;
	}

	private Vector2 BerechneNeuePosition(Vector2 position, int bewegungsrichtung){
		Vector2 neuePosition = new Vector2(-1, -1);

		switch (bewegungsrichtung) {
			case NORD:
				neuePosition.set(position.getX(), position.getY() - 1);
				break;
			case OST:
				neuePosition.set(position.getX() + 1, position.getY());
				break;
			case SUED:
				neuePosition.set(position.getX(), position.getY() + 1);
				break;
			case WEST:
				neuePosition.set(position.getX() - 1, position.getY());
				break;

			default:
				break;
		}

		return neuePosition;
	}

	public String toString() {
		String str = "";

		str += "\n";
		str += "[" + this.Position.getX()+"," + this.Position.getY() + "]\n";
		for (int x = 0; x < Welt[0].length; x++) {
			for (int y = 0; y < Welt.length; y++) {
				str += Welt[y][x] + " ";
			}
			str += "\n";
		}

		return str;
	}
}