package ca.utoronto.utm.othello.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A user can play the following, improved strategy:
 * Choose a corner if available,
 * otherwise choose a side if available, otherwise choose a space that
 * maximizes the players presence in the middle 4 by 4 square of the board,
 * otherwise choose the location maximizing the movers presence on the board.
 */
public class PlayerAdvanced extends Player {
    public PlayerAdvanced(Othello othello, char player) {
        super(othello, player);
    }

    @Override
    public Move getMove() {
        Othello othelloCopy = othello.copy();
        Move bestMove = new Move(0, 0);
        List<Move> avMoves = new ArrayList<>();
        for (int row = 0; row < Othello.DIMENSION; row++) {
            for (int col = 0; col < Othello.DIMENSION; col++) {
                othelloCopy = othello.copy();
                if (othelloCopy.move(row, col)) {
                    avMoves.add(new Move(row, col));
                }
            }
        }
        if (!(avMoves.isEmpty())) {
            for (Move x : avMoves) {
                if (isCorner(x)) {
                    return x;
                }
            }
            for (Move x : avMoves) {
                if (isSide(x)) {
                    return x;
                }
            }
            othelloCopy = othello.copy();
            int count = getCount4(this.othello, this.player);
            char original_player = this.player;
            Move curr = new Move(0, 0);
            int condition = -1;
            for (Move x : avMoves) {
                othelloCopy.move(x.getRow(), x.getCol());
                int new_count = getCount4(othelloCopy, original_player);
                if (new_count > count) {
                    condition = -1;
                    count = new_count;
                    curr = x;
                }
                if (new_count == count) {
                    condition = count;
                }
                othelloCopy = othello.copy();
            }
            if (condition != -1) {
                PlayerGreedy p = new PlayerGreedy(othello, player);
                Move k = p.getMove();
                return k;

            }
            return curr;
            }
        return new Move(0,0);

        }



    private boolean isCorner(Move x) {
        int row = x.getRow();
        int col = x.getCol();
        return (row == 0 && col == 0) || (row == 0 && col == 7) || (row == 7 && col == 0) || (row == 7 && col == 7);
    }

    private boolean isSide(Move x) {
        int row = x.getRow();
        int col = x.getCol();
        return (row == 0 || col == 0 || row == 7 || col == 7);

    }

    private int getCount4(Othello othello, char player) {
        int count = 0;
        if (othello.getToken(3, 3) == player) {
            count++;
        }
        if (othello.getToken(3, 4) == player) {
            count++;
        }
        if (othello.getToken(4, 3) == player) {
            count++;
        }
        if (othello.getToken(4, 4) == player) {
            count++;
        }
        return count;
    }

}


