package vip.frendy.tablayout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by frendy on 2018/7/26.
 */

public class SlidingIconTabLayout extends SlidingTabLayout {

    private int mIconPaddingLeft = 0;
    private int mIconPaddingTop = 0;
    private int mIconPaddingRight = 0;
    private int mIconPaddingBottom = 0;

    public SlidingIconTabLayout(Context context) {
        super(context);
    }

    public SlidingIconTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlidingIconTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setIconPadding(int left, int top, int right, int bottom) {
        mIconPaddingLeft = left;
        mIconPaddingTop = top;
        mIconPaddingRight = right;
        mIconPaddingBottom = bottom;
    }

    @Override
    public int getTabLayoutResId() {
        return R.layout.layout_tab_icon;
    }

    @Override
    protected void addTab(int position, CharSequence title, View tabView) {
        ImageView iv_tab_icon = (ImageView) tabView.findViewById(R.id.iv_tab_icon);
        if (iv_tab_icon != null) {
            //反射获取drawableId
            try {
                int drawableId = getDrawableId(getContext(), String.valueOf(title));
                Drawable d = getResources().getDrawable(drawableId);
                iv_tab_icon.setImageDrawable(d);
                iv_tab_icon.setPadding(mIconPaddingLeft, mIconPaddingTop, mIconPaddingRight, mIconPaddingBottom);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        tabView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mTabsContainer.indexOfChild(v);
                if (position != -1) {
                    if (mViewPager.getCurrentItem() != position) {
                        if (mSnapOnTabClick) {
                            mViewPager.setCurrentItem(position, false);
                        } else {
                            mViewPager.setCurrentItem(position);
                        }

                        if (mListener != null) {
                            mListener.onTabSelect(position);
                        }
                    } else {
                        if (mListener != null) {
                            mListener.onTabReselect(position);
                        }
                    }
                }
            }
        });

        /** 每一个Tab的布局参数 */
        LinearLayout.LayoutParams lp_tab = mTabSpaceEqual ?
                new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f) :
                new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        if (mTabWidth > 0) {
            lp_tab = new LinearLayout.LayoutParams((int) mTabWidth, LayoutParams.MATCH_PARENT);
        }

        mTabsContainer.addView(tabView, position, lp_tab);
    }

    public static int getDrawableId(Context paramContext, String paramString) {
        return paramContext.getResources().getIdentifier(paramString,
                "drawable", paramContext.getPackageName());
    }
}
