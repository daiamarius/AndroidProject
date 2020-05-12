package com.example.statusapp.mvvc;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.statusapp.R;
import com.example.statusapp.db.model.ServiceEntity;

import java.util.List;

public class StatisticsColumnView extends View {
    private List<ServiceEntity> services;

    public StatisticsColumnView(Context context) {
        super(context);
        services = null;
        init(null);
    }

    public StatisticsColumnView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public StatisticsColumnView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public StatisticsColumnView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
        canvas.drawColor(getResources().getColor(R.color.fadegrey));
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

        int max = 0;
        if (passing>max)
            max = passing;
        if (failing>max)
            max = failing;
        if (warning > max)
            max = warning;
        float perUnit = 300/max;

        Paint paint_fail = new Paint();
        Paint paint_pass = new Paint();
        Paint paint_warn = new Paint();
        paint_pass.setColor(getResources().getColor(R.color.lightgreen));
        paint_fail.setColor(getResources().getColor(R.color.darkred));
        paint_warn.setColor(getResources().getColor(R.color.yellow));

        int colwidth = 250;
        int startP=150;
        int bottom=300;
        int topbottom=290;

        Paint paint_text = new Paint();
        paint_text.setColor(getResources().getColor(R.color.darkblue));
        paint_text.setTextSize(50);

        canvas.drawRect(startP,topbottom-(passing*perUnit),startP+colwidth,bottom,paint_pass);
        canvas.drawText(String.valueOf(passing), startP+colwidth/2-10, 360 ,paint_text);
        startP += colwidth+10;
        canvas.drawRect(startP,topbottom-(warning*perUnit),startP+colwidth,bottom,paint_warn);
        canvas.drawText(String.valueOf(warning), startP+colwidth/2-10, 360 ,paint_text);
        startP += colwidth+10;
        canvas.drawRect(startP,topbottom-(failing*perUnit),startP+colwidth,bottom,paint_fail);
        canvas.drawText(String.valueOf(failing), startP+colwidth/2-10, 360 ,paint_text);



    }
}
