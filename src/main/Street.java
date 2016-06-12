package main;

import dots.Estate;

/**
 * Created by mayezhou on 16/4/9.
 */
public class Street {
    private Estate[] estates;
    private String name;

    public Street(String name, int number, int initialLocation, Game game) {
        this.name = name;
        estates = new Estate[number];
        for (int i = 0; i < number; i++) {
            estates[i] = game.map.getEstate(initialLocation+i);
            estates[i].setStreet(this);
            estates[i].setIndex(i);
        }
    }

    public String getName() {
        return name;
    }

    public Estate[] getEstates() {
        return estates;
    }
}
