package ecommerce.com.data.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductsBean {
    @SerializedName("product_category_id")
    @Expose
    var productCategoryId: String? = null

    @SerializedName("product_id")
    @Expose
    var productId: String? = null

    @SerializedName("ProductCode")
    @Expose
    var productCode: String? = null

    @SerializedName("ProductName")
    @Expose
    var productName: String? = null

    @SerializedName("ShortName")
    @Expose
    var shortName: String? = null

    @SerializedName("ShortDescr")
    @Expose
    var shortDescr: String? = null

    @SerializedName("RegularPrice")
    @Expose
    var regularPrice: String? = null

    @SerializedName("SalePrice")
    @Expose
    var salePrice: String? = null

    @SerializedName("ProductImage")
    @Expose
    var productImage: String? = null
}