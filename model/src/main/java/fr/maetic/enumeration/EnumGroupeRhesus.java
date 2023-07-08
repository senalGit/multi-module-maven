package fr.maetic.enumeration;

public enum EnumGroupeRhesus {

    A_PLUS("A+", EnumGroupeRhesus.PLUS),
    A_MOINS("A-", EnumGroupeRhesus.MOINS),
    B_PLUS("B+", EnumGroupeRhesus.PLUS),
    B_MOINS("B-", EnumGroupeRhesus.MOINS),
    AB_PLUS("AB+", EnumGroupeRhesus.PLUS),
    AB_MOINS("AB-", EnumGroupeRhesus.MOINS),
    O_PLUS("O+", EnumGroupeRhesus.PLUS),
    O_MOINS("O-", EnumGroupeRhesus.MOINS);

    public final String labelGroupe;
    public final String labelRhesus;
    private static final String PLUS = "Rhésus positif";
    private static final String MOINS = "Rhésus négatif";

    EnumGroupeRhesus(String labelGroupe, String labelRhesus) {
        this.labelGroupe = labelGroupe;
        this.labelRhesus = labelRhesus;
    }

    @Override
    public String toString() {
        return "GroupeRhesus{" +
                "labelGroupe='" + labelGroupe + '\'' +
                ", labelRhesus='" + labelRhesus + '\'' +
                '}';
    }

    public String getLabelGroupe() {
        return labelGroupe;
    }

    public String getLabelRhesus() {
        return labelRhesus;
    }

}
