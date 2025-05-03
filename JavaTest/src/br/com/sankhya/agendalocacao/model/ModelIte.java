package br.com.sankhya.agendalocacao.model;

import java.math.BigDecimal;

public class ModelIte {
    public BigDecimal getSequencia() {
        return sequencia;
    }

    public void setSequencia(BigDecimal sequencia) {
        this.sequencia = sequencia;
    }

    public BigDecimal getCodProd() {
        return codProd;
    }

    public void setCodProd(BigDecimal codProd) {
        this.codProd = codProd;
    }

    public BigDecimal getQtdNeg() {
        return qtdNeg;
    }

    public void setQtdNeg(BigDecimal qtdNeg) {
        this.qtdNeg = qtdNeg;
    }

    public BigDecimal getVlrUnit() {
        return vlrUnit;
    }

    public void setVlrUnit(BigDecimal vlrUnit) {
        this.vlrUnit = vlrUnit;
    }

    public BigDecimal getNunota() {
        return nunota;
    }

    public void setNunota(BigDecimal nunota) {
        this.nunota = nunota;
    }

    public BigDecimal getVlrTot() {
        return vlrTot;
    }

    public void setVlrTot(BigDecimal vlrTot) {
        this.vlrTot = vlrTot;
    }

    BigDecimal vlrTot;
    BigDecimal sequencia;
    BigDecimal codProd;
    BigDecimal qtdNeg;
    BigDecimal vlrUnit;
    BigDecimal nunota;
}
