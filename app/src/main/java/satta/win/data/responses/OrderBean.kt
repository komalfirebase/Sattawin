package ecommerce.com.data.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class OrderBean {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("address_id")
    @Expose
    var addressId: String? = null

    @SerializedName("Userid")
    @Expose
    var userid: String? = null

    @SerializedName("date_time")
    @Expose
    var dateTime: String? = null

    @SerializedName("amount")
    @Expose
    var amount: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("productcount")
    @Expose
    var productcount: String? = null
}