package stockMarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import stockMarket.entity.DataEntity;

@Repository
public interface StockRepository extends JpaRepository<DataEntity, Long>{
    
    @Query("select data from DataEntity data where data.date = :date )")
    public DataEntity findByDate(@Param("date") String date);
}
