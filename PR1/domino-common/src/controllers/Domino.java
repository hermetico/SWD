package controllers;

import models.Catalog;
import models.Movement;
import models.Piece;
import models.PlayedPile;

public class Domino {
	
	private final String CATALOG = "0001020304050611121314151622232425263334" +
			"3536444546555666";

	private final char LEFT = 'L';
	private final char RIGHT = 'R';
	
	
	private Catalog catalog;
	private PlayedPile playedPile;
	
	
	public Domino(){
		catalog = new Catalog();
		fillCatalog(); // llenamos el catalogo de fichas
	};
	
	
	private void fillCatalog(){
		for(int i = 0; i < CATALOG.length(); i += 2){
			char left = CATALOG.charAt(i);
			char right = CATALOG.charAt(i+1);
			catalog.addPiece(new Piece(left, right));
		}
	}
	

	/** BOOLEANS **/
	
	/**
	 * Return if is a valid Domino piece.
	 * Valid domino piece has to be present within the current catalog
	 * @param piece
	 * @return
	 */
	public boolean isValidPiece(Piece piece){
		return catalog.hasPiece(piece);
		
	}
	
	/**
	 * Returns if a side id valid
	 * Valid side can be LEFT or RIGHT
	 * @param side
	 * @return
	 */
	public boolean isValidSide(char side){
		side = Character.toUpperCase(side);
		return side == LEFT || side == RIGHT;
		
	}
	
	/**
	 * Returns if a movement is valid within a domini context
	 * First of all, it checks if it has a valid piece, then it checks
	 * if movement has a valid side, then it checks if the piece isn't currently
	 * in the played pile and eventually checks if the movement matches with the
	 * current played pile
	 * @param movement
	 * @return
	 */
	public boolean isValidMovement(Movement movement){
		Piece movementPiece = movement.getPiece();
		return isValidPiece(movementPiece)
				&& isValidSide(movement.getSide())
				&& !playedPile.hasPiece(movementPiece)
				&& playedPile.matchMovement(movement);
	}

}