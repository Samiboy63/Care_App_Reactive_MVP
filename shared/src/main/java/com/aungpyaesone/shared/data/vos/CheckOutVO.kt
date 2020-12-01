package com.aungpyaesone.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.aungpyaesone.shared.persistence.DeliveryRoutineTypeConverter
import com.aungpyaesone.shared.persistence.DoctorVOTypeConverter
import com.aungpyaesone.shared.persistence.PatientVOTypeConverter
import com.aungpyaesone.shared.persistence.PrescriptionTypeConverters
import com.google.firebase.firestore.IgnoreExtraProperties

@Entity(tableName = "check_out")
@IgnoreExtraProperties
@TypeConverters(PrescriptionTypeConverters::class,DoctorVOTypeConverter::class,PatientVOTypeConverter::class,DeliveryRoutineTypeConverter::class)
data class CheckOutVO(
        @PrimaryKey
        var id:String = "",
        var address:String? ="",
        var delivery_routine : DeliveryRoutineVO? = null,
        var doctor: DoctorVO? = null,
        var patientVO: PatientVO? =null,
        var prescriptionVO: ArrayList<PrescriptionVO>? = arrayListOf(),
        var totalPrice: Int? = 0
) {
}