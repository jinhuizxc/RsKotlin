package com.xyz.leeeyou.zhihuribao.view.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.xyz.leeeyou.zhihuribao.R
import com.xyz.leeeyou.zhihuribao.base.BaseFragment
import com.xyz.leeeyou.zhihuribao.view.adapter.one.MultipleItemQuickAdapterForOneIndex
import com.xyz.leeeyou.zhihuribao.model.one.ID
import com.xyz.leeeyou.zhihuribao.model.one.Index
import com.xyz.leeeyou.zhihuribao.di.component.DaggerOneComponent
import com.xyz.leeeyou.zhihuribao.di.module.OneModule
import com.xyz.leeeyou.zhihuribao.utils.inflate
import com.xyz.leeeyou.zhihuribao.model.one.OneIndexMultipleItem
import com.xyz.leeeyou.zhihuribao.view.activity.IndexActivity
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.lang.kotlin.subscribeBy
import rx.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

/**
 * Created by leeeyou on 2017/4/24.
 *
 * 一个主界面
 */
class OneFragment : BaseFragment() {

    private var TAG = OneFragment::class.java.simpleName

    @Inject
    lateinit var mIndexObservable: Observable<Index>

    @Inject
    lateinit var mIdObservable: Observable<ID>

    internal var mNoMoreDataView: View? = null

    lateinit var mIdList: Array<String>
    lateinit var mRecyclerView: RecyclerView
    var mIndexAdapter: MultipleItemQuickAdapterForOneIndex? = null

    var mPosition: Int = 0

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = container!!.inflate(R.layout.fragment_one)
        mRecyclerView = rootView.findViewById(R.id.recyclerView_one)
        mRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()
        updateData()
    }

    private fun initAdapter() {
        mIndexAdapter = MultipleItemQuickAdapterForOneIndex(null)
        mIndexAdapter?.setOnLoadMoreListener {
            if (mPosition < mIdList.size - 1) {
                loadIndexData(++mPosition)
            }
        }
        mIndexAdapter?.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        mRecyclerView.adapter = mIndexAdapter
    }

    /**
     * 获取id数据
     */
    private fun fetchIdData() {
        mIdObservable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            (activity as IndexActivity).refreshComplete()
                            it.printStackTrace()
                        },
                        onNext = {
                            mIdList = it.data
//                            Log.e(TAG, "mIdList =" + it.data);  // mIdList =[Ljava.lang.String;@8d0f59e
                            mPosition = 0
                            loadIndexData(mPosition)
                        }
                )
    }

    private fun loadIndexData(position: Int) {
        DaggerOneComponent.builder().oneModule(OneModule(mIdList[position].toInt())).build().inject(this)
        fetchIndexData()
    }

    /**
     * 获取index数据
     */
    private fun fetchIndexData() {
        mIndexObservable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onError = {
                            (activity as IndexActivity).refreshComplete()
                            it.printStackTrace()
                        },
                        onNext = {
                            (activity as IndexActivity).refreshComplete()

                            /**
                             * E/OneFragment: it 0 =Index(res=0, data=IndexData(id=4626, date=2017-12-19 06:00:00, weather=Weather(city_name=地球, date=2017-12-19, temperature=-275, humidity=120, climate=对流层, wind_direction=一阵妖风, hurricane=36级), content_list=[OneIndex(id=14119, category=0, display_category=6, item_id=1929, title=插画, forward=我觉得我们可能都太爱自己了，所以很爱装，很会装着去爱别人。, img_url=http://image.wufazhuce.com/FrZde1XtCSV1Q3F8fH3NUQBAPdlO, like_count=8914, post_date=2017-12-19 06:00:00, last_update_date=2017-12-11 11:55:26, author=Author(user_id=null, user_name=null, desc=null, wb_name=null, is_settled=null, settled_type=null, summary=null, fans_total=null, web_url=null), video_url=, audio_url=, audio_platform=2, start_video=, volume=VOL.1900, pic_info=Moeder Lin, words_info=陈升《咸鱼的滋味》, subtitle=, number=0, serial_id=0, serial_list=[], movie_story_id=0, ad_id=0, ad_type=0, ad_pvurl=, ad_linkurl=, ad_makettime=, ad_closetime=, ad_share_cnt=, ad_pvurl_vendor=, content_id=1929, content_type=0, content_bgcolor=#FFFFFF, share_url=http://m.wufazhuce.com/one/1929, tag_list=[], music_name=null, audio_author=null, audio_album=null), OneIndex(id=14160, category=5, display_category=6, item_id=1259, title=我们平凡女孩，一辈子也遇不到一个江辰, forward=能吃的狗粮，看看偶像剧就饱了。, img_url=http://image.wufazhuce.com/Fuw6Mr8vhjGxlszrvAmvCXG8BAjJ, like_count=1037, post_date=2017-12-19 06:00:00, last_update_date=2017-12-18 15:39:48, author=Author(user_id=7898995, user_name=李开春, desc=爱国儿女，鸡汤爱好者，不务正业的理工女。, wb_name=@李开开开春, is_settled=0, settled_type=0, summary=爱国儿女，鸡汤爱好者，不务正业的理工女。, fans_total=8323, web_url=http://image.wufazhuce.com/FuCd1X9lLbWuu3Ps_aoMd8vJjQml), video_url=, audio_url=, audio_platform=2, start_video=, volume=0, pic_info=, words_info=, subtitle=致我们单纯的小美好, number=0, serial_id=0, serial_list=[], movie_story_id=4242, ad_id=0, ad_type=0, ad_pvurl=, ad_linkurl=, ad_makettime=, ad_closetime=, ad_share_cnt=, ad_pvurl_vendor=, content_id=1259, content_type=5, content_bgcolor=#FFFFFF, share_url=http://m.wufazhuce.com/movie/1259, tag_list=[], music_name=null, audio_author=null, audio_album=null), OneIndex(id=14171, category=1, display_category=6, item_id=2978, title=猫还好吗？, forward=信任这种东西是很虚无缥缈的，像散落在宇宙间的星云，可以看见，但却不容易触摸。想象力是信任的宿敌。, img_url=http://image.wufazhuce.com/FnFNTExBzuZaRRYFjnzuQJtlV0VI, like_count=627, post_date=2017-12-19 06:00:00, last_update_date=2017-12-18 19:12:41, author=Author(user_id=5540827, user_name=与路, desc=作家，重度白日梦患者。, wb_name=, is_settled=0, settled_type=0, summary=作家，重度白日梦患者。, fans_total=461, web_url=http://image.wufazhuce.com/FqsR2GNvE2NfQuRQmBkE5MRn6x19), video_url=, audio_url=, audio_platform=2, start_video=, volume=0, pic_info=, words_info=, subtitle=, number=0, serial_id=0, serial_list=[], movie_story_id=0, ad_id=0, ad_type=0, ad_pvurl=, ad_linkurl=, ad_makettime=, ad_closetime=, ad_share_cnt=, ad_pvurl_vendor=, content_id=2978, content_type=1, content_bgcolor=#FFFFFF, share_url=http://m.wufazhuce.com/article/2978, tag_list=[Tag(id=7, title=ONE STORY)], music_name=null, audio_author=null, audio_album=null), OneIndex(id=14146, category=2, display_category=6, item_id=496, title=黑黑的故乡·粮店一家（4）, forward=面对这个结果，我妈一屁股坐在竹筒上，无法接受，想不明白。, img_url=http://image.wufazhuce.com/FnR1adrRSIdTIJE7JMqtdvAl6UA5, like_count=156, post_date=2017-12-19 06:00:00, last_update_date=2017-12-13 17:36:43, author=Author(user_id=6356695, user_name=大给, desc=《心理罪》《黑洞故事》《有爱我们床上谈》。微信公众号：大给坏趣味。, wb_name=@坏趣味大给, is_settled=0, settled_type=0, summary=写点字、编点剧。, fans_total=2988, web_url=http://
                             *
                             * E/OneFragment: indexData =
                             * [OneIndexMultipleItem(itemType=0, indexData=null, weather=Weather(city_name=地球, date=2017-12-19, temperature=-275, humidity=120, climate=对流层, wind_direction=一阵妖风, hurricane=36级)), OneIndexMultipleItem(itemType=1, indexData=OneIndex(id=14119, category=0, display_category=6, item_id=1929, title=插画, forward=我觉得我们可能都太爱自己了，所以很爱装，很会装着去爱别人。, img_url=http://image.wufazhuce.com/FrZde1XtCSV1Q3F8fH3NUQBAPdlO, like_count=8914, post_date=2017-12-19 06:00:00, last_update_date=2017-12-11 11:55:26, author=Author(user_id=null, user_name=null, desc=null, wb_name=null, is_settled=null, settled_type=null, summary=null, fans_total=null, web_url=null), video_url=, audio_url=, audio_platform=2, start_video=, volume=VOL.1900, pic_info=Moeder Lin, words_info=陈升《咸鱼的滋味》, subtitle=, number=0, serial_id=0, serial_list=[], movie_story_id=0, ad_id=0, ad_type=0, ad_pvurl=, ad_linkurl=, ad_makettime=, ad_closetime=, ad_share_cnt=, ad_pvurl_vendor=, content_id=1929, content_type=0, content_bgcolor=#FFFFFF, share_url=http://m.wufazhuce.com/one/1929, tag_list=[], music_name=null, audio_author=null, audio_album=null), weather=null), OneIndexMultipleItem(itemType=-1, indexData=null, weather=null), OneIndexMultipleItem(itemType=2, indexData=OneIndex(id=14160, category=5, display_category=6, item_id=1259, title=我们平凡女孩，一辈子也遇不到一个江辰, forward=能吃的狗粮，看看偶像剧就饱了。, img_url=http://image.wufazhuce.com/Fuw6Mr8vhjGxlszrvAmvCXG8BAjJ, like_count=1037, post_date=2017-12-19 06:00:00, last_update_date=2017-12-18 15:39:48, author=Author(user_id=7898995, user_name=李开春, desc=爱国儿女，鸡汤爱好者，不务正业的理工女。, wb_name=@李开开开春, is_settled=0, settled_type=0, summary=爱国儿女，鸡汤爱好者，不务正业的理工女。, fans_total=8323, web_url=http://image.wufazhuce.com/FuCd1X9lLbWuu3Ps_aoMd8vJjQml), video_url=, audio_url=, audio_platform=2, start_video=, volume=0, pic_info=, words_info=, subtitle=致我们单纯的小美好, number=0, serial_id=0, serial_list=[], movie_story_id=4242, ad_id=0, ad_type=0, ad_pvurl=, ad_linkurl=, ad_makettime=, ad_closetime=, ad_share_cnt=, ad_pvurl_vendor=, content_id=1259, content_type=5, content_bgcolor=#FFFFFF, share_url=http://m.wufazhuce.com/movie/1259, tag_list=[], music_name=null, audio_author=null, audio_album=null), weather=null), OneIndexMultipleItem(itemType=-1, indexData=null, weather=null), OneIndexMultipleItem(itemType=2, indexData=OneIndex(id=14171, category=1, display_category=6, item_id=2978, title=猫还好吗？, forward=信任这种东西是很虚无缥缈的，像散落在宇宙间的星云，可以看见，但却不容易触摸。想象力是信任的宿敌。, img_url=http://image.wufazhuce.com/FnFNTExBzuZaRRYFjnzuQJtlV0VI, like_count=627, post_date=2017-12-19 06:00:00, last_update_date=2017-12-18 19:12:41, author=Author(user_id=5540827, user_name=与路, desc=作家，重度白日梦患者。, wb_name=, is_settled=0, settled_type=0, summary=作家，重度白日梦患者。, fans_total=461, web_url=http://image.wufazhuce.com/FqsR2GNvE2NfQuRQmBkE5MRn6x19), video_url=, audio_url=, audio_platform=2, start_video=, volume=0, pic_info=, words_info=, subtitle=, number=0, serial_id=0, serial_list=[], movie_story_id=0, ad_id=0, ad_type=0, ad_pvurl=, ad_linkurl=, ad_makettime=, ad_closetime=, ad_share_cnt=, ad_pvurl_vendor=, content_id=2978, content_type=1, content_bgcolor=#FFFFFF, share_url=http://m.wufazhuce.com/article/2978, tag_list=[Tag(id=7, title=ONE STORY)], music_name=null, audio_author=null, audio_album=null), weather=null), OneIndexMultipleItem(itemType=-1, indexData=null, weather=null), OneIndexMultipleItem(itemType=2, indexData=OneIndex(id=14146, category=2, display_category=6, item_id=496, title=黑黑的故乡·粮店一家（4）, forward=面对这个结果，我妈一屁股坐在竹筒上，无法接受，想不明白。, img_url=http://image.wufazhuce.com/F
                             */
                            Log.e(TAG, "it 0 =" + it);
                            val indexData = parseIndexData(it)
                            Log.e(TAG, "indexData =" + indexData);
                            val isPullToRefresh = mPosition == 0
                            if (isPullToRefresh) {
                                mIndexAdapter?.setNewData(indexData)
                            } else {
                                mIndexAdapter?.addData(indexData)
                                mIndexAdapter?.loadMoreComplete()
                            }
                        })
    }


    private fun parseIndexData(index: Index): MutableList<OneIndexMultipleItem> {
        val tempDataList: MutableList<OneIndexMultipleItem> = ArrayList()

        val isPullToRefresh = mPosition == 0
        if (isPullToRefresh) {
            tempDataList.add(OneIndexMultipleItem(OneIndexMultipleItem.WEATHER, null, index.data.weather))
        }

        val contentList = index.data.content_list
        for (i in contentList.indices) {
            tempDataList.add(OneIndexMultipleItem(if (i == 0) OneIndexMultipleItem.TOP else OneIndexMultipleItem.READ, contentList[i], null))
            tempDataList.add(OneIndexMultipleItem(OneIndexMultipleItem.BLANK, null, null))
        }

        val isLoadMoreEnd = mPosition == mIdList.size - 1
        if (isLoadMoreEnd) {
            loadMoreEnd()
        }

        return tempDataList
    }

    override fun checkCanDoRefresh(): Boolean {
        return !mRecyclerView.canScrollVertically(-1)
    }

    override fun updateData() {
        mIndexAdapter?.setEnableLoadMore(true)
        mIndexAdapter?.removeAllFooterView()
        DaggerOneComponent.builder().oneModule(OneModule()).build().inject(this)
        fetchIdData()
    }

    private fun loadMoreEnd() {
        mIndexAdapter?.loadMoreEnd()
        mIndexAdapter?.setEnableLoadMore(false)
        if (mNoMoreDataView == null) {
            mNoMoreDataView = LayoutInflater.from(context).inflate(R.layout.not_loading, null, false)
        }
        mIndexAdapter?.addFooterView(mNoMoreDataView)
    }

}