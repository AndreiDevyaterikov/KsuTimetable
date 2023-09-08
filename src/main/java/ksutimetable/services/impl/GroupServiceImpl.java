package ksutimetable.services.impl;

import ksutimetable.constants.Constants;
import ksutimetable.entities.Group;
import ksutimetable.exceptions.KsuTimetableException;
import ksutimetable.repositories.GroupRepository;
import ksutimetable.services.DirectionService;
import ksutimetable.services.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final DirectionService directionService;

    @Override
    public void saveGroup(Group group) {
        groupRepository.save(group);
    }

    @Override
    public Group getGroupById(String groupId) {
        return groupRepository
                .findById(groupId)
                .orElseThrow(() -> {
                    var message = String.format(Constants.NOT_FOUND_GROUP_WITH_ID, groupId);
                    log.info(message);
                    return new KsuTimetableException(message, 404);
                });
    }

    @Override
    public List<Group> getGroupsByDirectionId(String directionId) {
        var direction = directionService.getDirectionById(directionId);
        return groupRepository.findAllByDirection(direction);
    }
}
