package fr.maetic.enumeration;

public enum EnumTypeSuivi {
    GROSSESSE("suivi grossess"),
    GYNECOLOGIE("suivi gynecologique"),
    REEDUCATION("rééducation"),
    AUTRE_SUIVI("autre suivi");

    public final String labelSuivi;

    EnumTypeSuivi(String labelSuivi) {
        this.labelSuivi = labelSuivi;
    }

    public String getLabelSuivi() {
        return labelSuivi;
    }

    @Override
    public String toString() {
        return "TypeSuivi{" +
                "labelSuivi='" + labelSuivi + '\'' +
                '}';
    }
}
