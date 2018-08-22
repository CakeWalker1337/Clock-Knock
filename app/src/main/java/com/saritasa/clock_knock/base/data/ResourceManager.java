package com.saritasa.clock_knock.base.data;

/**
 * Interface of resource manager.
 */
public interface ResourceManager{

    /**
     * Gets string resource by id and params.
     *
     * @param aResourceId resource id.
     * @param aParams other params.
     * @return String by resource id.
     */
    String getStringResource(int aResourceId, Object... aParams);

}
