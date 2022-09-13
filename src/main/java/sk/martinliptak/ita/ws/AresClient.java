package sk.martinliptak.ita.ws;

import cz.ares.response.VypisRZP;

public interface AresClient {
    VypisRZP getCompanyInfo(String ico);
}
