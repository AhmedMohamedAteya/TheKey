package apps.pixel.the.key.activites.retaurant.jobs.applicationJob;

import java.util.List;

import apps.pixel.the.key.models.cities.cityModel;
import apps.pixel.the.key.models.retaurants.SelectedCat;

public interface ApplicationJobInterface {
    void getCities(List<cityModel> cityModels);
}
