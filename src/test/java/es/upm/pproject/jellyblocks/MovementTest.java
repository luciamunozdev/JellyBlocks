package es.upm.pproject.jellyblocks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import es.upm.pproject.jellyblocks.model.Movement;

class MovementTest {

	Movement movement;

	@Test
	void testMovementGetX() {
		movement = new Movement(1, 1, 1, 1, 1);
		assertEquals(1, movement.getToX());

	}

	@Test
	void testMovementGetY() {
		movement = new Movement(1, 1, 1, 1, 1);
		assertEquals(1, movement.getToY());
	}

	@Test
	void testMovementGetFromX() {
		movement = new Movement(1, 1, 1, 1, 1);
		assertEquals(1, movement.getFromX());
	}

	@Test
	void testMovementGetFromY() {
		movement = new Movement(1, 1, 1, 1, 1);
		assertEquals(1, movement.getFromY());
	}

	@Test
	void testMovementGetMovement() {
		movement = new Movement(1, 1, 1, 1, 1);
		assertEquals(1, movement.getMoveNumber());
	}

}
