package com.vimond.service.engine;

import com.vimond.resources.datastructures.Asset;
import com.vimond.service.client.AssetClient;
import com.vimond.service.client.CategoriesClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Thelo on 18/10/16.
 */
public class SearchEngine {

    AssetModel model;

    public SearchEngine(AssetModel model){
        this.model = model;
    }

    public List<Asset> findOutSuperOptimizedPlaylist(int timeBeforeSchool){
        // Add some logic here.
        return Collections.emptyList();
    }

}
