package be.ucll.da.dentravak.controllers;

import be.ucll.da.dentravak.model.Sandwich;
import be.ucll.da.dentravak.model.SandwichPreferences;
import be.ucll.da.dentravak.repository.SandwichRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.naming.ServiceUnavailableException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class SandwichController {

    @Autowired
    private SandwichRepository swRepo;

    //inject is het zelfde als autowired, maar autowired is specifiek voor spring
    /*@Autowired
    private DiscoveryClient discoveryClient;*/

    @Autowired
    // RestTemplate laat toe op REST calls te maken naar andere sites.
    private RestTemplate restTemplate;

    @RequestMapping(value="/sandwiches", method = RequestMethod.GET)
    public Iterable<Sandwich> sandwich() {
        try {
            //TODO: sort allSandwiches by float in preferences
           SandwichPreferences preferences = getPreferences("1234567");
           Iterable<Sandwich> sandwiches = swRepo.findAll();
           List<Sandwich> sandwichesSorted = getSandwichSortedByRecommendations(preferences, (List<Sandwich>) sandwiches);
            return sandwichesSorted;
       } catch (Exception e) {
        return swRepo.findAll();
      }
    }

    List<Sandwich> getSandwichSortedByRecommendations(SandwichPreferences preferences, List<Sandwich> sandwiches) {
        //SandwichPreferences preferences = getPreferences("1234567");
        //List<Sandwich> sandwiches = (List<Sandwich>) swRepo.findAll();
        Collections.sort(sandwiches,(Sandwich s1, Sandwich s2) -> rating(preferences, s2).compareTo(rating(preferences, s1)) );
        return sandwiches;
    }

    private Float rating(SandwichPreferences preferences, Sandwich sandwich) {
        return preferences.getRatingForSandwich(sandwich.getId());
    }

    @RequestMapping(value="/sandwiches", method = RequestMethod.POST)
    public Sandwich postSandwich(@RequestBody Sandwich sandwich) {
        return swRepo.save(sandwich);
    }

    @RequestMapping(value="/sandwiches/{id}", method = RequestMethod.GET)
    public Sandwich getSandwich(@PathVariable UUID id) {
        return swRepo.findById(id).get();
    }

    @RequestMapping(value="/sandwiches/{id}", method = RequestMethod.PUT)
    @CrossOrigin(origins = "http://localhost:8081")
    public Sandwich putSandwich(@RequestBody Sandwich sandwich,@PathVariable UUID id ) {
        if(swRepo.findById(id).get() != null){
            return swRepo.save(sandwich);
        }else {
            return null;
        }
    }

    // why comment: for testing
    @GetMapping("/getpreferences/{emailAddress}")
    public SandwichPreferences getPreferences(@PathVariable String emailAddress) throws RestClientException, ServiceUnavailableException {
        URI service = recommendationServiceUrl()
                .map(s -> s.resolve("/recommend/" + emailAddress))
                .orElseThrow(ServiceUnavailableException::new);
        return restTemplate
                .getForEntity(service, SandwichPreferences.class)
                .getBody();
    }

    public Optional<URI> recommendationServiceUrl() {
        try {
            return Optional.of(new URI("http://localhost:8081"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


}
