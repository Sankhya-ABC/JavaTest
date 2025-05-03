package br.com.sankhya.agendalocacao.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ModelCab {
    BigDecimal codTipoper;
    BigDecimal codParc;
    BigDecimal codEmp;
    BigDecimal codNat;
    BigDecimal codCencus;
    BigDecimal nunota;
    BigDecimal codVend;
    BigDecimal codTipVenda;
    Timestamp dtNeg;
    Timestamp dtAlter;
    BigDecimal numContrato;

    public BigDecimal getCodTipoper() {
        return codTipoper;
    }

    public void setCodTipoper(BigDecimal codTipoper) {
        this.codTipoper = codTipoper;
    }

    public BigDecimal getCodParc() {
        return codParc;
    }

    public void setCodParc(BigDecimal codParc) {
        this.codParc = codParc;
    }

    public BigDecimal getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(BigDecimal codEmp) {
        this.codEmp = codEmp;
    }

    public BigDecimal getCodNat() {
        return codNat;
    }

    public void setCodNat(BigDecimal codNat) {
        this.codNat = codNat;
    }

    public BigDecimal getCodCencus() {
        return codCencus;
    }

    public void setCodCencus(BigDecimal codCencus) {
        this.codCencus = codCencus;
    }

    public BigDecimal getNunota() {
        return nunota;
    }

    public void setNunota(BigDecimal nunota) {
        this.nunota = nunota;
    }

    public BigDecimal getCodVend() {
        return codVend;
    }

    public void setCodVend(BigDecimal codVend) {
        this.codVend = codVend;
    }

    public BigDecimal getCodTipVenda() {
        return codTipVenda;
    }

    public void setCodTipVenda(BigDecimal codTipVenda) {
        this.codTipVenda = codTipVenda;
    }

    public Timestamp getDtNeg() {
        return dtNeg;
    }

    public void setDtNeg(Timestamp dtNeg) {
        this.dtNeg = dtNeg;
    }

    public Timestamp getDtAlter() {
        return dtAlter;
    }

    public void setDtAlter(Timestamp dtAlter) {
        this.dtAlter = dtAlter;
    }

    public BigDecimal getNumContrato() {
        return numContrato;
    }

    public void setNumContrato(BigDecimal numContrato) {
        this.numContrato = numContrato;
    }
}
