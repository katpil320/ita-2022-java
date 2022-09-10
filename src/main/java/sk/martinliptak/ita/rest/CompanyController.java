package sk.martinliptak.ita.rest;

import cz.ares.response.VypisRZP;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.martinliptak.ita.ws.AresClient;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final AresClient aresClient;

    @GetMapping("{vatNumber}")
    public VypisRZP getByIco(@PathVariable("vatNumber") String vatNumber) {
        return aresClient.getCompanyInfo(vatNumber);
    }
}
