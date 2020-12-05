package com.aungpyaesone.patient.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.aungpyaesone.patient.R
import com.aungpyaesone.patient.adapters.RecentlyDoctorAdapter
import com.aungpyaesone.patient.adapters.SpecialitiesAdapter
import com.aungpyaesone.patient.mvp.presenters.HomePresenter
import com.aungpyaesone.patient.mvp.presenters.impls.HomePresenterImpl
import com.aungpyaesone.patient.mvp.view.HomeView
import com.aungpyaesone.patient.views.view_pods.ConsultationViewPod
import com.aungpyaesone.shared.data.vos.DoctorVO
import com.aungpyaesone.shared.data.vos.RecentDoctorVO
import com.aungpyaesone.shared.data.vos.SpecialitiesVO
import com.padc.shared.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseFragment(),HomeView {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mPresenter : HomePresenter
    private lateinit var mAdapter : SpecialitiesAdapter
   // private lateinit var mRecentlyDoctorAdapter: RecentlyDoctorAdapter
    private lateinit var mConsultationViewPod: ConsultationViewPod

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        setUpViewPod()
        setUpRecycler()
        mPresenter.onUiReady(this)
    }

    private fun setUpRecycler() {
        mAdapter = SpecialitiesAdapter(mPresenter)
        rvSpecialities.apply {
            layoutManager = GridLayoutManager(activity,2)
            adapter = mAdapter
           // setEmptyView()
        }
    }

    private fun setUpViewPod() {
        mConsultationViewPod = acceptConsultationView as ConsultationViewPod
        mConsultationViewPod.setViewPodData(DoctorVO())
        mConsultationViewPod.setDelegate(mPresenter)
    }

    private fun setUpPresenter() {
       mPresenter = getPresenter<HomePresenterImpl,HomeView>()
    }

    override fun showSpecialitiesList(specialitiesList: List<SpecialitiesVO>) {
       mAdapter.setData(specialitiesList)
    }

    override fun showRecentlyConsultedDoctor(recentlyDoctorList: List<RecentDoctorVO>) {

    }

    override fun showConfirmationDialog(specialitiesVO: SpecialitiesVO) {

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                HomeFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}