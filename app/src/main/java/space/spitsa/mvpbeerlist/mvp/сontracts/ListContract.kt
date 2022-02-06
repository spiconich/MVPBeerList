package space.spitsa.mvpbeerlist.mvp.сontracts

import android.content.Context
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.lifecycle.Lifecycle
import space.spitsa.mvpbeerlist.mvp.Beer

interface ListContract {

    interface View {

        fun setBeerData(beerList:List<Beer>)

    }

    interface Model {

        suspend fun getBeerData(): List<Beer>?
    }

    interface Presenter {

        fun beerListRequested()

        fun attachObserver(lifecycle: Lifecycle)

        fun packBundle(beer: Beer): Bundle
    }
}