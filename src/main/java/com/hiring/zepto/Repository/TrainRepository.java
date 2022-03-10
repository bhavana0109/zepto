package com.hiring.zepto.Repository;

import com.hiring.zepto.Model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<Train
        , Integer> {
}
