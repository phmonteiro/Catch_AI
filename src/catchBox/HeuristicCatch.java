package catchBox;

import agentSearch.Heuristic;


public class HeuristicCatch extends Heuristic<CatchProblemSearch, CatchState> {

    @Override
    public double compute(CatchState state) {
        //TODO
        return state.computeHeuristic(problem.getGoalPosition());
    }

    @Override
    public String toString() {
        //TODO

        return "";
    }
}