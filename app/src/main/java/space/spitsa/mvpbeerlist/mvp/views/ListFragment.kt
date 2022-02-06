package space.spitsa.mvpbeerlist.mvp.views

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import space.spitsa.mvpbeerlist.mvp.сontracts.ListContract
import space.spitsa.mvpbeerlist.mvp.presenters.ListPresenter
import space.spitsa.mvpbeerlist.R
import space.spitsa.mvpbeerlist.mvp.Beer
import space.spitsa.mvpbeerlist.ui.adapters.BeerAdapter
import space.spitsa.mvpbeerlist.ui.adapters.clickedBeerInterface


class ListFragment : ListContract.View, clickedBeerInterface, Fragment() {

    private lateinit var listPresenter: ListPresenter
    private lateinit var recyclerView: RecyclerView
    private var clickedImage: Drawable? = null
    private val TAG ="List View"

    @Override
    override fun onClick(beer: Beer, image: Drawable?) {

        Log.e(TAG,object{}.javaClass.enclosingMethod.name)

        val bundle = listPresenter.packBundle(beer)
        clickedImage = image
        val manager = requireActivity().supportFragmentManager
        val transaction = manager.beginTransaction()
        val beerDetailFragment = DetailFragment()
        beerDetailFragment.arguments = bundle
        transaction.replace(R.id.list_fragment, beerDetailFragment)
        transaction.addToBackStack(beerDetailFragment::class.java.name);
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.e(TAG,object{}.javaClass.enclosingMethod.name)

        val lifecycleRegistry = LifecycleRegistry(this)
        listPresenter= ListPresenter(this)
        listPresenter.attachObserver(lifecycleRegistry as Lifecycle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view) ?: return
        recyclerView.layoutManager = LinearLayoutManager(context)

        Log.e(TAG,object{}.javaClass.enclosingMethod.name)

        val beerList = listPresenter.beerListRequested()
    }

    override fun setBeerData(beerList: List<Beer>) {

        Log.e(TAG,object{}.javaClass.enclosingMethod.name)

        recyclerView!!.adapter =
            BeerAdapter(this, beerList!!, R.layout.beer_list_item)
        val shimmer = requireView().findViewById<ShimmerFrameLayout>(R.id.shimmer_list_container)
        shimmer.stopShimmer()
        shimmer.hideShimmer()
        shimmer.isVisible = false
        recyclerView.isVisible = true
    }
}