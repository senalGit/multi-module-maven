package fr.maetic.enumeration;


public enum EnumTypeAnalyseMedicale {
    TRISOMIE21("Trisomie 21", null, null, false),
    TOXOPLASMOSE("Sérologie Toxoplasmose", null, null, false),
    HEPATITE_B("Hépatite B", null, null, false),
    RAI("recherchedes agglutinines irrégulières", null, null, false),
    SYPHILIS("syphilis", null, null, false),
    VIH("VIH", null, null, false),
    RUBEOLE("Rubéole", null, null, false),
    GLYCEMIE("Glycémie à jeun", null, null, false),
    HEPATITE_C("Hépatite C", null, null, false),
    FROTIS_VAGINAL("frottis Cervico Vaginal", null, null, false),
    STREPTOCOQUE_B("fDépistage du streptocoque B", null, null, false),
    BETA_HCG("Test de grossesse", null, null, false),
    HEMOGLOBINE("Hémoglobine", null, null, false),
    PLAQUETTE("Plaquettes", null, null, false),
    ALBUMINE("Albumine Glycosurie, Albuminurie ou Protéinurie", null, null, false);
    private String libelleAnalyse;
    private Double minimal;
    private Double maximal;
    private boolean positive;

    EnumTypeAnalyseMedicale(String libelleAnalyse, Double minimal, Double maximal, boolean positive) {
        this.libelleAnalyse = libelleAnalyse;
        this.minimal = minimal;
        this.maximal = maximal;
        this.positive = positive;
    }

    public String getLibelleAnalyse() {
        return libelleAnalyse;
    }

    public void setLibelleAnalyse(String libelleAnalyse) {
        this.libelleAnalyse = libelleAnalyse;
    }

    public Double getMinimal() {
        return minimal;
    }

    public void setMinimal(Double minimal) {
        this.minimal = minimal;
    }

    public Double getMaximal() {
        return maximal;
    }

    public void setMaximal(Double maximal) {
        this.maximal = maximal;
    }

    public boolean isPositive() {
        return positive;
    }

    public void setPositive(boolean positive) {
        this.positive = positive;
    }


}
