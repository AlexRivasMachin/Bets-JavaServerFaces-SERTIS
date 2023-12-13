package dev.sertis.betsjsf.dao;

import dev.sertis.betsjsf.domain.Forecast;

public interface ForecastDAO {

    void save(Forecast forecast);

    void update(Forecast forecast);

    void delete(Forecast forecast);

    Forecast getForecastById(Long forecastId);
}
