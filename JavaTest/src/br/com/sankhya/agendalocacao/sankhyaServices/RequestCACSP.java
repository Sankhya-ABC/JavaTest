package br.com.sankhya.agendalocacao.sankhyaServices;

import br.com.sankhya.dwf.services.ServiceUtils;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.mgecomercial.model.centrais.cac.CACSP;
import br.com.sankhya.mgecomercial.model.centrais.cac.CACSPBean;
import br.com.sankhya.mgecomercial.model.centrais.cac.CACSPHome;
import br.com.sankhya.mgecomercial.model.facades.SelecaoDocumentoSP;
import br.com.sankhya.mgecomercial.model.facades.SelecaoDocumentoSPBean;
import br.com.sankhya.mgecomercial.model.facades.SelecaoDocumentoSPHome;
import br.com.sankhya.modelcore.MGEModelException;
import br.com.sankhya.modelcore.auth.AuthenticationInfo;
import br.com.sankhya.modelcore.comercial.BarramentoRegra;
import br.com.sankhya.modelcore.comercial.ComercialUtils;
import br.com.sankhya.modelcore.comercial.centrais.CACHelper;
import br.com.sankhya.modelcore.comercial.centrais.ImobilizadoHelper;
import br.com.sankhya.modelcore.facades.ProdutoSP;
import br.com.sankhya.modelcore.facades.ProdutoSPBean;
import br.com.sankhya.modelcore.facades.ProdutoSPHome;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;
import br.com.sankhya.modelcore.util.SPBeanUtils;
import br.com.sankhya.modulemgr.MGESession;
import br.com.sankhya.util.troubleshooting.SKError;
import br.com.sankhya.util.troubleshooting.TSLevel;
import br.com.sankhya.ws.ServiceContext;
import com.sankhya.util.MGEAcesso;
import com.sankhya.util.StringUtils;
import com.sankhya.util.XMLUtils;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.mortbay.jetty.SessionManager;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class RequestCACSP {
    ImobilizadoHelper imobilizadoHelper = new ImobilizadoHelper();
    JapeSession.SessionHandle hnd = null;

    ServiceContext sctx = new ServiceContext(null);

    public void enviaAtivo(Element request) throws Exception {

            valida();

            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            String xmlString = xmlOutputter.outputString(request);
            System.out.println("XML: " + xmlString);

            sctx.setRequestBody(request);

            final CACSP notaSP = (CACSP) ServiceUtils.getStatelessFacade(CACSPHome.JNDI_NAME, CACSPHome.class);
            notaSP.salvarInformacoesImobilizado(sctx);


    }

    public void confirmaNota(Element request) throws Exception {
        ServiceContext sctx2 = new ServiceContext(null);
        sctx2.setAutentication(AuthenticationInfo.getCurrent());
        sctx2.makeCurrent();
        SPBeanUtils.setupContext(sctx2);
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        String xmlString = xmlOutputter.outputString(request);
        System.out.println("XML: " + xmlString);
        sctx2.setRequestBody(request);
        final CACSP notaSP = (CACSP) ServiceUtils.getStatelessFacade(CACSPHome.JNDI_NAME, CACSPHome.class);
        notaSP.confirmarNota(sctx2);


    }
    public void confirmaNotaCacHelper(BigDecimal nunota, BarramentoRegra barramentoRegra) throws Exception {
        hnd = JapeSession.open();
        System.out.println("ENTROU NO TRY CACHELPER CONFIRMA2");
        try {
            CACHelper cacHelper = new CACHelper();
            cacHelper.confirmarNota(nunota, barramentoRegra, true);
            System.out.println("ENTROU NO TRY CACHELPER CONFIRMA");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JapeSession.close(hnd);
        }
    }

    public void valida() throws Exception {
        sctx.setAutentication(AuthenticationInfo.getCurrent());
        sctx.makeCurrent();
        SPBeanUtils.setupContext(sctx);
    }

    public void faturaNota(Element request) throws Exception {
        valida();
        sctx.setRequestBody(request);
        SelecaoDocumentoSP selecaoDocumentoSP = (SelecaoDocumentoSP) ServiceUtils.getStatelessFacade("mge/com/ejb/session/SelecaoDocumentoSP", SelecaoDocumentoSPHome.class);

        selecaoDocumentoSP.faturar(sctx);

    }


}
