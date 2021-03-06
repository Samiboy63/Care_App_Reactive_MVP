package com.aungpyaesone.shared.data.vos
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.aungpyaesone.shared.persistence.converters.CaseSummaryTypeConverter
import com.aungpyaesone.shared.persistence.converters.DoctorVOTypeConverter
import com.aungpyaesone.shared.persistence.converters.PatientVOTypeConverter
import com.google.firebase.firestore.IgnoreExtraProperties

@Entity(tableName = "consultation_request")
@IgnoreExtraProperties
@TypeConverters(
        CaseSummaryTypeConverter::class,
        PatientVOTypeConverter::class,
        DoctorVOTypeConverter::class,
        )
data class ConsultationRequestVO(
        @PrimaryKey
        var id: String= "",
        var patient: PatientVO ? = null,
        var speciality : String ?= "",
        var date_time : String?= null,
        var doctor: DoctorVO? = null,
        var case_summary : ArrayList<QuestionAnswerVO> ?= arrayListOf(),
        var status:String? = "",
        var patient_id:String? ="",
        var doctor_id: String ="",
        var patient_type : String? = "",
        var consultationchat_id : String? = ""
)
{

}