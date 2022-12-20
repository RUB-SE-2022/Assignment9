import java.util.Collections;
import java.util.List;

public class StateMachine implements Fsm{
    public String name;
    public List<State> states = Collections.emptyList();
    public State initial;

    public StateMachine(String name, List<State> states, State initial) {
        this.name = name;
        this.states = states;
        this.initial = initial;
    }

    @Override
    public Fsm addState(String stateName, boolean isEndState) {
        State newState = new State(stateName, isEndState);
        states.add(newState);
        return this;
    }

    @Override
    public Fsm addTransition(String event, String actionOutput, State target) {
        Transition transition = new Transition(event, actionOutput, target);
        initial.leavingTransitions.add(transition);
        return this;
    }

    @Override
    public Fsm addTransition(String input, String target) {
        // find the target state based on its name
        State targetState = states.stream()
                .filter(s -> s.name.equals(target))
                .findFirst()
                .orElse(null);

        if (targetState == null) {
            throw new IllegalArgumentException("Target state nicht gefunden: " + target);
        }

        Transition transition = new Transition(input, targetState);
        initial.leavingTransitions.add(transition);
        return this;
    }
}
