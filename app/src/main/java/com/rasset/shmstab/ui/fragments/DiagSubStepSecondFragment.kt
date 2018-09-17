package com.rasset.shmstab.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rasset.shmstab.R
import com.rasset.shmstab.network.res.BaseModel
import com.rasset.shmstab.utils.Logger
import com.rasset.shmstab.utils.dpToPx
import kotlinx.android.synthetic.main.fragment_diag_step_second.*
import java.util.HashMap

/**
 * Created by devok on 2018-09-05.
 */

class DiagSubStepSecondFragment : BaseFragment() {

    private object Holder { val INSTANCE = DiagSubStepSecondFragment() }

    companion object {
        val singleTone: DiagSubStepSecondFragment by lazy { Holder.INSTANCE }

        val instance: DiagSubStepSecondFragment by lazy { DiagSubStepSecondFragment() }

        fun newInstance(context: Context): Intent {
            val intent = Intent(context, DiagSubStepSecondFragment::class.java)
            return intent
        }
    }

    lateinit var tabLayout: TabLayout
    var fragments: HashMap<Int, Fragment> = HashMap()

    private var previousVPposition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_diag_step_second, container, false)
        }
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isFirstInit){
            isFirstInit = true
            initFirst()
        }
    }

    override fun onStop() {
        super.onStop()
    }

    fun initFirst(){

        setupTablayout()
        setupMainViewPager()

    }

    private fun setupTablayout() {

        val digCategorise = resources.getStringArray(R.array.diag_categorys)

        tabLayout = TBL_TAB_MENUS

        tabLayout.removeAllTabs()
        tabLayout.setSelectedTabIndicatorColor(resources.getColor(R.color.main_text_primary))
        tabLayout.setTabTextColors(resources.getColor(R.color.main_text_secondary), resources.getColor(R.color.main_text_primary))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        // 선택 위원에 따른 탭구성 TODO
        var idx = 0
        for (title:String in digCategorise){
            if (idx < 2){
                tabLayout.addTab(tabLayout.newTab().setText(title))
            } else {
                break
            }
            idx++
        }
        for (i in 0 until tabLayout.tabCount) {
            val tab = (tabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(0, 0, dpToPx(mContext, 50).toInt(), 0)
            tab.requestLayout()
        }
    }

    private fun setupMainViewPager() {
        if (tabLayout == null) return

        //        coordLayout = (CoordinatorLayout) mRootView.findViewById(R.id.coordinatorLayout);
        //        VP_DIAG_SURVEYS = (GestureViewPager) mRootView.findViewById(R.id.VP_MAIN_CHAT_LIST);
        fragmentManager?.apply {
            val adapter = MainFragmentPagerAdapter(this, tabLayout.tabCount)
            VP_DIAG_SURVEYS.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
            VP_DIAG_SURVEYS.adapter = adapter
            VP_DIAG_SURVEYS.setSwipeEnabled(false)
            tabLayout.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    if (VP_DIAG_SURVEYS == null) return
                    VP_DIAG_SURVEYS.setCurrentItem(tab.position,false)
                    if (previousVPposition != tab.position)
                        previousVPposition = tab.position

                    if (fragments == null) return
                    if (fragments.get(tab.position) is DiagSurveyAssetSellFragment) {
                        //                    ((ChatListSuggestFragment) fragments.get(tab.getPosition())).scrolltoLittleBitListView();
                    } else if (fragments.get(tab.position) is DiagSurveyAssetBuyFragment) {
                        //                    ((ChatListRelationFragment) fragments.get(tab.getPosition())).scrolltoLittleBitListView();
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {

                }

                override fun onTabReselected(tab: TabLayout.Tab) {
                    if (fragments == null) return
                    if (previousVPposition == tab.position) {
                        if (fragments.get(tab.position) is DiagSurveyAssetSellFragment) {
                            //                        ((ChatListSuggestFragment) fragments.get(tab.getPosition())).smoothScrolltoTopListView();
                        } else if (fragments.get(tab.position) is DiagSurveyAssetBuyFragment) {
                            //                        ((ChatListRelationFragment) fragments.get(tab.getPosition())).smoothScrolltoTopListView();
                        }
                    }
                }
            })

        }

    }



    override fun onNetSuccess(data: BaseModel?, nReqType: Int) {

    }

    override fun onNetFail(retCode: Int, strErrorMsg: String, nReqType: Int) {
        super.onNetFail(retCode, strErrorMsg, nReqType)
    }

    override fun onProgresStart(nReqType: Int) {

    }

    override fun onProgresStop(nReqType: Int) {

    }


    inner class MainFragmentPagerAdapter(fragmentManager: FragmentManager, tabItemCount: Int) : FragmentStatePagerAdapter(fragmentManager) {
        private var NUM_PAGE_ITEMS = 0

        init {
            NUM_PAGE_ITEMS = tabItemCount
        }

        // Returns total number of pages
        override fun getCount(): Int {
            return NUM_PAGE_ITEMS
        }

        // Returns the fragment to display for that page
        // Adapter내부 인스턴스가 필요한경우만 호출됨. Destroy를 안하도록했기에 최초1회만 호출.
        override fun getItem(position: Int): Fragment {
            var fragment = BaseFragment()
            when (position) {
                0 -> fragment = DiagSurveyAssetSellFragment()
                1 -> fragment = DiagSurveyAssetBuyFragment()
            }
            fragments.put(position, fragment)
            Logger.d("MainFragmentPagerAdapter", "getItem position = " + position + "fragment = " + fragment)
            return fragment
        }

        override fun getItemPosition(`object`: Any): Int {
            Logger.d("MainFragmentPagerAdapter", "getItemPosition = POSITION_NONE  $`object`")
            return PagerAdapter.POSITION_NONE
        }

        // Returns the page title for the top indicator
        override fun getPageTitle(position: Int): CharSequence? {
            return "Page $position"
        }

        override fun destroyItem(container: ViewGroup, position: Int, instance: Any) {
            // Fragment Instance Refresh 처리..., !!!
        }
    }

}