package com.youtube.contactos.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by henryyerrybravosanchez on 3/13/15.
 */
public class CheackAboutRelativeLayout extends RelativeLayout implements Checkable {

    private boolean isCheck;
    private List<Checkable> checkables;

    public CheackAboutRelativeLayout(Context context) {
        super(context);
        inicializar(null);
    }

    public CheackAboutRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        inicializar(attrs);
    }

    public CheackAboutRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inicializar(attrs);
    }

    private void inicializar(AttributeSet attrs) {
        this.isCheck=false;
        this.checkables= new ArrayList<Checkable>();
    }

    //<editor-fold desc="METODOS CHECKEABLES">
    @Override
    public void setChecked(boolean checked) {
        this.isCheck=checked;
        for(Checkable c :checkables){
            c.setChecked(isChecked());
        }

    }

    @Override
    public boolean isChecked() {
        return isCheck;
    }

    @Override
    public void toggle() {
        this.isCheck=!this.isCheck;
        for(Checkable c :checkables){
            c.toggle();
        }
    }
    //</editor-fold>

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        final int childCount= this.getChildCount();
        for (int i=0;i<childCount;i++){
            buscarComponentesCheck(this.getChildAt(i));
        }
    }

    private void buscarComponentesCheck(View view) {
        if(view instanceof Checkable){
            this.checkables.add((Checkable) view);
        }
        if(view instanceof ViewGroup){
            final ViewGroup viewGroup=(ViewGroup)view;
            final int childCount=viewGroup.getChildCount();
            for(int i=0;i<childCount;i++){
                buscarComponentesCheck(viewGroup.getChildAt(i));
            }
        }
    }
}
