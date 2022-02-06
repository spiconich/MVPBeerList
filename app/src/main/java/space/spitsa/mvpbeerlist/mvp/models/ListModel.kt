package space.spitsa.mvpbeerlist.mvp.models

import android.util.Log
import androidx.annotation.Nullable
import space.spitsa.mvpbeerlist.mvp.Beer
import space.spitsa.mvpbeerlist.BeersNetworkProvider
import space.spitsa.mvpbeerlist.mvp.сontracts.ListContract
import java.lang.Exception

class ListModel: ListContract.Model {

    private val TAG="ListModel"

    @Nullable
    override suspend fun getBeerData(): List<Beer>? {

        Log.e(TAG,object{}.javaClass.enclosingMethod.name)

        val beersAnswer = BeersNetworkProvider()

        return beersAnswer.provide()
    }
}