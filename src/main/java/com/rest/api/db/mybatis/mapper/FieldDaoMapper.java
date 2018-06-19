package com.rest.api.db.mybatis.mapper;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.rest.api.model.Field;

@Mapper
public interface FieldDaoMapper extends Serializable {

	@Select(  "SELECT * "
			+ "FROM FIELD "
			+ "WHERE id = #{id}")
	public Field getField(BigInteger id);
	
	@Insert(  "INSERT INTO FIELD (F_STRING, F_SHORT, F_INT, F_LONG, F_FLOAT, F_DOUBLE, F_BIGDECIMAL, F_CLOB, F_BLOB, F_DATE, F_TIME, F_TIMESTAMP) "
			+ "VALUES (#{fString}, #{fShort}, #{fInt}, #{fLong}, #{fFloat}, #{fDouble}, #{fBigDecimal}, #{fClob}, #{fBlob}, #{fDate}, #{fTime}, #{fTimestamp})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void addField(Field field);
	
	@Update(  "UPDATE FIELD "
			+ "SET F_STRING = #{field.fString},"
			+ "    F_SHORT = #{field.fShort}, "
			+ "    F_INT = #{field.fInt}, "
			+ "    F_LONG = #{field.fLong}, "
			+ "    F_FLOAT = #{field.fFloat}, "
			+ "    F_DOUBLE = #{field.fDouble}, "
			+ "    F_BIGDECIMAL = #{field.fBigDecimal}, "
			+ "    F_CLOB = #{field.fClob}, "
			+ "    F_BLOB = #{field.fBlob}, "
			+ "    F_DATE = #{field.fDate}, "
			+ "    F_TIME = #{field.fTime}, "
			+ "    F_TIMESTAMP = #{field.fTimestamp} "
			+ "WHERE ID = #{id}")
	public void updateField(@Param("id") BigInteger id, @Param("field") Field field);
	
	@Delete(  "DELETE FROM FIELD "
			+ "WHERE id = #{id}")
	public boolean deleteField(BigInteger id);

	@Select("  SELECT * "
			+ "FROM FIELD")
	public Collection<Field> listFields();
}
