package utn.frc.sim.model.servers;

public enum ServerState {
    OCP("Ocupado"),
    LBR("Libre"),
    OUT("Limpiando");

    private final String text;

    ServerState(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
