package de.vit.typen;

public class Information {
	/**
	 * Klasse, die ein paar Startinformationen aufsammeln soll
	 * wahrscheinlich temporär
	 *
	 * @author Alexander Schmidt
	 * @param args
	 */
	private int playerId;
	private int level;

	public Information(int playerId, int level) {
		this.playerId = playerId;
		this.level = level;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
