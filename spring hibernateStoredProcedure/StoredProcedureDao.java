package com.fwd.eprecious.dao;

import java.util.List;
import java.util.Map;

public interface StoredProcedureDao {
	public List<Object> find_procedure(final String sql,final Object[] params,final Class c);
	public void testPro();
	 public List<List<Map<String, Object>>> find_procedure_multi(final String sql,final Object[] params);
}
