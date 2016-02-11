package stockMarket.mapper;

import org.joda.time.LocalDate;

import stockMarket.entity.DateEntity;

public class DateMapper {
    public static DateEntity map(LocalDate date) {
        if(date != null) {
            return new DateEntity(date.toString());
        }
        return null;
    }
    
}
