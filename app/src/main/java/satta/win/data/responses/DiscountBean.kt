package ecommerce.com.data.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DiscountBean {
    @SerializedName("discountId")
    @Expose
    var discountId: String? = null

    @SerializedName("minAmount")
    @Expose
    var minAmount: String? = null

    @SerializedName("maxAmount")
    @Expose
    var maxAmount: String? = null

    @SerializedName("discountPer")
    @Expose
    var discountPer: String? = null
}