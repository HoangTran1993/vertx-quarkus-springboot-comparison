package org.acme.getting.started

import com.google.gson.Gson
import org.acme.getting.started.service.DataService
import javax.inject.Inject
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.*

@Path("/cars")
class CarResource {

    @Inject
    lateinit var responseStream: CarStreamResponseOutput

    @GET
    @Produces("application/json")
    fun getCarsAsList() : Response {
        val blockingGet = DataService.getDataStream(0).toList().blockingGet()
        println(blockingGet)
        val gson = Gson()
        return Response.ok(gson.toJson(blockingGet)).build()
    }

    @GET
    @Path("/async1")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    fun loadCarsAsOctetStream(): Response {
        return Response.ok().entity(responseStream).build()
    }

    @GET
    @Path("/async2")
    @Produces("application/stream+json")
    fun loadCarsAsJsonStream(): Response {
        return Response.ok().entity(responseStream).build()
    }
}
