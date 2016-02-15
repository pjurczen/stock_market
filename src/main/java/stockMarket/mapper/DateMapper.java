package stockMarket.mapper;

import org.joda.time.LocalDate;

import stockMarket.entity.DataEntity;

public class DateMapper {
    public static DataEntity map(LocalDate date) {
        if(date != null) {
            return new DataEntity(date.toString());
        }
        return null;
    }

    public static LocalDate map2To(DataEntity data) {
        String stringDate = data.getDate();
        String[] dateArray = stringDate.split("-");
        int year = 0;
        int month = 0;
        int day = 0;
        try {
            year = Integer.parseInt(dateArray[0]);
            month = Integer.parseInt(dateArray[1]);
            day = Integer.parseInt(dateArray[2]);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        LocalDate dateTo = new LocalDate(year, month, day);
        return dateTo;
    }
    
}
