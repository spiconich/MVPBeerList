package space.spitsa.mvpbeerlist.mvp.views

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import com.squareup.picasso.Picasso
import space.spitsa.mvpbeerlist.R

class DetailFragment : Fragment() {

    private val TAG = "DetailView"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.e(TAG,object{}.javaClass.enclosingMethod.name)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar_layout)
        toolbar.setNavigationIcon(R.drawable.home_black_48dp);
        toolbar.setNavigationOnClickListener {

            Log.e(TAG, "back arrow pressed")

            requireActivity().supportFragmentManager.popBackStack() }

        val bundle = this.arguments

        val id = bundle?.getInt("id")
        val name = bundle?.getString("name")
        val tagline = bundle?.getString("tagline")
        val firstBrewed = bundle?.getString("firstBrewed")
        val description = bundle?.getString("description")
        val ibu = bundle?.getFloat("ibu")
        val abv = bundle?.getFloat("abv")
        val imageUrl = bundle?.getString("imageUrl")

        val descriptionTV = view.findViewById<TextView>(R.id.beer_list_detail_description)
        val taglineTV = view.findViewById<TextView>(R.id.beer_list_detail_tagline)
        val firstBrewedTV = view.findViewById<TextView>(R.id.beer_list_detail_firstBrewed)
        val idTV = view.findViewById<TextView>(R.id.beer_list_detail_id)
        val ibuTV = view.findViewById<TextView>(R.id.beer_list_detail_ibu)
        val abvTV = view.findViewById<TextView>(R.id.beer_list_detail_abv)
        val imageV = view.findViewById<ImageView>(R.id.beer_list_detail_image)

        toolbar.title = name
        descriptionTV.text = description
        ibuTV.text = "Ibu: $ibu"
        abvTV.text = "Abv: $abv"

        taglineTV.text = resources.getString(

            R.string.tagline,
            tagline
        )

        firstBrewedTV.text = resources.getString(

            R.string.firstBrewed,
            firstBrewed
        )

        idTV.text = resources.getString(

            R.string.id,
            id
        )

        Picasso.with(context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .into(imageV)
    }

}
