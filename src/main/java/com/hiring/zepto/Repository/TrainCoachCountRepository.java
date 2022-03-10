package com.hiring.zepto.Repository;

import com.hiring.zepto.Model.TrainCoachCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface TrainCoachCountRepository extends JpaRepository<TrainCoachCount, Integer> {


    TrainCoachCount findByTrainNumberAndCoachTypeId(Integer trainNumber, Integer coachTypeId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE TrainCoachCount t SET t.noOfCoaches=:noOfCoaches WHERE t.trainNumber=:trainNumber AND t.coachTypeId=:coachTypeId")
    void changeCoachesCount(@Param("trainNumber") Integer trainNumber, @Param("coachTypeId") Integer coachTypeId, @Param("noOfCoaches") int noOfCoaches);

}