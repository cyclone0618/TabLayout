package vip.frendy.tablayout.entity;

import android.content.Context;
import android.graphics.drawable.Drawable;

import vip.frendy.tablayout.listener.CustomTabEntity;

public class TabEntity implements CustomTabEntity {
    private Context context;
    public String title;
    public int selectedIcon;
    public int unSelectedIcon;

    public TabEntity(Context context, String title, int selectedIcon, int unSelectedIcon) {
        this.context = context;
        this.title = title;
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public Drawable getTabSelectedIcon() {
        return context.getResources().getDrawable(selectedIcon);
    }

    @Override
    public Drawable getTabUnselectedIcon() {
        return context.getResources().getDrawable(unSelectedIcon);
    }
}
