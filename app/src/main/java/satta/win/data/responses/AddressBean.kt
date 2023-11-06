package ecommerce.com.data.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AddressBean {
    @SerializedName("address_id")
    @Expose
    var addressId: String? = null

    @SerializedName("Userid")
    @Expose
    var userid: String? = null

    @SerializedName("address")
    @Expose
    var address: String? = null

    @SerializedName("city")
    @Expose
    var city: String? = null

    @SerializedName("mobile")
    @Expose
    var mobile: String? = null

    @SerializedName("pincode")
    @Expose
    var pincode: String? = null
}