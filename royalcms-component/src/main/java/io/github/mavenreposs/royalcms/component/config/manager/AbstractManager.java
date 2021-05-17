package io.github.mavenreposs.royalcms.component.config.manager;

import io.github.mavenreposs.royalcms.component.config.contracts.ConfigItemRepositoryInterface;

import java.util.ArrayList;

public abstract class AbstractManager implements ConfigItemRepositoryInterface {

    /**
     * The config repository implementation.
     */
    protected ConfigItemRepositoryInterface repository;

    /**
     * Create a new config instance.
     *
     * @param configItemRepository ConfigItemRepositoryInterface
     */
    public AbstractManager(ConfigItemRepositoryInterface configItemRepository) {
        this.repository = configItemRepository;
    }

    /**
     * Get the config repository instance.
     */
    public ConfigItemRepositoryInterface getRepository() {
        return this.repository;
    }

    /**
     * Get the all codes.
     *
     * @return array
     */
    public ArrayList<String> allKeys() {
        return this.getRepository().allKeys();
    }

    /**
     * Clean the caches.
     */
    public Boolean clearCache() {
        return this.getRepository().clearCache();
    }

}
