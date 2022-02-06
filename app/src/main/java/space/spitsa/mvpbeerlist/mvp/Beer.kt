package space.spitsa.mvpbeerlist.mvp

import com.google.gson.annotations.SerializedName

class Beer {

    var id: Int? = null
    var name: String? = null
    var tagline: String? = null
    @SerializedName("first_brewed")
    var firstBrewed: String? = null
    var description: String? = null
    @SerializedName("image_url")
    var imageUrl: String? = null
    var abv: Float? = null
    var ibu: Float? =null
    @SerializedName("target_fg")
    val targetFg: Int? = null
    @SerializedName("target_og")
    val targetOg: Float? = null
    val ebc: Int? = null
    val srm: Float? = null
    val ph: Float? = null
    @SerializedName("attenuation_level")
    val attenuationLevel: Float? = null
}