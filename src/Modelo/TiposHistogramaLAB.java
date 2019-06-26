package Modelo;

public enum TiposHistogramaLAB {
    L, A, B, H, V;
    
    @Override
    public String toString() {
        switch(this) {
            case L: return "L";
            case A: return "A";
            case B: return "B";
            case H: return "H";
            case V: return "V";
        }
        return null;
    }
}
