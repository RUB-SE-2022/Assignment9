import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InterpreterImpl implements Interpreter{
    private StateMachine stateMachine;
    private State currentState;

    public InterpreterImpl(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
        currentState = stateMachine.initial;
    }

    @Override
    public List<String> run(List<String> events) {
        var r = new LinkedList<String>();
        for (String i : events) {
            var t = matchingTransition(i);
            if (t.isPresent()) {
                currentState = t.get().target;
                if (t.get().actionOutput.isPresent())
                    r.add(t.get().actionOutput.get());
            } else
                break; // Falsche Antwort, EngeGelände
            if (currentState.isEndState) // Auch EndeGelände
                break;
        }
        return r;
    }

    @Override
    public void runInteractive() {
        printCurrentStateAndPossibleEvents();
        var sc = new Scanner(System.in);
        while (sc.hasNext()) {
            var t = matchingTransition(sc.next());
            if (t.isPresent()) {
                currentState = t.get().target;
                var o = t.get().actionOutput;
                if (o.isPresent())
                    System.out.println(o.get());
            } else
                System.out.println("Falscher Event Input.");
            if (currentState.isEndState)
                return;
            printCurrentStateAndPossibleEvents();
        }
    }

    private Optional<Transition> matchingTransition(String event) {
        var matchingtransition = currentState.leavingTransitions.stream().
                filter(x -> x.event.equals(event)).
                filter(x -> x.condition.getAsBoolean()).
                findFirst();
        if (matchingtransition.isPresent())
            return Optional.of(matchingtransition.get());
        else
            return Optional.empty();
    }

    @Override
    public void printCurrentStateAndPossibleEvents() {
        System.out.println("Aktueller State: " + currentState);
        var pe = currentState.leavingTransitions.stream().
                map(x -> x.event).
                distinct(). // Weil gleiche Events mit different conditions
                        collect(Collectors.joining(", "));
                        System.out.println("Mögliche Events: " + pe);
    }
}

