package Main.Engine;

import java.util.Vector;

import Main.Game.Direction;
import Main.Game.Entity;
import Math.Box;
import Math.Vec2f;

public class CollisionManager {

    // Simple interval overlap on one axis
    private boolean rangesOverlap(float aMin, float aMax, float bMin, float bMax) {
        return aMax > bMin && aMin < bMax;
    }

    public boolean BoxCollision(Entity e, Box moving, Vector<CollisionBox> stationaryBoxes, int speed, Direction direction, Vec2f currentVelocity, Vec2f outVelocity)
    {
		if (stationaryBoxes.isEmpty()) 
		{
            outVelocity.x = currentVelocity.x;
            outVelocity.y = currentVelocity.y;
            e.onGround = false;
            return false;
        }

        float vx = currentVelocity.x;
        float vy = currentVelocity.y;
        boolean hitX = false;
        boolean hitY = false;

        if (vx != 0) {
            for (int i = 0; i < stationaryBoxes.size(); i++) 
            {
                Box tile = stationaryBoxes.get(i).box;
                if (!rangesOverlap(moving.Top, moving.Bottom, tile.Top, tile.Bottom)) 
                {
                    continue;
                }

                if (vx > 0) 
                {
                    if (moving.Right <= tile.Left && moving.Right + vx > tile.Left)
                    {
                    	float allowed = tile.Left - moving.Right;
                        if (allowed < vx) 
                        {
                            vx = allowed;
                            hitX = true;
                        }
                    }
                } 
                else 
                { 
                    if (moving.Left >= tile.Right && moving.Left + vx < tile.Right) 
                    {
                    	float allowed = tile.Right - moving.Left;
                        if (allowed > vx) 
                        {
                            vx = allowed;
                            hitX = true;
                        }
                    }
                }
            }
        }

        float newLeft   = moving.Left + vx;
        float newRight  = moving.Right + vx;
        float newTop    = moving.Top;
        float newBottom = moving.Bottom;

        if (vy != 0) {
            for (int i = 0; i < stationaryBoxes.size(); i++) 
            {
                Box tile = stationaryBoxes.get(i).box;

                if (!rangesOverlap(newLeft, newRight, tile.Left, tile.Right)) 
                {
                    continue;
                }

                if (vy > 0) 
                {

                    if (newBottom <= tile.Top && newBottom + vy > tile.Top) 
                    {
                        float allowed = tile.Top - newBottom;
                        if (allowed < vy) 
                        {
                            vy = allowed;
                            hitY = true;
                        }
                    }
                } 
                else 
                { 
                   
                    if (newTop >= tile.Bottom && newTop + vy < tile.Bottom) 
                    {
                        float allowed = tile.Bottom - newTop;
                        if (allowed > vy) 
                        {
                            vy = allowed;
                            hitY = true;
                        }
                    }
                }
            }
        }

        outVelocity.x = vx;
        outVelocity.y = vy;

        if (hitY && currentVelocity.y > 0) 
        {
            e.onGround = true;
        } 
        else if (!hitY) 
        {
            e.onGround = false;
        }
        return hitX || hitY;
    }
}

