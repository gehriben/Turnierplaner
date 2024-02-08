package ch.zhaw.psit4.turnier.persistence.controller;

import ch.zhaw.psit4.turnier.model.Encounter;
import ch.zhaw.psit4.turnier.model.Group;
import ch.zhaw.psit4.turnier.persistence.service.BaseService;
import ch.zhaw.psit4.turnier.persistence.service.EncounterService;
import ch.zhaw.psit4.turnier.persistence.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("groups")
public class GroupController extends BaseRestController<Group, Integer> {

    @Autowired
    private GroupService groupService;

    @Autowired
    private EncounterService encounterService;

    @Override
    public BaseService<Group, Integer> getService() {
        return groupService;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, path = "/{group_id}/encounters")
    public List<Encounter> getAllEncounters(@PathVariable(value = "group_id") Integer groupId) throws Exception {
        return encounterService.getAllEncountersForGroup(groupId);
    }
}
