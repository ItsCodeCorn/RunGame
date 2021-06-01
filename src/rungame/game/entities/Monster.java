package rungame.game.entities;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import rungame.framework.Engine;
import rungame.framework.resources.Resources;
import rungame.framework.utils.Counter;
import rungame.game.entities.tracking.MonsterTrackStrategy;

public class Monster extends Entity {
    private Player player;
    private MonsterTrackStrategy originalTrackStrategy;
    private MonsterTrackStrategy trackStrategy;

    public Monster(int x, int y, MonsterTrackStrategy trackStrategy) {
        super(Resources.MONSTER, 'M', new Rectangle(x, y, 25, 25));

        moveTimeCounter = new Counter(Engine.MONSTER_MOVE_TIME);

        player = Engine.getPlayer();

        this.originalTrackStrategy = trackStrategy;
        this.trackStrategy = trackStrategy;

        hasCollision = new LinkedList<>();
        hasCollision.addFirst('*');
        hasCollision.addFirst('M');
        hasCollision.addFirst('U');
        hasCollision.addFirst('D');
        hasCollision.addFirst('E');
        hasCollision.addFirst('S');
    }

    @Override
    public void action() {
        if (!moveTimeCounter.count()) {
            return;
        }

        trackStrategy.track(this);

        if (player.getLocation().equals(getLocation())) {
            Engine.getPlayingState().setGameOver(true);
        }
    }

    public void setTrackStrategy(MonsterTrackStrategy trackStrategy) {
        this.trackStrategy = trackStrategy;
    }
    public void resetTrackStrategy() {
        this.trackStrategy = this.originalTrackStrategy;
    }
    public LinkedList<Character> getHasCollision() {
        return hasCollision;
    }
    public Player getPlayer() {
        return player;
    }
}
