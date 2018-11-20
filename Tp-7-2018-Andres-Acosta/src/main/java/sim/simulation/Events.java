package main.java.sim.simulation;

public enum Events {
    INICIO("Inicio"),
    INICIO_DEL_DIA("Inicio del dia."),
    LLEGADA_PERSONA("Llegada de persona."),
    FIN_TIRADA("Fin de tirada."),
    FIN_LIMPIEZA("Fin de limpieza de alfombra."),
    FIN_INTERRUPTION("Fin de interrupcion.");

    private final String text;

    Events(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

