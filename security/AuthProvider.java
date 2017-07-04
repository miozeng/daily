package com.fwd.eprecious.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fwd.eprecious.config.ServerSetting;
import com.fwd.eprecious.dto.RestrictedAction;
import com.fwd.eprecious.dto.UserSession;

@Component
public class AuthProvider implements AuthenticationProvider{


	private final Log logger = LogFactory.getLog(AuthenticationProvider.class);
	@Autowired
	private ServerSetting serverSetting;
	
	@Autowired
	private RestTemplate simpleRestTemplate;
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		  String username = auth.getName();
		    String password = auth.getCredentials().toString();
		    UserSession user = simpleRestTemplate.getForObject(serverSetting.getUrl()+"/eprecious-ws/Login/user/"+username+"/"+password, UserSession.class);
			
			if (user == null) {
				logger.error(" find user from DB by username is null");
				return null;
			} else {
				logger.info("find user from DB by username success ");
				user.setUsername(username);
				user.setPassword(password);
				List<RestrictedAction> actions  = new ArrayList<RestrictedAction>();
				List<HashMap<String,Object>> actions2 = simpleRestTemplate.getForObject(serverSetting.getUrl()+"/eprecious-ws/Login/findUserActions/"+user.getUsergroup(), List.class);
				for (HashMap<String, Object> hashMap : actions2) {
					RestrictedAction raction = new RestrictedAction();
					if( hashMap.get("action") != null){
						String action = (String) hashMap.get("action");
						raction.setAction(action);
					}
					if( hashMap.get("userGroup") != null){
						String userGroup = (String) hashMap.get("userGroup");
						raction.setUserGroup(userGroup);
					}
					if( hashMap.get("newable") != null){
						Boolean newable = (Boolean) hashMap.get("newable");
						raction.setNewable(newable);
					}
					if( hashMap.get("editable") != null){
						Boolean  editable = (Boolean) hashMap.get("editable");
						raction.setEditable(editable);
					}
					if( hashMap.get("mode") != null){
						String mode = (String) hashMap.get("mode");
						raction.setMode(mode);
					}
					
					actions.add(raction);
				}
				UserSession us = new UserSession(user.getUserid(), username, user.getUsergroup(), password, actions);
				Authentication token = new UsernamePasswordAuthenticationToken(us, null, us.getAuthorities());
			    return token;
			}
		
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
