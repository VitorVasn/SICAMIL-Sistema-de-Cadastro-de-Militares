package logica;

public enum Patente {
    TC("TC"),
    MAJ("MAJ"),
    CAP("CAP"),
    _1T("1T"),
    _2T("2T"),
    ASP("ASP"),
    SO("SO"),
    _1S("1S"),
    _2S("2S"),
    _3S("3S"),
    CB("CB"),
    S1("S1"),
    S2("S2"),
    REC("REC");

    private final String valor;

    Patente(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}
