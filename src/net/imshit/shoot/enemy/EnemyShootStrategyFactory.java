package net.imshit.shoot.enemy;

import net.imshit.shoot.ShootStrategy;
import net.imshit.shoot.ShootStrategyFactory;

/**
 * 敌机策略工厂
 *
 * @author Jim
 */
public class EnemyShootStrategyFactory extends ShootStrategyFactory {
    @Override
    public ShootStrategy getStrategy(int shootNum) {
        return switch (shootNum) {
            case 0 -> new EnemyNoShootStrategy();
            case 1 -> new EnemyDirectShootStrategy();
            case 3 -> new EnemyScatterShootStrategy();
            default -> new EnemyNoShootStrategy();
        };
    }
}
