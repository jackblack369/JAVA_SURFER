package com.ignite.spring.repository;

import com.ignite.spring.dto.EmployeeDTO;
import org.apache.ignite.springdata.repository.IgniteRepository;
import org.apache.ignite.springdata.repository.config.RepositoryConfig;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryConfig(cacheName = "willCache")
public interface EmployeeRepository extends IgniteRepository<EmployeeDTO, Integer> {

    EmployeeDTO getEmployeeDTOById(Integer id);
}
