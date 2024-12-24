package general.springboothomework.controller;

import general.springboothomework.entity.Group;
import general.springboothomework.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @PostMapping
    public Group createGroup(@RequestBody Group group) {
        return groupRepository.save(group);
    }

    @GetMapping("/{name}")
    public Group getGroupByName(@PathVariable String name) {
        return groupRepository.findByName(name);
    }
}