package com.vimond.service.resources;

import com.vimond.resources.datastructures.Asset;
import com.vimond.service.engine.AssetModel;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Thelo on 10/10/16.
 */

/**
 * Dead brain API sample to show one example of How to create Dropwizard API.
 * Since the entire class path is "sample" all API call will be done starting with the keyword "sample".
 */
@Path("/sample")
@Produces(MediaType.APPLICATION_JSON)
public class SampleResource {

    private static final Logger LOG = LoggerFactory.getLogger(SampleResource.class);

    private AssetModel assetModel;

    public SampleResource(AssetModel assetModel){
        this.assetModel = assetModel;
    }


    /**
     * GET is typically a read-only request.
     *
     * You can see that the path of the function is "asset".
     * Hence to call this function you will have to use
     * curl -XGET http://localhost:8084/sample/asset
     *
     * The function also has a QueryParam "anOption", this parameter is optional and can be set directly in the call such as
     * curl -XGET http://localhost:8084/sample/asset?anOption=blabla
     *
     * Then anOption will take the value "blabla".
     *
     * As the Asset class has been annotated, Dropwizard knows how to convert the returned list of asset into a proper Json file.
     *
     * Typically, this request could return all of the existing assets that match some filter passed as QueryParam
     */
    @GET
    @Path("/asset")
    public List<Asset> listAllAssets(@QueryParam("anOption") String anOption){
        LOG.debug("List all assets.");
        return this.assetModel.listAsset();
    }

    /**
     * Here we see a new type of parameter. PathParam are parameters that are located in the URL directly.
     *
     * For example, this function could return a single asset information using this call:
     * curl -XGET http://localhost:8084/sample/asset/1234
     * Where 1234 would be the id of the asset.
     *
     * @param assetId id of the asset
     * @return an Asset description
     */
    @GET
    @Path("/asset/{id}")
    public Asset getAsset(@PathParam("id") int assetId) {
        LOG.debug("Get asset with id {}.", assetId);
        return this.assetModel.getAsset(assetId);
    }


    /**
     * Post is usually used to create new Data, an example would be an user POST-ing a new asset,
     * This asset will be store and affected an id.
     * This id will be returned afterward
     *
     * You could find an example of how to make this call using the command specified in the file:
     * - resource/api_call_examples/post_asset
     */
    @POST
    @Path("/asset")
    public int createAsset(Asset asset) {
        return this.assetModel.createAsset(asset);
    }

    /**
     * Put has multiples purpose that can be confusing ( even more since the REST norm added some new method)
     * In general you will use put to:
     * - Update an asset you know the id of. ( PUT is indempotent so doing such request 1 time or 1000 times shouldnt change anything)
     * - Create an asset with a specific id.
     *
     * You are free to design this API, an easy choice would be for the function to take an entire Asset as a parameter, but you might also
     * choose to send only the change that you want to apply to an asset
     */
    @PUT
    @Path("/asset/{id}")
    public void updateAsset(@PathParam("id") int id) {

    }


    /**
     * DELETE method is usually used to remove permanently some information from the system.
     * Here we can use it to remove some assets metadata from the model, it can be used as such:
     * curl -XDELETE http://localhost:8084/sample/asset/1234
     * where 1234 is the id of the asset to delete.
     */
    @DELETE
    @Path("/asset/{id}")
    public void deleteAsset(@PathParam("id") int assetId){
        this.assetModel.deleteAsset(assetId);
    }

}
