package net.imshit.element.prop.factory;

import net.imshit.element.prop.AbstractProp;

/**
 * @author Jim
 */
abstract public class AbstractPropFactory {
    protected final float speedX = 0;
    protected final float speedY = 0.1f;

    /**
     * 道具工厂创造道具
     *
     * @param locationX 道具掉落水平位置
     * @param locationY 道具掉落垂直位置
     * @return 道具实例
     */
    abstract public AbstractProp createProp(float locationX, float locationY);
}
