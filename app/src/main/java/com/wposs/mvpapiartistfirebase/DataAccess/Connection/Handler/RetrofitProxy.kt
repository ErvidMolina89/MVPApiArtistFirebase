package com.wposs.mvpapiartistfirebase.DataAccess.Connection.Handler

import android.content.Context
import com.wposs.mvpapiartistfirebase.BuildConfig
import com.google.gson.GsonBuilder
import com.wposs.mvpapiartistfirebase.DataAccess.Connection.Interfaces.IGenericServices
import com.wposs.mvpapiartistfirebase.DataAccess.Connection.Interfaces.IRetrofitParcelable
import com.wposs.mvpapiartistfirebase.DataAccess.Connection.Interfaces.IServiceParameters
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProxy {
    private var context : Context?= null
    private var page: Int = 1
    private var limit: Int = 20
    fun withContext(context : Context) :objectToSend{
        this.context = context
        return objectToSend()
    }

    private var listenerObjectToSend : IRetrofitParcelable?= null
    inner class objectToSend{
        fun withObjectToSend(listenerObjectToSend : IRetrofitParcelable) : expectedObjeto{
            this@RetrofitProxy.listenerObjectToSend = listenerObjectToSend
            return expectedObjeto()
        }
    }

    private var ExpectedObjectClass : Class<*> ?= null
    inner class expectedObjeto{
        fun <T:IRetrofitParcelable> withExpectedObjeto(expectedObjeto : Class<T>) : answerObjeto{
            this@RetrofitProxy.ExpectedObjectClass = expectedObjeto
            return answerObjeto()
        }
    }

    private var listenerAnswerObject : ((IRetrofitParcelable?)->Unit)?= null
    private var listenerListObjetc : ((MutableList<IRetrofitParcelable>?)->Unit)?= null
    inner class answerObjeto{

        fun withListenerObjetc(listenerAnswerObject : ((IRetrofitParcelable?)->Unit)?) : answerObjeto{
            this@RetrofitProxy.listenerAnswerObject = listenerAnswerObject
            return this
        }

        fun withListenerListObjetcs(listenerListObjetc : ((MutableList<IRetrofitParcelable>?)->Unit)?) : answerObjeto{
            this@RetrofitProxy.listenerListObjetc = listenerListObjetc
            return this
        }

        fun listenerFailure() : answerFailure{
            return answerFailure()
        }

    }

    private var listenerOfFailure : ((Int,Int)->Unit)?= null
    inner class answerFailure{
        fun withAnswerOfFailure(listenerOfFailure : ((Int,Int)->Unit)) : service{
            this@RetrofitProxy.listenerOfFailure = listenerOfFailure
            return service()
        }
    }

    private var serviceSelected : IServiceParameters?= null
    inner class service{
        fun withService(service : IServiceParameters) : QueryManager{
            this@RetrofitProxy.serviceSelected = service
            return QueryManager()
        }
    }

    private val header = emptyMap<String,String>().toMutableMap()
    private val complementaryFields = emptyMap<String,String>().toMutableMap()

    inner class QueryManager{

        fun withParameterHeader(key : String, data : String) : QueryManager{
            header[key] = data
            return this
        }

        fun withComplementaryFields(key : String , data : String) : QueryManager{
            complementaryFields[key] = data
            return this
        }

        fun withPagination(page: Int, limit: Int): QueryManager {
            this@RetrofitProxy.page = page
            this@RetrofitProxy.limit = limit
            return this
        }

        fun startQuery(){

            val retrofit = generateRetrofit()
            val genericService = retrofit.create(IGenericServices::class.java)

            val call = toSelectCall(genericService)

            call.enqueue(
                HandlerAnswerRetrofit()
                    .withListenerOfFailure(listenerOfFailure)
                    .withListenerListObjetcs(listenerListObjetc)
                    .withListenerAnswerObjetcs(listenerAnswerObject)
                    .withExpectedObject(ExpectedObjectClass)
            )

        }

        private fun generateRetrofit() : Retrofit {
            val BaseURL = BuildConfig.Base_Url

            val gson =  GsonBuilder()
                .setLenient()
                .create();


            return Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        }

        private fun toSelectCall(genericService : IGenericServices) : Call<Any>{
            return when(serviceSelected!!.getMethods()){
                IServiceParameters.Methods.GET -> genericService.getGeneric(serviceSelected!!.getURL()+BuildConfig.Api_Key, page, limit)
                IServiceParameters.Methods.POST -> genericService.postGeneric(serviceSelected!!.getURL()+BuildConfig.Api_Key)
                IServiceParameters.Methods.PUT -> genericService.putGeneric(serviceSelected!!.getURL()+BuildConfig.Api_Key)
                IServiceParameters.Methods.DELETE -> genericService.deleteGeneric(serviceSelected!!.getURL()+BuildConfig.Api_Key)
            }

        }

    }

}