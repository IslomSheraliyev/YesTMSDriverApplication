package com.yestms.driver.android.data.remote.response.loads.get

import com.google.gson.annotations.SerializedName

data class LoadRemoteModel(
    val id: Int?,
    val loadId: String?,
    val activationLink: String?,
    val rate: Int?,
    val mileage: Int?,
    val commodity: String?,
    val weight: Int?,
    val height: Int?,
    val length: Int?,
    val pickUpLocation: String?,
    val pickUpLat: String?,
    val pickUpLng: String?,
    val pickUpPolitical: String?,
    val pickUpDate: String?,
    val pickUpAppointment: Boolean?,
    val pickUpNote: String?,
    val deliveryLocation: String?,
    val deliveryLat: String?,
    val deliveryLng: String?,
    val deliveryPolitical: String?,
    val deliveryDate: String?,
    val deliveryAppointment: Boolean?,
    val deliveryNote: String?,
    val expenses: Int?,
    val notes: String?,
    val createdAt: String?,
    val updatedAt: String?,
    val isAlert: Boolean?,
    val loadStatusId: Int?,
    val mcId: Int?,
    val brokerId: Int?,
    val driverId: Int?,
    val dispatcherId: Int?,
    val parentId: Int?,
    val companyId: Int?,
    @SerializedName("load_status")
    val loadStatus: LoadStatusRemoteModel?,
    @SerializedName("company_mc_number")
    val companyMcNumber: LoadsCompanyMcNumberRemoteModel?,
    val broker: LoadsBrokerRemoteModel?,
    val drivers: List<LoadsDriverRemoteModel>?,
    val dispatcher: LoadsDispatcherRemoteModel?,
    @SerializedName("rate_cons")
    val rateCons: List<LoadsRateConsRemoteModel>?,
    @SerializedName("media_bols")
    val mediaBols: List<LoadsMediaBolsRemoteModel>?
)