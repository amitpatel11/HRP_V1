package com.hrp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrp.dao.UserDao;
import com.hrp.dto.UserRegistrationDTO;
import com.hrp.model.Answer;
import com.hrp.model.User;
import com.hrp.model.UserProfile;

@Service("userService")
@Transactional("transactionManager")
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	 public List<User> loginUser(String username){
		return userDao.loginUser(username);
	}
 
	@Transactional(rollbackFor=Exception.class)	
	@Override
	 public void registerUser(UserRegistrationDTO userRegistrationDTO){

		 		UserProfile userProfile = new UserProfile();
		 		userProfile.setCreatedDate(new Date());
		 		userProfile.setDeletedYn(false);
		 		userProfile.setFirstName(userRegistrationDTO.getUserProfile().getFirstName());
		 		userProfile.setLastName(userRegistrationDTO.getUserProfile().getLastName());
		 		userProfile.setMobile(userRegistrationDTO.getUserProfile().getMobile());
		 		
				User user=new User();
				user.setCreatedDate(new Date());
				user.setDeletedYn(false);
				user.setEmail(userRegistrationDTO.getUser().getEmail());
				user.setUserProfile(userProfile);
				
				Long userId = userDao.saveUser(user);
				
				Answer answer=null;
				List<Answer> answerList=new ArrayList<Answer>();
			
				for (Answer answers : userRegistrationDTO.getAnswers()) {
					answer=new Answer();
					answer.setCreatedDate(new Date());
					answer.setDeletedYn(false);
					answer.setQuestionId(answers.getQuestionId());
					answer.setUserId(userId);
					answer.setAnswer(answers.getAnswer());
					answerList.add(answer);
				} 
		 
				userDao.registerUser(answerList);
	 }

	@Override
	public Long isUser(String email) {
		return userDao.isUser(email);
	}

	@Override
	public Boolean checkAnswer(Answer answer) {
		return userDao.checkAnswer(answer) ;
	}

	@Override
	public User getUserById(Long id) {
		return (User)userDao.get(User.class, id);
	}

	@Override
	public List<User> selectProviders( Long serviceId,Long roleId,Integer experience) {

		return userDao.selectProviders(serviceId, roleId, experience) ;
	}

	@Override
	public List<User> selectProvidersBasedOnExperience(Long serviceId, Long roleId, ArrayList<Long> idList) {
		
		return userDao.selectProvidersBasedOnExperience(serviceId, roleId, idList);
	}

}
