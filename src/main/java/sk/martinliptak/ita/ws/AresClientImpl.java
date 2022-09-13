package sk.martinliptak.ita.ws;

import cz.ares.request.AresDotazTyp;
import cz.ares.request.AresDotazy;
import cz.ares.request.Dotaz;
import cz.ares.response.AresOdpovedi;
import cz.ares.response.VypisRZP;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class AresClientImpl extends WebServiceGatewaySupport implements AresClient {
    @Override
    public VypisRZP getCompanyInfo(String vatNumber) {
        final AresDotazy aresDotazy = new AresDotazy();
        aresDotazy.setDotazPocet(1);
        aresDotazy.setDotazTyp(AresDotazTyp.VYPIS_RZP);

        final Dotaz dotaz = new Dotaz();
        dotaz.setICO(vatNumber);
        dotaz.setPomocneID(1);
        aresDotazy.getDotaz().add(dotaz);

        final AresOdpovedi response = (AresOdpovedi) getWebServiceTemplate()
                .marshalSendAndReceive(aresDotazy);

        return response.getOdpoved().get(0).getVypisRZP().get(0);
    }
}
