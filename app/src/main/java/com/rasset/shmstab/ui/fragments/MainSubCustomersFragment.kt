package com.rasset.shmstab.ui.fragments

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.rasset.shmstab.R
import com.rasset.shmstab.core.AppConst
import com.rasset.shmstab.core.TabApp
import com.rasset.shmstab.model.CustomerInfo
import com.rasset.shmstab.network.NetManager
import com.rasset.shmstab.network.protocol.ParamKey
import com.rasset.shmstab.network.protocol.ReqType
import com.rasset.shmstab.network.res.BaseModel
import com.rasset.shmstab.network.res.ResCustomerList
import com.rasset.shmstab.network.task.MainListTask
import com.rasset.shmstab.ui.MainActivity
import com.rasset.shmstab.ui.adapter.BaseRecyclerExtendsAdapter
import com.rasset.shmstab.ui.adapter.ReloadRecyclerViewScrollListner
import com.rasset.shmstab.ui.components.CropCircleTransform
import com.rasset.shmstab.utils.AUtil
import com.rasset.shmstab.utils.JUtil
import com.rasset.shmstab.utils.JUtil.isDoubleClick
import com.rasset.shmstab.utils.Logger
import jp.wasabeef.recyclerview.animators.FadeInAnimator
import kotlinx.android.synthetic.main.fragment_main_customer.*

/**
 * Created by devok on 2018-09-05.
 */

class MainSubCustomersFragment : BaseFragment() , SwipeRefreshLayout.OnRefreshListener {

    private object Holder { val INSTANCE = MainSubCustomersFragment() }

    companion object {
        val singleTone: MainSubCustomersFragment by lazy { Holder.INSTANCE }

        val instance: MainSubCustomersFragment by lazy { MainSubCustomersFragment() }

        fun newInstance(context: Context): Intent {
            val intent = Intent(context, MainSubCustomersFragment::class.java)
            return intent
        }
    }

    private var selectFilter :Int? = null
    private var mFirstSeq :Long = 0
    private var selectFilterType: Int = 0
    private var mListAdapter: CustomerListAdapter? = null
    private var myRecycler: RecyclerView? = null
    private var manager: LinearLayoutManager? = null
    private var mScrollListener: ReloadRecyclerViewScrollListner? = null
    private val mAdapterList = arrayListOf<CustomerInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_main_customer, container, false)
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
        if (mLockDialog != null)
            mLockDialog.dismiss()
    }

    override fun onRefresh() {
        Logger.d(this, "=== SubList onRefresh ===")
        //  ListView 데이터 다시가져와서 Net Callback에서 Refresh

        mFirstSeq = 0
        mScrollListener?.reset()
        mAdapterList.clear()
        mListAdapter?.notifyDataSetChanged()
        SR_REFRESH_LAYOUT?.isEnabled = false
        getCustomerList(mFirstSeq)
    }

    fun initFirst(){
        IB_TEMP_NEW_CUSTOMER.setOnClickListener {
           if (mActivity is MainActivity) {
               (mActivity as MainActivity).startDiagAttentionActivity(CustomerInfo())
           }
        }

        RG_FILTER_GROUP.setOnCheckedChangeListener(OnFilterClickListener)

        setuserInfo()
        setSwipeToRefresh()
        setRecyclerView()
        getCustomerList(mFirstSeq)
    }

    fun setuserInfo(){
        // TODO 인턴 프로필사진 이름 설정
        TabApp.userInfo?.let {
            val strProfileURL = it.photoImgPath
            Glide.with(mContext)
                    .load(strProfileURL)
                    .bitmapTransform(CropCircleTransform(mContext))
                    .crossFade(300)
                    .into(IV_USER_PROFILE_IMG)

            TV_USER_PROFILE_NAME.text = it.userName
        }

    }

    val OnFilterClickListener = RadioGroup.OnCheckedChangeListener { _: RadioGroup, checkedId: Int ->
        if (JUtil.isDoubleClick(view,1500) || selectFilter == checkedId) return@OnCheckedChangeListener

        selectFilter = checkedId
        selectFilterType = getSelctFilterType()
        onRefresh()
        Logger.d("TV_FILTER_GROUP Selected $selectFilter")
    }

    private fun getSelctFilterType() = when (selectFilter){
            R.id.RB_FILTER_ALL -> 0
            R.id.RB_FILTER_NONMEMBER -> 1
            R.id.RB_FILTER_NORMAL -> 2
            R.id.RB_FILTER_SILVER -> 3
            R.id.RB_FILTER_NOVELIE -> 4
            R.id.RB_FILTER_RICH -> 5
            R.id.RB_FILTER_ROYAL -> 6
            else -> 0
    }

    private fun setRecyclerView() {
        if (mRootView == null) return

        myRecycler = mRootView?.findViewById(R.id.RCV_CUSTOMER_LIST)
        manager = GridLayoutManager(mContext,5)
//        manager?.orientation = LinearLayoutManager.VERTICAL
        myRecycler?.layoutManager = manager
        myRecycler?.setHasFixedSize(true)
        mListAdapter = CustomerListAdapter(mAdapterList)
        // LoadMore
        mScrollListener = object : ReloadRecyclerViewScrollListner(manager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                if (mListAdapter == null)
                    return
                Logger.d(this@MainSubCustomersFragment, "=== SubList LoadMore ===")
                getCustomerList(mFirstSeq)
            }

            override fun onScrolledExt(recyclerView: RecyclerView, dx: Int, dy: Int) {
                //                Logger.d("onScrolledExt", "dy = " + dy);
                //                int size = myRecycler.computeVerticalScrollOffset();
                //                Logger.d("onScrolledExt", "size = " + size);
            }

        }
        myRecycler?.addOnScrollListener(mScrollListener)
        mListAdapter?.pageItemCountByLoadMore = AppConst.MORE_LIST_LIMIT_UNIT
//        mListAdapter?.setOnItemClickEvent()

        // @see 아이템에니메이터 구리다 그냥,.... 직접 ViewHolder에서 구현
        //        myRecycler.setItemAnimator(null);
        val animator = FadeInAnimator(LinearInterpolator())
        animator.addDuration = 1000
        animator.removeDuration = 1000
        myRecycler?.itemAnimator = animator
        // 롤리팝 이전버젼에선 카드뷰 Margin이 추가적용되므로... 롤리팝일때만 데코 추가.
        //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
        val pixel = resources.getDimensionPixelSize(R.dimen.customerlist_item_margin_height)
        myRecycler?.addItemDecoration(SpacesItemDecoration(pixel))
        //        }
        //        SlideInBottomAnimationAdapter slideUpAdapter = new SlideInBottomAnimationAdapter(mListAdapter);
        //        slideUpAdapter.setFirstOnly(true);
        //        slideUpAdapter.setDuration(800);
        //        slideUpAdapter.setInterpolator(new AccelerateDecelerateInterpolator());
        myRecycler?.adapter = mListAdapter

    }

    private fun setSwipeToRefresh() {
        if (mRootView == null) return
        // 사용안함
        SR_REFRESH_LAYOUT?.isEnabled = false

        //        swipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipeRefreshLayout);
        SR_REFRESH_LAYOUT?.setOnRefreshListener(this)
        SR_REFRESH_LAYOUT?.setColorSchemeResources(R.color.main_accent_color)
    }

    private fun getCustomerList(mFirstSeq:Long) {
        val task = MainListTask(mContext, ReqType.REQUEST_TYPE_GET_CUSOMER_LIST, this)
        task.addParam(ParamKey.PARAM_LASTSEQ, 0)
        task.addParam(ParamKey.PARAM_FIRSTSEQ, mFirstSeq)
        task.addParam(ParamKey.PARAM_LISTTYPE, selectFilterType)
        NetManager.startTask(task)
    }

    override fun onNetSuccess(data: BaseModel?, nReqType: Int) {

        if (data is ResCustomerList){
            val spannablecontent = SpannableString("회원 ${data.userTotalCount} 명")
            spannablecontent.setSpan(StyleSpan(Typeface.BOLD),
                    3, spannablecontent.length - 1, 0)
            spannablecontent.setSpan(RelativeSizeSpan(1.5f), 3,spannablecontent.length - 1, 0)
            TV_CUSTOMER_COUNT.text = spannablecontent

            if (mFirstSeq == 0L) {
                mListAdapter?.data = data.list
            } else {
                mListAdapter?.addItemAll(data.list)
            }
            mFirstSeq = data.list.last()?.idx.toLong()
            mListAdapter?.notifyDataSetChanged()
            SR_REFRESH_LAYOUT?.isRefreshing = false  // Refresh Finished
            SR_REFRESH_LAYOUT?.isEnabled = true
        }
    }

    override fun onNetFail(retCode: Int, strErrorMsg: String, nReqType: Int) {
        super.onNetFail(retCode, strErrorMsg, nReqType)
    }

    override fun onProgresStart(nReqType: Int) {

    }

    override fun onProgresStop(nReqType: Int) {

    }

    private inner class CustomerListAdapter(data: List<CustomerInfo>) : BaseRecyclerExtendsAdapter<CustomerInfo>(data) {

//        override fun onViewRecycled(holder: RecyclerView.ViewHolder?) {
//            super.onViewRecycled(holder)
//            //            Glide.clear(holder.itemView.findViewById(R.id.IV_USERLIST_PROFILE_IMAGE));
//        }

        override fun onBindViewHolderImpl(viewHolder: RecyclerView.ViewHolder, adapter: BaseRecyclerExtendsAdapter<CustomerInfo>, i: Int) {
            // If you're using your custom handler (as you should of course)
            // you need to cast viewHolder to it.
            val customerInfo = data[i]
            customerInfo.idx = i

            // View Set
            val strProfileURL = customerInfo.userId
            Glide.with(mContext)
                    .load(strProfileURL)
                    .bitmapTransform(CropCircleTransform(mContext))
                    .crossFade(300)
                    .error(R.drawable.profile_default)
                    .into((viewHolder as MyCustomViewHolder).civProfileImage)

            if (viewHolder is MyCustomViewHolder){
                viewHolder.tvCustomerName.text = customerInfo.userName
                viewHolder.tvCustomerLevel.text = getUserLevel(customerInfo.userLevel)
                viewHolder.ibProfileImage.setOnClickListener {
                    viewHolder.rootviewBg.performClick()
                }
                viewHolder.rootviewBg.setOnClickListener(View.OnClickListener {
                    if (isDoubleClick(it)) {
                        return@OnClickListener
                    }
                    viewHolder.ibProfileImage.isSelected = true
                    notifyItemChanged(i)
                    Handler().postDelayed({
                        viewHolder.ibProfileImage.isSelected = false
                        notifyItemChanged(i)
                    },300)
                    if (mActivity is MainActivity) {
                        (mActivity as MainActivity).startDiagAttentionActivity(customerInfo)
                    }
                })
            }

        }

        private fun getUserLevel(userLevel: Long): CharSequence? {
            return when (userLevel){
                0L -> "비회원"
                1L -> "일반회원"
                2L -> "실버"
                3L -> "노블리에"
                4L -> "리치"
                5L -> "로얄패밀리"
                else -> "비회원"
            }
        }

        override fun onCreateViewHolderImpl(viewGroup: ViewGroup, adapter: BaseRecyclerExtendsAdapter<CustomerInfo>, i: Int): MyCustomViewHolder {
            // Here is where you inflate your row and pass it to the constructor of your ViewHolder
            val view = MyCustomViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_main_customer, viewGroup, false))
            return view
        }

        fun addItemAllAtTop(items: List<CustomerInfo>?) {
            if (mData != null && items != null) {
                mData.addAll(0, items)
                notifyItemRangeInserted(0, items.size)
            }
        }

        fun addItemAtTop(items: CustomerInfo?, position: Int) {
            if (mData != null && items != null) {
                mData.add(position, items)
                notifyItemInserted(position)
            }
        }
    }

    internal class MyCustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rootviewBg: RelativeLayout
        var civProfileImage: ImageView
        var ibProfileImage: ImageView
        var tvCustomerName: TextView
        var tvCustomerLevel: TextView

        init {
            rootviewBg = itemView.findViewById(R.id.RL_CUSTOMER_LAYOUT)
            ibProfileImage = itemView.findViewById(R.id.IB_CUSTOMER_PROFILE)
            civProfileImage = itemView.findViewById(R.id.IV_CUSTOMER_PROFILE)
            tvCustomerName = itemView.findViewById(R.id.TV_CUSTOMER_NAME)
            tvCustomerLevel = itemView.findViewById(R.id.TV_CUSTOMER_LEVEL)
        }
    }

    internal class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
            // Add bottom margin only for other as not the first
//            if (parent.getChildAdapterPosition(view) == 0
//                || parent.getChildAdapterPosition(view) == 1
//                || parent.getChildAdapterPosition(view) == 2
//                || parent.getChildAdapterPosition(view) == 3
//                || parent.getChildAdapterPosition(view) == 4) {
//                outRect.top = space
//            } else {
//                outRect.top = space
//                outRect.bottom = space
//            }
        }
    }

}
