package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Implements the MovementStrategy interface 
 * which defines movement behavior for boss plane.
 */
public class BossMovementStrategy implements MovementStrategy {

    private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
    private static final int ZERO = 0;
    private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
    private static final int VERTICAL_VELOCITY = 8;

    private final List<Integer> movePattern;
    private int consecutiveMovesInSameDirection;
    private int indexOfCurrentMove;
    /**
     * Constructs BossMovementStrategy and initialises boss plane move pattern.
     */
    public BossMovementStrategy() {
        movePattern = new ArrayList<>();
        initializeMovePattern();
        consecutiveMovesInSameDirection = 0;
        indexOfCurrentMove = 0;
    }
    /**
     * Moves boss plane according to move pattern defined.
     * The movement is constrained for keeping boss plane 
     * within the vertical boundary of game screen.
     *
     * @param plane: FighterPlane object which represents boss plane.
     */
    @Override
    public void move(FighterPlane plane) {
        double initialTranslateY = plane.getTranslateY();
        int nextMove = getNextMove();
        plane.setTranslateY(initialTranslateY + nextMove);

        double currentPosition = plane.getLayoutY() + plane.getTranslateY();
        if (currentPosition < 50 || currentPosition > 620) {
            plane.setTranslateY(initialTranslateY);
        }
    }
    /**
     * Initialises boss move pattern 
     * with combination of upwards, downwards, and stationary movements.
     * It is then shuffled to introduce randomness.
     */
    private void initializeMovePattern() {
        for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
            movePattern.add(VERTICAL_VELOCITY);
            movePattern.add(-VERTICAL_VELOCITY);
            movePattern.add(ZERO);
        }
        Collections.shuffle(movePattern);
    }
    /**
     * Retrieves next movement from move pattern defined.
     * If boss has maintained the same movement for maximum frames allowed,
     * move pattern will be shuffled to change direction of movement.
     *
     * @return next vertical movement value from move pattern defined.
     */
    private int getNextMove() {
        int currentMove = movePattern.get(indexOfCurrentMove);
        consecutiveMovesInSameDirection++;
        if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
            Collections.shuffle(movePattern);
            consecutiveMovesInSameDirection = 0;
            indexOfCurrentMove++;
        }
        if (indexOfCurrentMove == movePattern.size()) {
            indexOfCurrentMove = 0;
        }
        return currentMove;
    }
}
