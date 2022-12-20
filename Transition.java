import java.util.Optional;
import java.util.function.BooleanSupplier;

public class Transition {
    public String event;
    public Optional<String> actionOutput = Optional.empty();
    public State target;
    public BooleanSupplier condition = () -> true;

    public Transition(String event, String actionOutput, State target) {
        this.event = event;
        this.actionOutput = Optional.of(actionOutput);
        this.target = target;
    }

    public Transition(String event, String actionOutput, BooleanSupplier condition, State target) {
        this.event = event;
        this.actionOutput = Optional.of(actionOutput);
        this.target = target;
        this.condition = condition;
    }

    public Transition(String event, State target) {
        this.event = event;
        this.target = target;
    }
}
