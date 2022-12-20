import java.util.Collections;
import java.util.List;

public interface Fsm {
    Fsm addState(String stateName, boolean isEndState);
    Fsm addTransition(String event, String actionOutput, State target);
    Fsm addTransition(String input, String target);
}
