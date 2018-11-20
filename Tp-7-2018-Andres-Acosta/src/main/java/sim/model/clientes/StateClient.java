package main.java.sim.model.clientes;

public enum StateClient {

    EN_COLA("En cola de alfombra"),
    EN_ALFOMBRA("En alfombra magica"),
    TERMINADO("Terminado");

    private final String text;

    StateClient(final String text){ this.text = text;}

    @Override
    public String toString() {
        return  text ;
    }
}
