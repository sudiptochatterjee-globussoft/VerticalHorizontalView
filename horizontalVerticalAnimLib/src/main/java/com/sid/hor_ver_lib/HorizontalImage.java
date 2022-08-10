package com.sid.hor_ver_lib;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

public class HorizontalImage {
    private float arrowRadius;
    private float arrowX;
    private float arrowY;
    private float arrowSpeedX;
    private RectF arrowBounds;
    private Drawable d;
    private float arrowAngleY;

    public HorizontalImage(float arrowX, float arrowY, float radius, int arrowSpeedX, int arrowAngleY, Drawable d){
        this.arrowX=arrowX;
        this.arrowY=arrowY;
        this.arrowRadius=radius;
        this.arrowSpeedX=arrowSpeedX;
        this.arrowAngleY = arrowAngleY;
        this.arrowBounds=new RectF();
        this.d = d;
    }

    public void update(int xMin,int xMax, int yMin, int yMax){
        this.arrowX+=this.arrowSpeedX;
        this.arrowY+=this.arrowAngleY;
        if(this.arrowX+this.arrowRadius > xMax){
            this.arrowX=xMin+this.arrowRadius;
        }
        else
        if(this.arrowX-this.arrowRadius<xMin){
            this.arrowSpeedX=-this.arrowSpeedX;
            this.arrowX = xMin+this.arrowRadius;
        }
        if(this.arrowY+this.arrowRadius>yMax){
            this.arrowY=yMin+this.arrowRadius;
        }
        else
        if(this.arrowY-this.arrowRadius<yMin){
            this.arrowAngleY= -this.arrowAngleY;
            this.arrowY = yMin+this.arrowRadius;
        }
    }

    public void draw(Canvas canvas){
        this.arrowBounds.set(this.arrowX-this.arrowRadius,this.arrowY-this.arrowRadius,this.arrowX+this.arrowRadius,this.arrowY+this.arrowRadius);
        d.setBounds((int)(this.arrowX-this.arrowRadius),(int)(this.arrowY-this.arrowRadius),(int)(this.arrowX+this.arrowRadius),(int)(this.arrowY+this.arrowRadius));
        d.draw(canvas);
    }
}
