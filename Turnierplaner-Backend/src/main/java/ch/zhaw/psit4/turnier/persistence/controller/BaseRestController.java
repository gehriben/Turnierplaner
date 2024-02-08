package ch.zhaw.psit4.turnier.persistence.controller;

import ch.zhaw.psit4.turnier.persistence.service.BaseService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
public abstract class BaseRestController<T, ID> {

    public abstract BaseService<T, ID> getService();

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public T createObject(@RequestBody T object) {
        return getService().createObject(object);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Iterable<T> getAllObjects() {
        return getService().getAllObjects();
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET, produces = "application/json", path = "/{id}")
    public T getObjectById(@PathVariable(value = "id") ID id) throws Exception {
        return getService().getObjectById(id);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.PUT, produces = "application/json", path = "/{id}")
    @ResponseBody
    public T updateObject(@PathVariable(value = "id") ID id, @RequestBody T object) {
        return getService().updateObject(object);
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public void deleteById(@PathVariable(value = "id") ID id) {
        getService().deleteById(id);
    }
}
