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

        UUID sandwichId1 = UUID.randomUUID();
        UUID sandwichId2 = UUID.randomUUID();
        UUID sandwichId3 = UUID.randomUUID();

        SandwichPreferences preferences = new SandwichPreferences();
        preferences.put(sandwichId1, 1.0f);
        preferences.put(sandwichId2, 5.0f);
        preferences.put(sandwichId3, 3.0f);

        Sandwich sandwich1 = aSandwich().withId(sandwichId1).withName("sandwich 1").build();
        Sandwich sandwich2 = aSandwich().withId(sandwichId2).withName("sandwich 2").build();
        Sandwich sandwich3 = aSandwich().withId(sandwichId3).withName("sandwich 3").build();

        List<Sandwich> sandwiches = Arrays.asList(sandwich1, sandwich2, sandwich3);

        sandwichController.getSandwichSortedByRecommendations(preferences, sandwiches);

        assertThat(sandwiches)
                .containsExactly(sandwich2, sandwich3, sandwich1);
    }
}
