package com.aungpyaesone.patient.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.aungpyaesone.patient.mvp.presenters.HomePresenter
import com.aungpyaesone.patient.mvp.view.HomeView
import com.aungpyaesone.patient.utils.SessionManager
import com.aungpyaesone.shared.data.models.CoreModel
import com.aungpyaesone.shared.data.models.PatientModel
import com.aungpyaesone.shared.data.models.impls.CoreModelImpls
import com.aungpyaesone.shared.data.models.impls.PatientModelImpls
import com.aungpyaesone.shared.data.vos.*
import com.aungpyaesone.shared.mvp.presenter.AbstractBasePresenter

class HomePresenterImpl  : HomePresenter, AbstractBasePresenter<HomeView>(){
    private val mCoreModel : CoreModel = CoreModelImpls
    private val mPatientModel : PatientModel = PatientModelImpls

    init {
        mCoreModel.getSpecialityFromNetWork(onSuccess = {}, onFailure = {
            mView?.showErrorMessage(it)
        })
    }

    override fun onTapPositiveButton() {
        mView?.navigateToCaseSummary()
    }

    override fun onTapNegativeButton() {
    }

    override fun onUiReady(lifecycleOwner: LifecycleOwner) {
     // mView?.showLoading()
      mCoreModel.getSpecialityFromDb().observe(lifecycleOwner, Observer {
        //  mView?.hideLoading()
          it?.let{
              mView?.showSpecialitiesList(it)
          }

      })

      mPatientModel.getAllConsultationRequestFromApi(SessionManager.patient_id.toString(),onSuccess = {},onFailure = {mView?.showErrorMessage(it)})
      mCoreModel.getAllDoctorAcceptConsultationRequestFromDb().observe(lifecycleOwner, Observer {
          it?.let {
              mView?.showAcceptDoctorList(it)
          }
      })

        mCoreModel.getRecentlyConsultedDoctorFromApi(SessionManager.patient_id.toString(),onSuccess = {},onFailure = {})
        mCoreModel.getRecentlyConsultedDoctorFromDb().observe(lifecycleOwner, Observer {
            it?.let{
                mView?.showRecentlyConsultedDoctor(it)
            }

        })


        mPatientModel.getPatientByEmail(SessionManager.patient_email.toString(), onSuccess = {
            it.deviceId = SessionManager.patient_device_id
            mPatientModel.addPatient(it,onSuccess = {},onFailure = {})
            SessionManager.patient_name = it.name
            SessionManager.patient_id = it.id
            SessionManager.patient_dateOfBirth = it.dob
            SessionManager.comment= it.allergic_medicine
            SessionManager.patient_email = it.email
            SessionManager.patient_height = it.height
            SessionManager.patient_blood_type = it.blood_type
            SessionManager.weight = it.weight
            SessionManager.patient_device_id = it.deviceId
            SessionManager.bloodPressure = it.blood_pressure
            SessionManager.photo = it.photo
            SessionManager.created_date = it.created_date
            SessionManager.address = it.address
            SessionManager.phone = it.phone
        }, onError = {
            mView?.showErrorMessage(it)
           // mView?.hideLoading()
        })

        mPatientModel.getPatientFromDbByEmail(SessionManager.patient_email.toString()).observe(lifecycleOwner,
            Observer {

             //   mView?.hideLoading()
            })

    }

    override fun onTapSpecialitiesItem(specialitiesVO: SpecialitiesVO) {
        mView?.showConfirmationDialog(specialitiesVO)
    }

    override fun onTapStartConsultation(consulRequestVO: ConsultationRequestVO) {

       mPatientModel.updateConsultationRequestStatus(
           consulRequestVO,
           "consult",onSuccess = {},onFailure = {}
       )
       mView?.navigateToChatActivity(consulRequestVO)
    }

    override fun onTapRecentlyDoctor(recentDoctorVO: RecentDoctorVO) {
        mView?.showConConfirmDialog(recentDoctorVO)
    }

}