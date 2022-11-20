package com.epam.ld.block2.springplay.repository;

import com.epam.ld.block2.springplay.model.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {

}
