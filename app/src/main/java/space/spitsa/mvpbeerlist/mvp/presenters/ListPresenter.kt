package space.spitsa.mvpbeerlist.mvp.presenters

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import space.spitsa.mvpbeerlist.mvp.Beer
import space.spitsa.mvpbeerlist.mvp.сontracts.ListContract
import space.spitsa.mvpbeerlist.mvp.models.ListModel
import kotlin.coroutines.CoroutineContext

class ListPresenter (

    private var listView: ListContract.View?): ListContract.Presenter, LifecycleObserver,CoroutineScope{

    private val supervisorJob = SupervisorJob()
    private val listModel= ListModel()
    private var beerList:List<Beer>?=null
    lateinit var scope: CoroutineScope

    private val TAG = "List Presenter"

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + supervisorJob

    override fun beerListRequested() {

        Log.e(TAG,object{}.javaClass.enclosingMethod.name)

        //viewLifecycle.lifecycleScope.launch {}
        //Проверяем, был ли уже загружен ранее список пива.
        scope= CoroutineScope(coroutineContext)
        scope.launch {

            Log.e(TAG,"Coroutine started")

            if (beerList == null) {

                beerList = listModel.getBeerData()
            }
            else
            {

                Log.e(TAG,"Using downloaded data")
            }

            if (beerList != null ) {

                listView?.setBeerData(beerList!!) //TODO: NORM???
            }
            else

                Log.e(TAG, "beer list is empty")
        }

    }

    override fun attachObserver(viewLifecycle: Lifecycle) {

        Log.e(TAG,object{}.javaClass.enclosingMethod.name)

        viewLifecycle.addObserver(this)
    }

    override fun packBundle(beer: Beer):Bundle {

        Log.e(TAG,object{}.javaClass.enclosingMethod.name)

        val bundle = Bundle()
        try {

            bundle.putInt("id", beer.id!!)
            bundle.putString("name", beer.name)
            bundle.putString("tagline", beer.tagline!!)
            bundle.putString("firstBrewed", beer.firstBrewed!!)
            bundle.putString("description", beer.description!!)
            bundle.putString("imageUrl", beer.imageUrl!!)
            bundle.putFloat("abv", beer.abv!!)
            bundle.putFloat("ibu", beer.ibu!!)

        } catch (e: Exception) {

            Log.e("TAG", "Some row was empty")
        }

        return bundle
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {

        supervisorJob.cancel()
    }

}