package br.com.sankhya.agendalocacao.model;

import br.com.sankhya.modelcore.comercial.BarramentoRegra;

import java.math.BigDecimal;

public class CabRetorno {

    private BigDecimal nunota;
    private BarramentoRegra barramentoRegra;

    public BigDecimal getNunota() {
        return nunota;
    }

    public void setNunota(BigDecimal nunota) {
        this.nunota = nunota;
    }

    public BarramentoRegra getBarramentoRegra() {
        return barramentoRegra;
    }

    public void setBarramentoRegra(BarramentoRegra barramentoRegra) {
        this.barramentoRegra = barramentoRegra;
    }
}
