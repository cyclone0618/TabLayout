package com.myxianwen.ngzb.fragment

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_icon_text.*
import vip.frendy.ktablayout.R

/**
 * Test
 */
class FragmentIconText : Fragment(), View.OnClickListener {
    private var rootView: View? = null

    private val mFragments: ArrayList<Fragment> = ArrayList()
    private val tab = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml("呵呵呵<small>呵呵呵</small>", Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml("呵呵呵<small>呵呵呵</small>")
    }
    private val mTitles = arrayListOf<CharSequence>("测", "哈哈", tab, "嘿嘿嘿嘿", "哦哦哦哦", "嗯嗯嗯")

    companion object {
        fun getInstance(): FragmentIconText {
            val fragment = FragmentIconText()
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater?.inflate(R.layout.fragment_icon_text, container, false)
            initData()
        }
        // 缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误
        (rootView?.parent as? ViewGroup)?.removeView(rootView)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initData() {
        for(title in mTitles) {
            mFragments.add(FragmentCommunity.getInstance())
        }
    }

    private fun initView() {
        content.adapter = NewsPagerAdapter(childFragmentManager)
        tabs.setIconResIds(R.drawable.tab_home, R.drawable.tab_community, R.drawable.tab_user)
        tabs.setIconPadding(0, 24, 0 , 24)
        tabs.setViewPager(content)
        content.currentItem = 0
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {

        } else {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onClick(view: View) {

    }

    inner class NewsPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int = mFragments.size
        override fun getItem(index: Int): Fragment = mFragments[index]
        override fun getPageTitle(index: Int): CharSequence = mTitles[index]
        override fun getItemPosition(`object`: Any?): Int = PagerAdapter.POSITION_NONE
    }
}
