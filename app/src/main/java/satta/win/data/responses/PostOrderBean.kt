package ecommerce.com.data.responses

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class PostOrderBean {
    @SerializedName("id")
    @Expose
    var id: String? = null
}