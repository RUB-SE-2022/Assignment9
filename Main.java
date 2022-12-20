import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Erstelle Zustände
        State state1 = new State("State1", false);
        State state2 = new State("State2", false);
        State state3 = new State("State3", true);

        // Erstelle eine State Machine
        StateMachine fsm = new StateMachine("StateMachine", List.of(state1, state2, state3), state1);
        StateMachine fsm2 = new StateMachine("StateMachine", List.of(state1, state2, state3),state2);

        // Erstelle Übergänge
        fsm.addTransition("Event1", "State2");
        fsm.addTransition("State1", "Going Back", state1);
        fsm.addTransition("Event2", "Okay Boomer", state3);

        fsm2.addTransition("Event1", "State2");
        fsm2.addTransition("State1", "Going Back", state1);
        fsm2.addTransition("Event2", "Okay Boomer", state3);

        // Erstelle einen Interpreter für die State Machine
        Interpreter interpreter = new InterpreterImpl(fsm);

        // Führe die State Machine im interaktiven Modus aus
        interpreter.runInteractive();
    }
}




