package ecommerce.com.data.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CartAddBean {
    @SerializedName("Message")
    @Expose
    var message: String? = null



}