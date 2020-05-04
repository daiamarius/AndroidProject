package com.example.statusapp.mvvc;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.statusapp.R;
import com.example.statusapp.db.model.ServiceEntity;
import com.example.statusapp.db.model.ServiceWithTags;

import java.util.ArrayList;
import java.util.List;

public class StatisticsView extends View {

    private List<ServiceEntity> services;

    public StatisticsView(Context context) {
        super(context);
        services = null;
        init(null);
    }

    public StatisticsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public StatisticsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public StatisticsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }

    private void init(@Nullable AttributeSet set){

    }

    public void setServices(List<ServiceEntity> services){
        this.services = services;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if( services == null)
                return;

        int passing=0;
        int failing=0;
        int warning=0;
        for(ServiceEntity e : services){
            passing+=e.getPassing();
            failing+=e.getFailing();
            warning+=e.getWarning();
        }

        passing=3;
        warning=1;
        failing=2;
        int sum = passing+failing+warning;
        int start = 0;
        float passangle = 360*passing/sum;
        float warnangle = 360*warning/sum;
        float failangle = 360*failing/sum;
        Paint paint_fail  = new Paint();
        Paint paint_pass  = new Paint();
        Paint paint_warn  = new Paint();

        Paint text_fail = new Paint();
        text_fail.setColor(getResources().getColor(R.color.darkblue));
        text_fail.setTextSize(50);
        text_fail.setTextAlign(Paint.Align.CENTER);

        Paint text_pass = new Paint();
        text_pass.setColor(getResources().getColor(R.color.darkblue));
        text_pass.setTextSize(50);
        text_pass.setTextAlign(Paint.Align.CENTER);

        Paint text_warn = new Paint();
        text_warn.setColor(getResources().getColor(R.color.darkblue));
        text_warn.setTextSize(50);
        text_warn.setTextAlign(Paint.Align.CENTER);

        Path path_pass= new Path();
        Path path_fail= new Path();
        Path path_warn= new Path();



        paint_pass.setColor(getResources().getColor(R.color.lightgreen));
        canvas.drawArc(250,100,750,600,start,passangle,true,paint_pass);
        path_pass.addArc(230,80,770,620,start,passangle);
        canvas.drawTextOnPath(String.valueOf(passing),path_pass,0,0,text_pass);


        start+=passangle;
        paint_fail.setColor(getResources().getColor(R.color.darkred));
        canvas.drawArc(250,100,750,600,start,failangle,true,paint_fail);
        path_fail.addArc(230,80,770,620,start,failangle);
        canvas.drawTextOnPath(String.valueOf(failing),path_fail,0,0,text_fail);

        start+=failangle;
        paint_warn.setColor(getResources().getColor(R.color.yellow));
        canvas.drawArc(250,100,750,600,start,warnangle,true,paint_warn);
        path_warn.addArc(230,80,770,620,start,warnangle);
        canvas.drawTextOnPath(String.valueOf(warning),path_warn,0,0,text_warn);

    }
}
