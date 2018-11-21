package vip.frendy.tablayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by frendy on 2018/11/21.
 */

public class SlidingDoubleTabLayout extends SlidingTabLayout {

    public SlidingDoubleTabLayout(Context context) {
        super(context);
    }

    public SlidingDoubleTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlidingDoubleTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getTabLayoutResId() {
        return R.layout.layout_tab_double;
    }

    @Override
    protected void addTab(int position, CharSequence title, View tabView) {
        TextView textTop = (TextView) tabView.findViewById(R.id.tv_text_top);
        TextView text = (TextView) tabView.findViewById(R.id.tv_text);
        if (textTop != null && text != null && title != null) {
            String[] titles = title.toString().split("\n");
            if (titles.length == 2 && titles[0] != null && titles[1] != null) {
                textTop.setText(titles[0]);
                text.setText(titles[1]);
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

    protected void updateTabStyles() {
        for (int i = 0; i < mTabCount; i++) {
            View v = mTabsContainer.getChildAt(i);

            TextView text = (TextView) v.findViewById(R.id.tv_text);
            if (text != null) {
                text.setTextColor(i == mCurrentTab ? mTextSelectColor : mTextUnselectColor);
                text.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextsize);
                text.setPadding((int) mTabPadding, 0, (int) mTabPadding, 0);
                if (mTextAllCaps) {
                    text.setText(text.getText().toString().toUpperCase());
                }

                if (mTextBold == TEXT_BOLD_BOTH) {
                    text.getPaint().setFakeBoldText(true);
                } else if (mTextBold == TEXT_BOLD_NONE) {
                    text.getPaint().setFakeBoldText(false);
                }
            }

            TextView textTop = (TextView) v.findViewById(R.id.tv_text_top);
            if (textTop != null) {
                textTop.setTextColor(i == mCurrentTab ? mTextSelectColor : mTextUnselectColor);
                textTop.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTopTextSize);
                textTop.setPadding((int) mTabPadding, 0, (int) mTabPadding, 0);
                if (mTextAllCaps) {
                    textTop.setText(textTop.getText().toString().toUpperCase());
                }

                if (mTextBold == TEXT_BOLD_BOTH) {
                    textTop.getPaint().setFakeBoldText(true);
                } else if (mTextBold == TEXT_BOLD_NONE) {
                    textTop.getPaint().setFakeBoldText(false);
                }
            }
        }
    }
}
