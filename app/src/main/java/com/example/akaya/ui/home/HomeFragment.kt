package com.example.akaya.ui.home

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.akaya.R
import com.example.akaya.ui.api.ApiHelperImpl
import com.example.akaya.ui.api.RetrofitBuilder
import com.example.akaya.ui.dialog.CustomLoaderDialog
import com.example.akaya.ui.model.SignUpRequest
import com.example.akaya.ui.model.categoryList
import com.example.akaya.ui.model.offersList
import com.example.akaya.ui.register.SignUpViewModel
import com.example.akaya.utils.AndroidUtility
import com.example.akaya.utils.Status
import com.example.akaya.utils.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment:Fragment() {
    lateinit var mActivity: Activity
    var mView: View? = null
    private var rv_banner_list: RecyclerView?=null
    private var rv_categorilist:RecyclerView?=null
    private lateinit var viewModel: Homeviewmodel
    private lateinit var viewModel1: Homecategoryviewmodel

    lateinit var mCustomLoaderDialog: CustomLoaderDialog
    var homeData: ArrayList<offersList> = arrayListOf()
    var categorydata: ArrayList<categoryList> = arrayListOf()
    lateinit var mAdapter: HomeAdapter
    lateinit var mAdapter1: HomecategoriesAdapter
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity= context as Activity
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_home, container, false)
        mCustomLoaderDialog = CustomLoaderDialog(mActivity)

        initview(mView!!)
        setUpViewModel()
        setUpViewModel1()
        setupObserver()
        setupObserver1()
        homeapi()

        return mView
    }
    private fun initview(mview:View){
        rv_banner_list=mview!!.findViewById(R.id.rv_banner_list)
        rv_categorilist=mview!!.findViewById(R.id.rv_categorilist)
        mView!!.rv_banner_list!!.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        mView!!.rv_banner_list.setHasFixedSize(true)

        mView!!.rv_categorilist!!.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        mView!!.rv_categorilist.setHasFixedSize(true)




    }
    private fun homeapi(){
        viewModel.requesthomebanner(mActivity)
        viewModel1.requestcategory(mActivity)
    }

//    private fun retrieveList( offersList: List<offersList>) {
//        mAdapter.apply {
//            addUsers(offersList)
//            notifyDataSetChanged()
//        }
//    }
    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService)
            )
        ).get(Homeviewmodel::class.java)
    }
    private fun setUpViewModel1() {
        viewModel1 = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService)
            )
        ).get(Homecategoryviewmodel::class.java)
    }


    private fun setupObserver() {
        activity?.let {
            viewModel.home_banner().observe(it, Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        hideLoader()
                        val baseResponse = it.data
                        val errorCode = baseResponse?.status?.error_code
                        when {
                            (errorCode == 0) -> {
                                AndroidUtility.showToast(mActivity,"Success" )
                                homeData = baseResponse.result!!.offerlist as ArrayList<offersList>
                                mAdapter = HomeAdapter(mActivity, (homeData as List<offersList>?)!!)
                                mView!!.rv_banner_list.adapter = mAdapter
                                mAdapter.notifyDataSetChanged()


                            }
                            (errorCode == 1)->{
                                AndroidUtility.showToast(mActivity,baseResponse.status?.message?:"" )
                            }
                        }

                        //baseResponse.let { offersList -> offersList?.let { it1 -> retrieveList(it1) } }





                    }


                    Status.LOADING -> {
                        showLoader()
                    }
                    Status.ERROR -> {
                        hideLoader()
                        AndroidUtility.showToast(mActivity, getString(R.string.something_went_wrong))
                    }
                }

            })
        }
    }
    private fun setupObserver1() {
        activity?.let {
            viewModel1.category().observe(it, Observer {
                when (it.status) {
                    Status.SUCCESS -> {
                        hideLoader()
                        val baseResponse = it.data
                        val errorCode = baseResponse?.status?.error_code
                        when {
                            (errorCode == 0) -> {
                                AndroidUtility.showToast(mActivity,"Success" )
                                categorydata = baseResponse.result!!.categoryList as ArrayList<categoryList>
                                mAdapter1 = HomecategoriesAdapter(mActivity, (categorydata as List<categoryList>?)!!)
                                mView!!.rv_categorilist.adapter = mAdapter1
                                mAdapter1.notifyDataSetChanged()


                            }
                            (errorCode == 1)->{
                                AndroidUtility.showToast(mActivity,baseResponse.status?.message?:"" )
                            }
                        }

                        //baseResponse.let { offersList -> offersList?.let { it1 -> retrieveList(it1) } }





                    }


                    Status.LOADING -> {
                        showLoader()
                    }
                    Status.ERROR -> {
                        hideLoader()
                        AndroidUtility.showToast(mActivity, getString(R.string.something_went_wrong))
                    }
                }

            })
        }
    }

    fun showLoader() {
        mCustomLoaderDialog.show()
    }

    fun hideLoader() {
        if (mCustomLoaderDialog.isShowing)
            mCustomLoaderDialog.cancel()
    }





}