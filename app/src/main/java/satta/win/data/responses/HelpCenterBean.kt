package ecommerce.com.data.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HelpCenterBean {
    @SerializedName("help")
    @Expose
    var help: String? = null
}