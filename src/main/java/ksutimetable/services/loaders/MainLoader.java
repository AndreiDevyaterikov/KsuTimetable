package ksutimetable.services.loaders;

import ksutimetable.models.ResponseModel;
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
    private final TimetableLoader timetableLoader;
    public ResponseModel loadAllDataToDatabase(){
        buildingLoader.loadData();
        cabinetLoader.loadData();
        userLoader.loadData();
        facultyLoader.loadData();
        directionLoader.loadData();
        groupLoader.loadData();
        timetableLoader.loadData();
        return new ResponseModel(200, "Данные успешно загружены");
    }
}
