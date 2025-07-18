package br.com.grupodagostini.dagostini_infraction_api.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface DateTimeMapper {

    @Named("dateToOffsetDateTime")
    default OffsetDateTime dateToOffsetDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().atOffset(ZoneOffset.UTC);
    }

    @Named("offsetDateTimeToDate")
    default Date offsetDateTimeToDate(OffsetDateTime offsetDateTime) {
        if (offsetDateTime == null) {
            return null;
        }
        return Date.from(offsetDateTime.toInstant());
    }
}
