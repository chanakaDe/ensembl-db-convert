/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.db.convert.repository;

import com.db.convert.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chanu1993@gmail.com
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

}
