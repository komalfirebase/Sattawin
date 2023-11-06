package ecommerce.com.data.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CategoryBean {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("image")
    @Expose
    var image: String? = null
}