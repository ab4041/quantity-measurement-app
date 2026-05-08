package com.quantity.repository;

import com.quantity.entity.QuantityMeasurementEntity;

import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementCacheRepository
        implements IQuantityMeasurementRepository {

    private static QuantityMeasurementCacheRepository instance;

    private List<QuantityMeasurementEntity> cache =
            new ArrayList<>();

    private QuantityMeasurementCacheRepository() {}

    public static QuantityMeasurementCacheRepository getInstance() {

        if (instance == null)
            instance =
                    new QuantityMeasurementCacheRepository();

        return instance;
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {

        cache.add(entity);
    }
}