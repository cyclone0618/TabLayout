package vip.frendy.tablayout;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SlidingIconTextTabLayout extends SlidingTabLayout {

    private int mIconPaddingLeft = 0;
    private int mIconPaddingTop = 0;
    private int mIconPaddingRight = 0;
    private int mIconPaddingBottom = 0;

    private List<Integer> mIconResIds = new ArrayList<>();

    public SlidingIconTextTabLayout(Context context) {
        super(context);
    }

    public SlidingIconTextTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlidingIconTextTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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
        return R.layout.layout_tab_icon_text;
    }

    public void setIconResIds(@DrawableRes int... resIds) {
        mIconResIds.clear();
        for (int resId : resIds) {
            mIconResIds.add(resId);
        }
    }

    @Override
    protected void updateTabStyles() {
        for (int i = 0; i < mTabCount; i++) {
            View v = mTabsContainer.getChildAt(i);
            TextView tvTitle = (TextView) v.findViewById(R.id.tv_tab_title);
            ImageView ivIcon = (ImageView) v.findViewById(R.id.iv_tab_icon);
            if (tvTitle != null && ivIcon != null) {
                boolean isSelect = i == mCurrentTab;
                tvTitle.setTextColor(isSelect? mTextSelectColor : mTextUnselectColor);
                tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextsize);
                tvTitle.setPadding((int)mTabPadding, 0, (int)mTabPadding, 0);
                if (mTextAllCaps) {
                    tvTitle.setText(tvTitle.getText().toString().toUpperCase());
                }

                if (mTextBold == TEXT_BOLD_BOTH) {
                    tvTitle.getPaint().setFakeBoldText(true);
                } else if (mTextBold == TEXT_BOLD_NONE) {
                    tvTitle.getPaint().setFakeBoldText(false);
                }

                // 如果图片控件有资源
                if (i < mIconResIds.size()) {
                    tvTitle.setVisibility(isSelect ? INVISIBLE : VISIBLE);
                    ivIcon.setVisibility(isSelect ? VISIBLE : INVISIBLE);
                }
            }
        }
    }

    @Override
    protected void updateTabSelection(int position) {
        Log.i("TabLayout", "pos = " + position);
        for (int i = 0; i < mTabCount; ++i) {
            View tabView = mTabsContainer.getChildAt(i);
            final boolean isSelect = i == position;
            TextView tabTitle = (TextView) tabView.findViewById(R.id.tv_tab_title);
            ImageView ivTabIcon = (ImageView) tabView.findViewById(R.id.iv_tab_icon);
            if (tabTitle != null && ivTabIcon != null) {
                tabTitle.setTextColor(isSelect ? mTextSelectColor : mTextUnselectColor);
                if (mTextBold == TEXT_BOLD_WHEN_SELECT) {
                    tabTitle.getPaint().setFakeBoldText(isSelect);
                }

                // 如果图片控件有资源
                if (i < mIconResIds.size()) {
                    tabTitle.setVisibility(isSelect ? INVISIBLE : VISIBLE);
                    ivTabIcon.setVisibility(isSelect ? VISIBLE : INVISIBLE);
                }
            }
        }
    }

    @Override
    protected void addTab(int position, CharSequence title, View tabView) {
        TextView tvTabTitle = (TextView) tabView.findViewById(R.id.tv_tab_title);
        ImageView ivTabIcon = (ImageView) tabView.findViewById(R.id.iv_tab_icon);
        if (tvTabTitle != null) {
            tvTabTitle.setText(title);
        }
        if (position < mIconResIds.size()) {
            ivTabIcon.setImageResource(mIconResIds.get(position));
            ivTabIcon.setPadding(mIconPaddingLeft, mIconPaddingTop, mIconPaddingRight, mIconPaddingBottom);
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

        // 每一个Tab的布局参数
        LinearLayout.LayoutParams lpTab = mTabSpaceEqual ?
                new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f) :
                new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        if (mTabWidth > 0) {
            lpTab = new LinearLayout.LayoutParams((int) mTabWidth, LayoutParams.MATCH_PARENT);
        }

        mTabsContainer.addView(tabView, position, lpTab);
    }
}
