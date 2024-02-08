package ch.zhaw.psit4.turnier.persistence.controller;

import ch.zhaw.psit4.turnier.model.GamePoints;
import ch.zhaw.psit4.turnier.model.GameSet;
import ch.zhaw.psit4.turnier.persistence.service.BaseService;
import ch.zhaw.psit4.turnier.persistence.service.GameSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gamesets")
public class GameSetController extends BaseRestController<GameSet, Integer> {

    @Autowired
    private GameSetService service;

    @Override
    public BaseService<GameSet, Integer> getService() {
        return service;
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, path = "/{gameset_id}/encountertournamentparticipant/{etp_id}/gamepoints")
    public GamePoints addGamePoints(@PathVariable(value = "gameset_id") Integer gameset_id, @PathVariable(value = "etp_id") Integer etp_id, @RequestBody Integer points) throws Exception {
        return service.addGamepoints(gameset_id, etp_id, points);
    }
    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, path = "/{gameset_id}/encountertournamentparticipant/{etp_id}/gamepoints")
    public GamePoints changeGamePoints(@PathVariable(value = "gameset_id") Integer gameset_id, @PathVariable(value = "etp_id") Integer etp_id, @RequestBody Integer points) throws Exception {
        return service.changeGamepoints(gameset_id, etp_id, points);
    }
}
