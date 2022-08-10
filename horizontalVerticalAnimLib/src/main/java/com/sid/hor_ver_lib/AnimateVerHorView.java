package com.sid.hor_ver_lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class AnimateVerHorView extends View  {
    int[] arr,brr ;
    int[] arr1,brr1;
    private int xMin = -500,yMin = -500;
    private int xMax,yMax;
    private ArrayList<HorizontalImage> horizontalArrows;
    private ArrayList<VerticalImage> verticalArrows;
    private int minArrow,maxArrow,maxPlay,minPlay;
    private int minArrowSize,maxArrowSize;
    Context context;
    Drawable hDrawable,vDrawable;
    int  horizontalDrawable,verticalDrawable;
    TypedArray typedArray;
    DisplayMetrics metrics = new DisplayMetrics();
    int transparencyLow,transparencyHigh;
    int width,height;
    int minSpeed,maxSpeed;
    int verticalAngle, horizontalAngle;

    public AnimateVerHorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initialize(attrs);
        horizontalArrows = new ArrayList<>();
        verticalArrows = new ArrayList<>();
        int minHeight = height/10 ;
        int minWidth = width/10;
        arr = new int[] { minWidth,   minWidth*9, minWidth*7, minWidth*6, minWidth*3, minWidth*3, minWidth*6, minWidth*8, minWidth,   minWidth*9, minWidth*7, minWidth*4, minWidth*5, minWidth*2, minWidth*6, minWidth*8, minWidth*8, minWidth*2,minWidth*3};
        arr1 = new int[] {minHeight*1,minHeight*2,minHeight/2,minHeight*2,minHeight/3,minHeight*3,minHeight*4,minHeight*4,minHeight*5,minHeight*7,minHeight*5,minHeight*2,minHeight*7,minHeight*6,minHeight*8,minHeight*8,minHeight*7,minHeight*9,minHeight*7+(minHeight/2)};

        brr = new int[]  {minWidth*2,minWidth*8,minWidth,minWidth*4+(minWidth/2), minWidth*7,   minWidth*3,               minWidth*2,               minWidth*5,minWidth*8+(minWidth/2),minWidth+(minWidth/2),minWidth,minWidth*7,minWidth*4,minWidth*5};
        brr1 = new int[] {minHeight,minHeight,minHeight*2,minHeight*3,minHeight*3+(minHeight/2),minHeight*5-(minHeight/2),minHeight*3+(minHeight/2),minHeight*6,minHeight*6,           minHeight*8,           minHeight*10,minHeight*9,minHeight/2,minHeight*9+(minHeight/2)};
        for (int i = 0; i <arr.length ; i++) {
            vDrawable=null;
            verticalArrows.add(this.addVArrow(i));
        }
        for (int i = 0; i < brr.length; i++) {
            hDrawable=null;
            horizontalArrows.add(this.addHArrow(i));
        }
    }

    private void initialize(AttributeSet attrs) {
        metrics = context.getResources().getDisplayMetrics();
        width = metrics.widthPixels;
        height = metrics.heightPixels;

        if (attrs != null) {
            typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.HorizontalVerticalAnimateView, 0, 0);
            try {
                transparencyLow = typedArray.getInt(R.styleable.HorizontalVerticalAnimateView_transparencyHighLimit, 50);
                transparencyLow = typedArray.getInt(R.styleable.HorizontalVerticalAnimateView_transparencyLowLimit, 80);
                minSpeed=typedArray.getInt(R.styleable.HorizontalVerticalAnimateView_minSpeed, 2);
                maxSpeed=typedArray.getInt(R.styleable.HorizontalVerticalAnimateView_maxSpeed, 2);
                minArrow = typedArray.getInt(R.styleable.HorizontalVerticalAnimateView_minArrow, 12);
                maxArrow = typedArray.getInt(R.styleable.HorizontalVerticalAnimateView_maxArrow, 20);
                minPlay = typedArray.getInt(R.styleable.HorizontalVerticalAnimateView_minPlay, 12);
                maxPlay = typedArray.getInt(R.styleable.HorizontalVerticalAnimateView_maxPlay, 20);
                minArrowSize = typedArray.getInt(R.styleable.HorizontalVerticalAnimateView_minSize, 10);
                maxArrowSize = typedArray.getInt(R.styleable.HorizontalVerticalAnimateView_maxSize, 25);
                horizontalDrawable = typedArray.getResourceId(R.styleable.HorizontalVerticalAnimateView_horizontalDrawable,R.drawable.ic_play);
                verticalDrawable = typedArray.getResourceId(R.styleable.HorizontalVerticalAnimateView_verticalDrawable,R.drawable.ic_download);
                verticalAngle = typedArray.getInt(R.styleable.HorizontalVerticalAnimateView_verticalAngle, 0);
                horizontalAngle = typedArray.getInt(R.styleable.HorizontalVerticalAnimateView_horizontalAngle, 0);
            } finally {
                typedArray.recycle();
            }
        }
    }

    public HorizontalImage addHArrow(int i) {
        HorizontalImage horizontalArrow;
        hDrawable = ContextCompat.getDrawable(context,horizontalDrawable);
        hDrawable.setAlpha(transparencyLow + (int)(Math.random() *transparencyHigh));
        int velX = minSpeed ;
        int size = (minArrowSize+5 )+ (int) (Math.random() * maxArrowSize);

        horizontalArrow = new HorizontalImage(brr[i],brr1[i], size, velX, horizontalAngle, hDrawable);
        return horizontalArrow;
    }

    public VerticalImage addVArrow(int i) {
        VerticalImage verticalArrow;
        vDrawable = ContextCompat.getDrawable(context,verticalDrawable);
        vDrawable.setAlpha(transparencyLow + (int)(Math.random() *transparencyHigh));
        int velY = minSpeed;
        int size = minArrowSize + (int) (Math.random() * maxArrowSize);
        verticalArrow = new VerticalImage(arr[i],arr1[i], size, velY, verticalAngle, vDrawable);
        return verticalArrow;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0; i < horizontalArrows.size(); i++) {
            horizontalArrows.get(i).draw(canvas);
        }
        for (int i = 0; i < horizontalArrows.size(); i++)
            horizontalArrows.get(i).update(xMin, xMax,yMin,yMax);

        for (int i = 0; i < verticalArrows.size(); i++) {
            verticalArrows.get(i).draw(canvas);
        }
        for (int i = 0; i < verticalArrows.size(); i++)
            verticalArrows.get(i).update(xMin, xMax,yMin,yMax);

//        try {
//            Thread.sleep(5);
//        } catch (InterruptedException e) {
//        }
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        this.xMax = w + 80;
        this.yMax = h + 80;
    }

    public AnimateVerHorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AnimateVerHorView(Context context) {
        super(context);
    }

}
