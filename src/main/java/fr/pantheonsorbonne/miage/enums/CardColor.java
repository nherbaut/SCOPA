package fr.pantheonsorbonne.miage.enums;

/**
 * List the possible colors of a card
 */
public enum CardColor {
    DENIER(127137), //D
    EPEE(127137 + 16), //E
    BATON(127137 + 16 * 2), //B
    COUPE(127137 + 16 * 3); //C

    private final int code;

    CardColor(int code) {
        this.code = code;
    }

    public static CardColor valueOfStr(String substring) {
        for (CardColor color : CardColor.values()) {
            if (color.name().substring(0, 1).equals(substring)) {
                return color;
            }
        }
        throw new RuntimeException("No Such Color");
    }

    public int getCode() {
        return code;
    }

    public String getStringRepresentation() {
        return "" + this.name().charAt(0);
    }
}