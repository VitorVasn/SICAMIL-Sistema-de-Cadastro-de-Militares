package logica;

public enum Situacao {
    EF("EF"),//Efetivo
    PSF("PSF"),//Prestando Serviço Fora
    PSV("PSV");//Prestando Serviço para OM

    private final String valor;

    Situacao(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}
