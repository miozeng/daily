package com.fwd.eprecious.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ParameterMode;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.hibernate.procedure.ProcedureCall;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class StoredProcedureDaoImpl extends HibernateDaoSupport implements  StoredProcedureDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> find_procedure(final String sql,final Object[] params,final Class c) {
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql).addEntity(c);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		List<Object> list = query.list();
		return list;
	 
	}

	/**
     * @param sql 
     * @param params
     * @return
     */
    public List<List<Map<String, Object>>> find_procedure_multi(final String sql,final Object[] params){
        final List<List<Map<String, Object>>> result = new ArrayList<List<Map<String, Object>>>();
        try {
        	Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
            session.doWork(new Work(){
                public void execute(Connection conn) throws SQLException {
                    CallableStatement cs=null;
                    ResultSet rs=null;
                    cs = conn.prepareCall(sql);
                    for(int i=1;i<=params.length;i++){
                        cs.setObject(i, params[i-1]);
                    }
                    boolean hadResults = cs.execute();
                    ResultSetMetaData metaData = null;
                    while(hadResults){
                        List<Map<String, Object>> rsList = new ArrayList<Map<String, Object>>();
                        rs = cs.getResultSet();
                        metaData=rs.getMetaData();
                        int colCount=metaData.getColumnCount();
                        while(rs.next()){
                            Map<String, Object> map = new HashMap<String, Object>();
                            for(int i=1;i<=colCount;i++){
                                String colName=metaData.getColumnName(i);
                                map.put(colName, rs.getObject(colName));
                            }
                            rsList.add(map);
                        }
                        result.add(rsList);
                        hadResults=cs.getMoreResults();
                    }
                }
            });
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	@Override
	public void testPro() {

		
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		ProcedureCall procedureCall = session.createStoredProcedureCall("SP_Test");
		procedureCall.registerParameter("QuotationNo", String.class, ParameterMode.IN).bindValue("12") ;
		procedureCall.registerParameter("QuottaionVer", Integer.class,  ParameterMode.IN).bindValue(1) ;
		procedureCall.registerParameter("outValue", String.class,  ParameterMode.OUT);
//		procedureCall.registerParameter(1, String.class, ParameterMode.IN).bindValue("12") ;
//		procedureCall.registerParameter(2, Integer.class,  ParameterMode.IN).bindValue(1) ;
//		procedureCall.registerParameter(3, String.class,  ParameterMode.OUT);
//		procedureCall.registerParameter(4, Class.class, ParameterMode.REF_CURSOR);
		String recipients_id = (String)procedureCall.getOutputs().getOutputParameterValue("outValue");
		System.out.println(recipients_id);

	}
}
