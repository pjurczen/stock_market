package stockMarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import stockMarket.entity.DateEntity;
import stockMarket.entity.StockEntity;

@Repository
public interface StockRepository extends JpaRepository<DateEntity, StockEntity>{
    
    @Query("select date from DateEntity date where date.date = :date )")
    public DateEntity findByDate(@Param("date") String date);
}
