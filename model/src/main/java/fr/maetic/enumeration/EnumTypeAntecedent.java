package fr.maetic.enumeration;

public enum EnumTypeAntecedent {
    FAMILIAL("antecedent familial"),
    MEDICAL("antecedent médical"),
    CHIRURGICAL("antecedent chirurgical"),
    OBSTETRICAL("antecedent obstétrical"),
    PERSONNEL("antecedent personnel");

    public final String labelAntecedent;

    EnumTypeAntecedent(String labelAntecedent) {
        this.labelAntecedent = labelAntecedent;
    }

    public String getLabelAntecedent() {
        return labelAntecedent;
    }

    @Override
    public String toString() {
        return "TypeAntecedentEnum{" + "labelAntecedent='" + labelAntecedent + '\'' + '}';
    }

}
