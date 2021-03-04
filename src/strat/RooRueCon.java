package strat;

public class RooRueCon extends Strategy {
    @Override
    protected int generateMove() {
        int rounds = info.getRoundsPlayed();
        if (rounds == 0) {
            return 0;
        } else if (rounds > (150 * (0.63))) {
            return 1;
        }
        int[] oppMoves = info.getOppMoves();
        int oppMove = oppMoves[info.getRoundsPlayed() - 1];
        return oppMove;
    }

    @Override
    protected String getName() {
        return "RooRue";
    }

    @Override
    protected String getAuthor() {
        return "Laik";
    }

    @Override
    public void reinit() {

    }
}
