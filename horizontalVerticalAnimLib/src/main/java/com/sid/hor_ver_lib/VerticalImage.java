package com.sid.hor_ver_lib;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

public class VerticalImage {
    private float arrowRadius;
    private float arrowX;
    private float arrowY;
    private float arrowSpeedY;
    private RectF arrowBounds;
    private Drawable d;
    private float arrowAngleX;

    public VerticalImage(float arrowX, float arrowY, float radius, int arrowSpeedY, int arrowAngleX, Drawable d){
        this.arrowX=arrowX;
        this.arrowY=arrowY;
        this.arrowRadius=radius;
        this.arrowSpeedY=arrowSpeedY;
        this.arrowAngleX = arrowAngleX;
        this.arrowBounds=new RectF();
        this.d = d;
    }

    public void update(int xMin,int xMax, int yMin, int yMax){
        this.arrowY+=this.arrowSpeedY;
        this.arrowX+=this.arrowAngleX;
        if(this.arrowY+this.arrowRadius>yMax){
            this.arrowY=yMin+this.arrowRadius;
        }
        else
        if(this.arrowY-this.arrowRadius<yMin){
            this.arrowSpeedY= -this.arrowSpeedY;
            this.arrowY = yMin+this.arrowRadius;
        }
        if(this.arrowX+this.arrowRadius > xMax){
            this.arrowX=xMin+this.arrowRadius;
        }
        else
        if(this.arrowX-this.arrowRadius<xMin){
            this.arrowAngleX=-this.arrowAngleX;
            this.arrowX = xMin+this.arrowRadius;
        }
    }
    public void draw(Canvas canvas){
        this.arrowBounds.set(this.arrowX-this.arrowRadius,this.arrowY-this.arrowRadius,this.arrowX+this.arrowRadius,this.arrowY+this.arrowRadius);
        d.setBounds((int)(this.arrowX-this.arrowRadius),(int)(this.arrowY-this.arrowRadius),(int)(this.arrowX+this.arrowRadius),(int)(this.arrowY+this.arrowRadius));
        d.draw(canvas);
    }
}

