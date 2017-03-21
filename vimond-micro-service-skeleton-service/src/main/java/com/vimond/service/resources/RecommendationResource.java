package com.vimond.service.resources;

import com.vimond.resources.datastructures.Asset;
import com.vimond.service.engine.SearchEngine;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Thelo on 21/03/2017.
 */
@Path("/recommend")
@Produces(MediaType.APPLICATION_JSON)
public class RecommendationResource {

    private final SearchEngine engine;

    public RecommendationResource(SearchEngine engine){
        this.engine = engine;
    }


    /**
     * Propose API, feel free to add some filters:
     * for example you could :
     * - include or exclude categories
     * - Remove every video with the word "Trump" inside and have a bright start of day.
     * - Look for only video that have a title containing "puppies" insides.
     *
     * @param timeBeforeSchool
     * @return
     */
    @GET
    @Path("/{timeLeft}")
    public List<Asset> giveMeSuperOptimizedPlaylist(@PathParam("timeLeft") int timeBeforeSchool){

        /*
         * You could add some check here to see whether the time is positive,
         * if its a school days, ....
         * Then call your search algorithm
         */

        return this.engine.findOutSuperOptimizedPlaylist(timeBeforeSchool);
    }
}
