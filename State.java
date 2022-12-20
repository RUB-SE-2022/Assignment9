import java.util.LinkedList;
import java.util.List;

public class State {
    public String name = "";
    public boolean isEndState = false;
    public List<Transition> leavingTransitions = new LinkedList<>();

    public State(String name) {
        this.name = name;
    }

    public State(String name, boolean isEndState) {
        this.name = name;
        this.isEndState = isEndState;
    }

    public State(boolean isEndState) {
        this.isEndState = isEndState;
    }

    @Override
    public String toString() {
        return name;
    }
}
