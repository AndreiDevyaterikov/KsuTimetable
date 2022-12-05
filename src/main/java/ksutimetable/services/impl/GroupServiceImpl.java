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
    public void saveGroups(List<Group> groups) {
        groupRepository.saveAll(groups);
    }

    @Override
    public Group getGroupByName(String groupName) {
        var groupOpt = groupRepository.findGroupByTitle(groupName);
        if (groupOpt.isPresent()) {
            return groupOpt.get();
        } else {
            var message = String.format(Constants.NOT_FOUND_GROUP_WITH_NAME, groupName);
            log.info(message);
            throw new KsuTimetableException(message, 404);
        }
    }

    @Override
    public List<Group> getGroups() {
        return groupRepository.findAll();
    }

    @Override
    public List<Group> getGroupsByDirectionId(String directionId) {
        var direction = directionService.getDirectionById(directionId);
        return groupRepository.findAllByDirection(direction);
    }
}
