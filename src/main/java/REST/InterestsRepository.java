package REST;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class InterestsRepository {
	List<Interests> list=new ArrayList<Interests>(); 
	////////////////////////////////////////
	void setInterests(Interests interests) {
		list.add(interests);
		
	}
	Interests getInterestsById(int userId) {
		if(userId<list.size())
			return list.get(userId);
		return null;
	}
	boolean checkIfDuplicates(int userId) {
		boolean check=false;
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getUserId()==userId)
				check=true;
		}
		return check;
	}
	///////////////////////////////////////////////////////////////
	void updateSpecifiedUserInterests(int userId, String subject, String sport) {
		if(!list.isEmpty()) {
			for(int i=0;i<list.size();i++) {
				if(list.get(i).getUserId()==userId) {
					list.get(i).setSubject(subject);
					list.get(i).setSport(sport);
				}
			}
		}
	}
	///////////////////////////////////////////////////////////////////////////
	Interests getSpecifiedUser(int userId) {
		Interests value=null;
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getUserId()==userId)
				value=list.get(i);
		}
	return value;
	}
	/////////////////////////////////////////////////////////////////////////
	void deleteSpecifiedUser(int userId) {
		int i;
		boolean check=false;	
		for(i=0;i<list.size();i++) {
			if(list.get(i).getUserId()==userId) {
				list.remove(i);
				check=true;
				break;
			}	
		}
		if(check) {
			while(i<list.size()) {
				list.get(i).userId--;
				i++;
			}
		}
	}
	/////////////////////////////////////////////////////////////////////////
}