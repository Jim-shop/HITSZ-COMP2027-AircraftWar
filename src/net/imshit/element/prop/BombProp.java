package net.imshit.element.prop;

import net.imshit.element.aircraft.hero.HeroAircraft;

/**
 * @author Jim
 */
public class BombProp extends AbstractProp {

    public BombProp(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    @Override
    public void use(HeroAircraft hero) {
        System.out.println("FireSupply active!");
    }
}