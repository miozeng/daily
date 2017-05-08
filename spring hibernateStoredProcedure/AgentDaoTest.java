package com.fwd.eprecious.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fwd.eprecious.entity.GroupUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application/application-config-hibernate.xml")
public class AgentDaoTest {
	
	/**/
	@Resource
	private StoredProcedureDao spDao;
	
	@Test
	@Transactional
	public void testPro2(){
		List<Object> ol = spDao.find_procedure("{CALL  SP_findAllUser(?)}",new Object[]{"12"},GroupUser.class);
		for (Object object : ol) {
			if(object instanceof GroupUser){
				GroupUser u = (GroupUser) object;
				System.out.println(u.getDisplayName());
			}
		}
	}
	@Test
	@Transactional
	public void testSave(){
		List<List<Map<String, Object>>>  aa = spDao.find_procedure_multi("declare @result varchar(100)='' exec [dbo].[SP_Test] ?,?, @result OUTPUT select @result", new Object[]{"12",1});
//		List<List<Map<String, Object>>>  bb = agentDao.find_procedure_multi("{CALL SP_Test(?,?)}", new Object[]{"12",1});

		System.out.println("aa");
	}
	
	
	
	
	@Test
	@Transactional
	public void testPro(){
		spDao.testPro();
		System.out.println("aa");
	}
	

	
	@Test
	public void testaa(){
		System.out.println("aa");
	}
}
