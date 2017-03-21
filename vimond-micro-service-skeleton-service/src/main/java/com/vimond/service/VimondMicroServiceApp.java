package com.vimond.service;

import com.vimond.service.client.AssetClient;
import com.vimond.service.client.CategoriesClient;
import com.vimond.service.configuration.VimondEndPointConfiguration;
import com.vimond.service.engine.AssetModel;
import com.vimond.service.engine.SearchEngine;
import com.vimond.service.health.ServiceHealthCheck;
import com.vimond.service.resources.RecommendationResource;
import com.vimond.service.resources.SampleResource;
import io.dropwizard.Application;
import com.vimond.service.configuration.VimondMicroServiceConfiguration;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;

/**
 * Created by Thelo on 11/10/16.
 */
public class VimondMicroServiceApp extends Application<VimondMicroServiceConfiguration> {

    private static final Logger LOG = LoggerFactory.getLogger(VimondMicroServiceApp.class);


    public static void main(String[] args) {
            try {
                new VimondMicroServiceApp().run(args);
            } catch (Exception e){
                LOG.error("Error while running micro service : " + e.getMessage());

            }
        }

        public String getName(){
            return "vimond-micro-service";
        }

        public void initialize(Bootstrap<VimondMicroServiceConfiguration> bootstrap){
        }

        public void run(VimondMicroServiceConfiguration configuration,
                        Environment environment){

            // Create health check
            final ServiceHealthCheck healthCheck =
                    new ServiceHealthCheck();
            environment.healthChecks().register("vimond-micro-service", healthCheck);

            // Create http client to query Vimond's micro services
            VimondEndPointConfiguration endpoint = configuration.getVimondEndPointConfiguration();
            final Client client = new JerseyClientBuilder(environment).build("Rest client");
            CategoriesClient catClient = new CategoriesClient(client, endpoint.getEndpoint(), endpoint.getPort());
            AssetClient assetClient = new AssetClient(client, endpoint.getEndpoint(), endpoint.getPort(), catClient);
            environment.jersey().register(catClient);
            environment.jersey().register(assetClient);

            // Create model needed
            AssetModel assetModel = new AssetModel(assetClient, catClient);
            SearchEngine engine = new SearchEngine(assetModel);

            // Create resources
            final SampleResource assetResource = new SampleResource(assetModel);
            final RecommendationResource recommendationResource =
                    new RecommendationResource(engine);

            // Add resources to Dropwizard
            environment.jersey().register(assetResource);
            environment.jersey().register(recommendationResource);

        }
}