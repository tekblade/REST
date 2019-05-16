package REST;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class MyRestController {
	public static final Logger logger = LoggerFactory.getLogger(MyRestController.class);
	UserRepository userRepository=new UserRepository();
	InterestsRepository interestsRepository=new InterestsRepository();
	@RequestMapping("/getuniqueuserid")  //This content would be used by for ex. CMS(MVC).  
	public HttpEntity<String> handle() {     //This is specified communication between MVC and Rest 
		userRepository.addUser();
		return new HttpEntity<String>("New user's ID in the system:   " + userRepository.getLastCreatedId());
	}
	@RequestMapping(value="/setspecifieduserinterests", method = RequestMethod.POST)
	public HttpEntity<String> setspecifieduserinterests(@RequestBody Interests asJson){
		if(interestsRepository.checkIfDuplicates(asJson.getUserId()))
			return new HttpEntity<String>("Możliwe jest stworzenie tylko jednego obiektu dla danego identyfikatora użytkownika");
		interestsRepository.setInterests(asJson);
		logger.info("---------------------------------------------");
		logger.info("added: ");
		logger.info("USERID: "+asJson.getUserId()+" Favourite Sport: "+asJson.getSport()+" Favourite Subject: "+asJson.getSubject());
		logger.info("---------------------------------------------");
		logger.info("for check: "+interestsRepository.getInterestsById(0).getSubject());
		logger.info("---------------------------------------------");
		return new HttpEntity<String>("Dane zostały zapisane");
	}
	@RequestMapping(value="/updateinterests", method = RequestMethod.PUT)
	public HttpEntity<String> updatespecifieduserinterests(@RequestBody Interests asJson){	
		if(asJson.getUserId() < interestsRepository.list.size()) {
			interestsRepository.updateSpecifiedUserInterests(asJson.getUserId(), asJson.getSubject(), asJson.getSport());
			logger.info("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
			logger.info("updated: ");
			logger.info("for check,subject: "+interestsRepository.getInterestsById(0).getSubject()+" sport: "+interestsRepository.getInterestsById(0).getSport());
			logger.info("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
			return new HttpEntity<String>("Dane dla id: "+ asJson.getUserId() + "  zostały zaktualizowane");				
		}
		else
			return new HttpEntity<String>("Dla danego id użytkownik nie istnieje");	
	}
	@RequestMapping(value="/readspecifieduser/{userId}", method = RequestMethod.GET)
	public HttpEntity<String> readspecifieduser(@PathVariable int userId){
		Interests example=interestsRepository.getSpecifiedUser(userId);
		if(example==null)
			return new HttpEntity<String>("nie istnieje");
		return new HttpEntity<String>("dla podanego id przedmiot szkolny: "+example.getSubject()+" sport: "+example.getSport());
	}
	@RequestMapping(value="/deletespecifieduser/{userId}", method = RequestMethod.DELETE)
	public HttpEntity<String> deletespecifieduser(@PathVariable int userId){
		interestsRepository.deleteSpecifiedUser(userId);
		userRepository.usersCounter--;
		return new HttpEntity<String>("użytkownik o id "+userId+" został usuniety jeśli dane są poprawne jeśli nie to nie");
	}

}
