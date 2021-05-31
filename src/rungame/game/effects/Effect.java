package rungame.game.effects;

import rungame.framework.Engine;
import rungame.framework.utils.Counter;

public abstract class Effect {
    public static final int SPEED_UP_PLAYER_EFFECT = 0;
    public static final int SPEED_DOWN_MONSTER_EFFECT = 1;

    public static final int DURATION = (int)(5.0d * (1000.0d / Engine.TICK));
    private boolean trigger;
    protected Counter effectCounter;

    public Effect() {
        trigger = false;
        effectCounter = new Counter(DURATION);
    }

    public void toggle() {
        if (this.trigger) {
            this.trigger = false;
        } else {
            this.trigger = true;
        }

        effectCounter.reset();
    }

    public void trigger() {
        this.trigger = true;
        effectCounter.reset();
    }

    public void check() {
        if (!trigger) {
            return;
        }

        if (!effectCounter.count()) {
            launch();
        } else {
            toggle();
            reset();
        }
    }

    public abstract void launch();
    public abstract void reset();

    public boolean isTrigger() {
        return trigger;
    }
}