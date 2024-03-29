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

import net.imshit.util.resource.AudioManager;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 有音效
 *
 * @author Jim
 */
public class BasicMusicStrategy extends AbstractMusicStrategy {

    private final ExecutorService pool = new ThreadPoolExecutor(32, 64, 10, TimeUnit.SECONDS, new SynchronousQueue<>(), new BasicThreadFactory.Builder().namingPattern("music-thread-%d").daemon(true).build());
    private AudioThread bgmThread;

    @Override
    public void setBgm(BgmType bgmType) {
        if (bgmThread != null) {
            bgmThread.interrupt();
        }
        switch (bgmType) {
            case NONE -> bgmThread = null;
            case NORMAL -> {
                bgmThread = new AudioThread(AudioManager.BGM, true);
                pool.execute(bgmThread);
            }
            case BOSS -> {
                bgmThread = new AudioThread(AudioManager.BGM_BOSS, true);
                pool.execute(bgmThread);
            }
            default -> { // 已经列全了，不可能执行到这里
            }
        }
    }

    @Override
    public void playBombExplosion() {
        pool.execute(new AudioThread(AudioManager.BOMB_EXPLOSION, false));
    }

    @Override
    public void playBullet() {
        pool.execute(new AudioThread(AudioManager.BULLET, false));
    }

    @Override
    public void playBulletHit() {
        pool.execute(new AudioThread(AudioManager.BULLET_HIT, false));
    }

    @Override
    public void playGetSupply() {
        pool.execute(new AudioThread(AudioManager.GET_SUPPLY, false));
    }

    @Override
    public void playGameOver() {
        if (bgmThread != null) {
            bgmThread.interrupt();
        }
        pool.execute(new AudioThread(AudioManager.GAME_OVER, false));
    }
}
