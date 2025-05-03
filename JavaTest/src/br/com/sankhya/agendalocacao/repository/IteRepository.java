package br.com.sankhya.agendalocacao.repository;

import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.util.JapeSessionContext;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.vo.PrePersistEntityState;
import br.com.sankhya.modelcore.auth.AuthenticationInfo;
import br.com.sankhya.modelcore.comercial.centrais.CACHelper;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;
import br.com.sankhya.ws.ServiceContext;

import br.com.sankhya.agendalocacao.model.ModelIte;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class IteRepository {
    public AuthenticationInfo authInfo;
    JapeSession.SessionHandle hnd;
    CACHelper cacHelper = new CACHelper();
    private ServiceContext sctx;

    public void insertIte(ModelIte ite, BigDecimal nunota) throws Exception {
        validacoes();
        hnd = JapeSession.open();

        try {
            EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();
            Collection<PrePersistEntityState> itensNota = new ArrayList<>();
            DynamicVO itemVO = (DynamicVO) dwfFacade.getDefaultValueObjectInstance("ItemNota");
            itemVO.setProperty("SEQUENCIA", ite.getSequencia());
            itemVO.setProperty("CODPROD", ite.getCodProd());
            itemVO.setProperty("QTDNEG", ite.getQtdNeg());
            itemVO.setProperty("VLRUNIT", ite.getVlrUnit());
            itemVO.setProperty("VLRUNITLOC", ite.getVlrUnit());
            PrePersistEntityState itePreState = PrePersistEntityState.build(dwfFacade, "ItemNota", itemVO);
            itensNota.add(itePreState);
            cacHelper.incluirAlterarItem(nunota, authInfo, itensNota, true);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JapeSession.close(hnd);
        }

    }

    public ModelIte montaModelIte(BigDecimal codprod) {
        ModelIte ite = new ModelIte();
        ite.setCodProd(codprod);
        ite.setQtdNeg(BigDecimal.ONE);
        ite.setSequencia(BigDecimal.ONE);
        ite.setVlrUnit(new BigDecimal(120));
        return ite;
    }


    private void validacoes() {
        try {
            this.sctx = new ServiceContext(null);
            this.authInfo = new AuthenticationInfo("SUP", BigDecimal.ZERO, BigDecimal.ZERO, new Integer(2147483647));
            this.sctx.setAutentication(this.authInfo);
            this.sctx.putHttpSessionAttribute("usuario_logado", this.authInfo.getUserID());
            JapeSessionContext.putProperty("usuario_logado", this.authInfo.getUserID());
            this.sctx.setAttribute("usuario_logado", this.authInfo.getUserID());
            this.sctx.makeCurrent();
        } catch (Exception e) {
            System.out.println("ERRO NA VALIDADE USUARIO");
        }
    }

}
