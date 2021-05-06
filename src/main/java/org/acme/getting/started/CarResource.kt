package org.acme.getting.started

import com.google.gson.Gson
import org.acme.getting.started.model.Car
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
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    fun getCarsAsList() : Response {
        val blockingGet = DataService.getDataStream(0).toList().blockingGet()
        println(blockingGet)
        return Response.ok(blockingGet).build()
    }

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    fun loadCarsAsOctetStream(): Response {
        return Response.ok().entity(responseStream).build()
    }

    @GET
    @Produces("application/stream+json")
    fun loadCarsAsJsonStream(): Response {
        return Response.ok().entity(responseStream).build()
    }
}
