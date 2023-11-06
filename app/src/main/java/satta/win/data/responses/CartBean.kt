package ecommerce.com.data.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CartBean {
    @SerializedName("CartId")
    @Expose
    var cartId: String? = null

    @SerializedName("Userid")
    @Expose
    var userid: String? = null

    @SerializedName("product_id")
    @Expose
    var productId: String? = null

    @SerializedName("ProductCode")
    @Expose
    var productCode: String? = null

    @SerializedName("ProductName")
    @Expose
    var productName: String? = null

    @SerializedName("Qty")
    @Expose
    var qty: String? = null

    @SerializedName("RegularPrice")
    @Expose
    var regularPrice: String? = null

    @SerializedName("Amount")
    @Expose
    var amount: String? = null

    @SerializedName("ShortDescr")
    @Expose
    var shortDescr: String? = null

    @SerializedName("SalePrice")
    @Expose
    var salePrice: String? = null

    @SerializedName("ShortName")
    @Expose
    var shortName: String? = null

    @SerializedName("ProductImage")
    @Expose
    var productImage: String? = null
}