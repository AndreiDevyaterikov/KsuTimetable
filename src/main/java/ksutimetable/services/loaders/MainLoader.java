package ksutimetable.services.loaders;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MainLoader {

    private final BuildingLoader buildingLoader;
    private final CabinetLoader cabinetLoader;
    private final UserLoader userLoader;
    private final FacultyLoader facultyLoader;
    private final DirectionLoader directionLoader;
    private final GroupLoader groupLoader;
    public void loadAllDataToDatabase(){
        buildingLoader.loadData();
        cabinetLoader.loadData();
        userLoader.loadData();
        facultyLoader.loadData();
        directionLoader.loadData();
        groupLoader.loadData();
    }
}
