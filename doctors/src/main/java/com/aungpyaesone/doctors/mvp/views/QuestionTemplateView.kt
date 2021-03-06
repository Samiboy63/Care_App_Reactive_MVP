package com.aungpyaesone.doctors.mvp.views

import com.aungpyaesone.shared.data.vos.GeneralQuestionVO
import com.aungpyaesone.shared.mvp.views.BaseView

interface QuestionTemplateView : BaseView {
    fun showQuestionView(templateList : List<GeneralQuestionVO>)
    fun navigateToChatActivity()
}