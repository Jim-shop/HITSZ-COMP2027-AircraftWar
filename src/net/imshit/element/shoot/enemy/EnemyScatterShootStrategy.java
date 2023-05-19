/*
 * Copyright (c) 2023 Jim-shop
 * AircraftWar is licensed under Mulan PubL v2.
 * You can use this software according to the terms and conditions of the Mulan PubL v2.
 * You may obtain a copy of Mulan PubL v2 at:
 *          http://license.coscl.org.cn/MulanPubL-2.0
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PubL v2 for more details.
 */

package net.imshit.element.shoot.enemy;

import net.imshit.element.bullet.EnemyBullet;

import java.util.LinkedList;
import java.util.List;

/**
 * 敌机散射策略
 *
 * @author Jim
 */
public class EnemyScatterShootStrategy implements EnemyShootStrategy {
    @Override
    public List<EnemyBullet> shoot(float x, float y, float speedY, int power) {
        List<EnemyBullet> res = new LinkedList<>();
        int direction = 1;
        int shootNum = 3;
        float bulletY = y + direction * 2;
        float bulletCenterSpeedX = 0;
        float bulletCenterSpeedY = speedY + direction * 0.1f;
        for (int i = 0; i < shootNum; i++) {
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            res.add(new EnemyBullet(x + (i * 2 - shootNum + 1) * 10, bulletY, bulletCenterSpeedX + (i * 2 - shootNum + 1) * 0.01f, bulletCenterSpeedY, power));
        }
        return res;
    }
}
