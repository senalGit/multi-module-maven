package fr.maetic.enumeration;
public enum EnumContraception {

    PATCH_CONTRACEPTIF("libelleContraception"),
    DIU("Dispositif Intra-Utérin"),
    IMPLANT("Implant contraceptif"),
    ANNEAU("Anneau vaginal"),
    DIAPHRAGME("Diaphragme et cape cervicale"),
    PRESERVATIF("Préservatif féminin"),
    PROGESTATIF("Progestatifs injectables"),
    SPERMICIDE("libelleContraception");

    private final String libelleContraception;

    EnumContraception(String libelleContraception) {
        this.libelleContraception = libelleContraception;
    }

    public String getLibelleContraception() {
        return libelleContraception;
    }

}
