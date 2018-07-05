package vip.frendy.ktablayout

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.myxianwen.ngzb.fragment.FragmentCommunity
import com.myxianwen.ngzb.fragment.FragmentMain
import kotlinx.android.synthetic.main.activity_main.*
import vip.frendy.tablayout.entity.TabEntity
import vip.frendy.tablayout.listener.CustomTabEntity
import java.util.*

/**
 * Created by iiMedia on 2017/8/25.
 */
class MainActivity: AppCompatActivity() {
    private val mFragments: ArrayList<Fragment> = ArrayList()

    private val mTitles: ArrayList<String> = arrayListOf(
            "首页", "社区", "我的")
    private val mIconUnselectIds = intArrayOf(
            R.drawable.tab_home_pre, R.drawable.tab_community_pre, R.drawable.tab_user_pre)
    private val mIconSelectIds = intArrayOf(
            R.drawable.tab_home, R.drawable.tab_community, R.drawable.tab_user)
    private val mTabEntities = ArrayList<CustomTabEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mFragments.add(FragmentMain.getInstance())
        mFragments.add(FragmentCommunity.getInstance())
        mFragments.add(FragmentCommunity.getInstance())

        for (i in mTitles.indices) {
            mTabEntities.add(TabEntity(this, mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]))
        }
        tabs.setTabData(mTabEntities, this, R.id.content, mFragments)
        tabs.showMsg(1,"new")
    }
}