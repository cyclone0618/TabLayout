package vip.frendy.ktablayout.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myxianwen.ngzb.fragment.FragmentCommunity
import kotlinx.android.synthetic.main.fragment_demo.*
import vip.frendy.ktablayout.R

/**
 * Test
 */
class FragmentDemo : Fragment(), View.OnClickListener {
    private var rootView: View? = null

    private val mFragments: ArrayList<Fragment> = ArrayList()
    private val mIcons = arrayListOf<CharSequence>("tab_home", "tab_user", "tab_community", "tab_home",
            "tab_community", "tab_user", "tab_community", "tab_user", "tab_community", "tab_community",
            "tab_home", "tab_community", "tab_home", "tab_community")

    companion object {
        fun getInstance(): FragmentDemo {
            val fragment = FragmentDemo()
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (rootView == null) {
            rootView = inflater?.inflate(R.layout.fragment_demo, container, false)
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
        for(icon in mIcons) {
            mFragments.add(FragmentCommunity.getInstance())
        }
    }

    private fun initView() {
        content.adapter = NewsPagerAdapter(childFragmentManager)
        tabs.setIconPadding(0, 24, 0 , 24)
        tabs.setViewPager(content)
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
        override fun getPageTitle(index: Int): CharSequence = mIcons[index]
        override fun getItemPosition(`object`: Any?): Int = PagerAdapter.POSITION_NONE
    }
}