package com.vimond.service.engine;

import com.vimond.resources.datastructures.Asset;
import com.vimond.service.client.AssetClient;
import com.vimond.service.client.CategoriesClient;

import java.util.List;

/**
 * Created by Thelo on 21/03/2017.
 */
public class AssetModel {

    private final AssetClient assetClient;
    private final CategoriesClient categoriesClient;

    private List<Asset> assetInMemory;

    public AssetModel(AssetClient assetClient, CategoriesClient categoriesClient){
        this.assetClient = assetClient;
        this.categoriesClient= categoriesClient;
        initAsset();
    }

    private void initAsset(){
        this.assetInMemory = assetClient.getAllAsset();
    }

    public List<Asset> listAsset(/* filters here if needed */) {

        /**
         * Complete code here if needed
         */

        return assetInMemory;
    }

    /**
     * Tips: Since you need to in a possibly large list of asset by Id you are encouraged to make a new data structure
     * to be more efficient.
     */
    public Asset getAsset(int assetId){
        return null;
    }

    public int createAsset(Asset asset) {
        /**
         * Complete code here
         */
        return -1;
    }

    public void updateAsset() {
        /**
         * Complete code here
         */
    }

    public void deleteAsset(int assetId) {
        /**
         * Complete code here
         */
    }
}
