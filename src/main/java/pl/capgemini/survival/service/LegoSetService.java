package pl.capgemini.survival.service;

import java.util.List;

import org.springframework.stereotype.Service;
import pl.capgemini.survival.common.to.SearchCriteriaTo;
import pl.capgemini.survival.common.to.SearchResultTo;
import pl.capgemini.survival.common.typ.Condition;
import pl.capgemini.survival.common.typ.Status;
import pl.capgemini.survival.persistence.entity.LegoSet;
@Service
public interface LegoSetService {

	LegoSet createLegoSet(LegoSet legoSet);

	LegoSet findLegoSet(long legoSetId);

	LegoSet updateLegoSet(LegoSet legoSet);

	void deleteLegoSet(long id);

	List<LegoSet> findAllLegoSets();

	SearchResultTo searchLegoSets(SearchCriteriaTo searchCriteria);

	List<LegoSet> findByLegoStatusAndLegoCondition(Status s, Condition c);

	List<LegoSet> findByLegoNameStartsWith(String name);


}
