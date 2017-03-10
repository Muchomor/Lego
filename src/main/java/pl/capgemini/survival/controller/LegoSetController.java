package pl.capgemini.survival.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import pl.capgemini.survival.common.mapper.LegoSetMapper;
import pl.capgemini.survival.common.to.LegoSetTo;
import pl.capgemini.survival.common.typ.Condition;
import pl.capgemini.survival.common.typ.Status;
import pl.capgemini.survival.persistence.entity.LegoSet;
import pl.capgemini.survival.service.LegoSetService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/survival")
public class LegoSetController {

	@Autowired
	private LegoSetService legoSetService;

	@Autowired
	private LegoSetMapper mapper;

	// TODO TASK 1.0 - Hello World po springowemu - napisac metodę restową
	// zwracającą Hello World
	@RequestMapping(path = "/hello", method = RequestMethod.GET)
	public String helloWorld(){
		return "Hello World";
	}

	// TODO TASK 1.1 - Napisać metody restowe pod CRUD zgodnie z konwencją
	// opisaną w wykładach
	@RequestMapping(path = "/legos", method = RequestMethod.GET)
	public List<LegoSetTo> findLegos(){
		List <LegoSet> list = new ArrayList<>(legoSetService.findAllLegoSets());
		List <LegoSetTo> listToReturn = new ArrayList<>();
		for(LegoSet ls: list){
			listToReturn.add(mapper.mapToLegoSetTo(ls));
		}
		return listToReturn;
	}

	@RequestMapping(path = "/lego", method = RequestMethod.POST)
	public LegoSetTo addLego(@RequestBody LegoSetTo lst){
		LegoSet ls = mapper.mapToLegoSet(lst);
		legoSetService.createLegoSet(ls);
		LegoSetTo toReturn = mapper.mapToLegoSetTo(ls);
		return toReturn;
	}



	@RequestMapping(path = "/lego", method = RequestMethod.PUT)
	public LegoSetTo updateLego(@RequestBody LegoSetTo lst){
		LegoSet ls = mapper.mapToLegoSet(lst);
		legoSetService.updateLegoSet(ls);
		LegoSetTo toReturn = mapper.mapToLegoSetTo(ls);
		return toReturn;
	}


	@RequestMapping(path = "/lego/{id}", method = RequestMethod.DELETE)
	public void deleteLego(@PathVariable ("id") long id){
		LegoSet ls = legoSetService.findLegoSet(id);
		if(ls==null) throw new LegoNotFoundException();
		else legoSetService.deleteLegoSet(id);
	}

	// TODO TASK 1.2 - Otestować metody CRUD używając Postmana
	//DONE
	//get ok
	//post ok
	//put ok
	//delete ok

	@RequestMapping(path = "/condition", method = RequestMethod.GET)
	public List<LegoSetTo> conditionTest(){
		List<LegoSet> ls = legoSetService.findByStatusAndCondition(Status.OWNED, Condition.NEW);
		List<LegoSetTo> toReturn = new ArrayList<>();
		for(LegoSet legoSet: ls) toReturn.add(mapper.mapToLegoSetTo(legoSet));
		return toReturn;
	}

}

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such lego")  // 404
class LegoNotFoundException extends RuntimeException {
	public LegoNotFoundException(){
		super();
	}
}
