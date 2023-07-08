package fr.maetic.enumeration;

public enum EnumSpecialite {
    GROSSESSE("suivi grossesse"),
    GYNECOLOGIE("suivi gynecologique"),
    REEDUCATION("rééducation"),
    AUTRE_SUIVI("autre suivi");

    public final String labelSpecialite;

    EnumSpecialite(String labelSpecialite) {
        this.labelSpecialite = labelSpecialite;
    }

    public String getLabelSpecialite() {
        return labelSpecialite;
    }



    @Override
    public String toString() {
        return "Specialite{ " +
                "labelSpecialite='" + labelSpecialite + '\'' +
                '}';
    }


}
