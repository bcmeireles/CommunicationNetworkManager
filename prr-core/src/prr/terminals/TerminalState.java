package prr.terminals;
/** https://web.tecnico.ulisboa.pt/~david.matos/w/pt/index.php/State_Pattern_(padr%C3%A3o_de_desenho)/Exerc%C3%ADcio_02:_M%C3%A1quina_de_Lavar */
abstract class TerminalState {
    /** Terminal */
    protected Terminal _terminal;

    /** @param terminal is the terminal */
    public TerminalState(Terminal _terminal) { _terminal = terminal; }
}