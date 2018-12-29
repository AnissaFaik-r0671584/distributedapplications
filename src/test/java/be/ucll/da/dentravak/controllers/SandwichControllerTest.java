package be.ucll.da.dentravak.controllers;

import be.ucll.da.dentravak.model.Sandwich;
import be.ucll.da.dentravak.model.SandwichPreferences;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static be.ucll.da.dentravak.model.Sandwich.SandwichBuilder.aSandwich;
import static org.assertj.core.api.Assertions.assertThat;

public class SandwichControllerTest {

    @Test
    public void sortingBasedOnPreferences_Works() {
        SandwichController sandwichController = new SandwichController();

        Sandwich sandwich1 = aSandwich().withId(UUID.randomUUID()).withName("sandwich 1").build();
        Sandwich sandwich2 = aSandwich().withId(UUID.randomUUID()).withName("sandwich 2").build();
        Sandwich sandwich3 = aSandwich().withId(UUID.randomUUID()).withName("sandwich 3").build();

        SandwichPreferences preferences = new SandwichPreferences();
        preferences.put(sandwich1.getId(), 1.0f);
        preferences.put(sandwich2.getId(), 5.0f);
        preferences.put(sandwich3.getId(), 3.0f);

        List<Sandwich> sandwiches = Arrays.asList(sandwich1, sandwich2, sandwich3);
        sandwichController.getSandwichSortedByRecommendations(preferences, sandwiches);
        assertThat(sandwiches)
                .containsExactly(sandwich2, sandwich3, sandwich1);
    }
}
