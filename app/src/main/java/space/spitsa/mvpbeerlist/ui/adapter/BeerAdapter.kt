package space.spitsa.mvpbeerlist.ui.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import space.spitsa.mvpbeerlist.R
import space.spitsa.mvpbeerlist.mvp.Beer

interface –°lickedBeerInterface {

    fun onClick(beer: Beer, image:Drawable?)
}
class BeerAdapter(
    private val listener: –°lickedBeerInterface,
    private val beers: List<Beer>,
    private val rowLayout: Int
) : RecyclerView.Adapter<BeerAdapter.BeerViewHolder>() {

    private val TAG="BeerAdapter"

    class BeerViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        internal var description: TextView = v.findViewById(R.id.beer_list_item_description)
        internal var name: TextView = v.findViewById(R.id.beer_list_item_name)
        internal var image: ImageView = v.findViewById(R.id.beer_list_item_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(rowLayout,parent,false)
        return BeerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {

        val current = beers[position]
        holder.name.text=current.name
        holder.description.text=current.description
        holder.itemView.setOnClickListener {

            listener.onClick(current,holder.image.drawable)
        }

        //Log.e(TAG,current.name!!)

        Picasso.with(holder.name.context)
            .load(current.imageUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.image)
    }

    override fun getItemCount(): Int {

        return beers.size
    }
}
