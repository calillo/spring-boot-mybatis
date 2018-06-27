package com.rest.api.db.mybatis.mapper;

import java.io.Serializable;
import java.util.Collection;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.rest.api.model.Car;

@Mapper
public interface CarDaoMapper extends Serializable {

	@Select(  "SELECT * "
			+ "FROM CAR "
			+ "WHERE id = #{id}")
	public Car getCar(long id);

	@Insert(  "INSERT INTO CAR (BRAND, MODEL, VERSION, PRICE) "
			+ "VALUES (#{brand}, #{model}, #{version}, #{price})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void addCar(Car car);
	
	@Update(  "UPDATE CAR "
			+ "SET BRAND = #{car.brand},"
			+ "    MODEL = #{car.model}, "
			+ "    VERSION = #{car.version}, "
			+ "    PRICE = #{car.price}, "
			+ "    UPDATE_DATE = #{car.updateDate} "
			+ "WHERE ID = #{id}")
	public void updateCar(@Param("id") long id, @Param("car") Car car);

	@Delete(  "DELETE FROM CAR "
			+ "WHERE id = #{id}")
	public boolean deleteCar(long id);

	@Select("  SELECT * "
			+ "FROM CAR")
	public Collection<Car> listCars();
}
