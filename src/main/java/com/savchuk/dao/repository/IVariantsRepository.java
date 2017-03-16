package com.savchuk.dao.repository;

import com.savchuk.dao.entitties.Variant;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by home on 10.03.17.
 */
public interface IVariantsRepository extends CrudRepository<Variant, Long> {
}
