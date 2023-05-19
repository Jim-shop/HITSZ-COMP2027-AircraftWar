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

package net.imshit.util.music;

/**
 * 无音效
 *
 * @author Jim
 */
public class MuteMusicStrategy extends AbstractMusicStrategy {
    @Override
    public void setBgm(BgmType bgmType) {
    }

    @Override
    public void playBombExplosion() {
    }

    @Override
    public void playBullet() {
    }

    @Override
    public void playBulletHit() {
    }

    @Override
    public void playGetSupply() {
    }

    @Override
    public void playGameOver() {
    }
}
