
# spring + hibernate 调用存储过程
此方式调用存储过程的方法有很多种
在java代码中给出了三种方式

## 还有几种没测试的方式
## 1. NamedNativeQuery in annotation
Declare your store procedure inside the @NamedNativeQueries annotation.
```Java
//Stock.java
...
@NamedNativeQueries({
	@NamedNativeQuery(
	name = "callStockStoreProcedure",
	query = "CALL GetStocks(:stockCode)",
	resultClass = Stock.class
	)
})
@Entity
@Table(name = "stock")
public class Stock implements java.io.Serializable {
...
Call it with getNamedQuery().

Query query = session.getNamedQuery("callStockStoreProcedure")
	.setParameter("stockCode", "7277");
List result = query.list();
for(int i=0; i<result.size(); i++){
	Stock stock = (Stock)result.get(i);
	System.out.println(stock.getStockCode());
}
```
## 2. sql-query in XML mapping file
Declare your store procedure inside the "sql-query" tag.
```Java
<!-- Stock.hbm.xml -->
...
<hibernate-mapping>
    <class name="com.mkyong.common.Stock" table="stock" ...>
        <id name="stockId" type="java.lang.Integer">
            <column name="STOCK_ID" />
            <generator class="identity" />
        </id>
        <property name="stockCode" type="string">
            <column name="STOCK_CODE" length="10" not-null="true" unique="true" />
        </property>
        ...
    </class>

    <sql-query name="callStockStoreProcedure">
	<return alias="stock" class="com.mkyong.common.Stock"/>
	<![CDATA[CALL GetStocks(:stockCode)]]>
    </sql-query>

</hibernate-mapping>
```

## 3. When using the @NamedStoredProcedureQuery annotation, we can specify parameters using the @StoredProcedureParameter annotation:
```Java
@NamedStoredProcedureQuery(
  name="GetFoosByName",
  procedureName="GetFoosByName",
  resultClasses = { Foo.class },
  parameters={
    @StoredProcedureParameter(name="fooName", type=String.class, mode=ParameterMode.IN)
  }
)
We can make use of the registerStoredProcedureParameter() method to call our stored procedure with the fooName parameter:


StoredProcedureQuery spQuery = entityManager.
  createNamedStoredProcedureQuery("GetFoosByName")
  .registerStoredProcedureParameter(
    "New Foo", 
    String.class , 
    ParameterMode.IN
  );
  ```
