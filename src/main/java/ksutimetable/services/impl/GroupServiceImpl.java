package ksutimetable.services.impl;

import ksutimetable.entities.Group;
import ksutimetable.repositories.GroupRepository;
import ksutimetable.services.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Override
    public void saveGroups(List<Group> groups) {
        groupRepository.saveAll(groups);
    }

    @Override
    public List<Group> getGroups() {
        return groupRepository.findAll();
    }
}
