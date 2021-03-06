package com.aungpyaesone.doctors.mvp.views

import com.aungpyaesone.shared.data.vos.PrescriptionVO
import com.aungpyaesone.shared.mvp.views.BaseView

interface PrescriptionInfoView : BaseView {
    fun displayPrescriptionList(prescription_list: List<PrescriptionVO>)

}