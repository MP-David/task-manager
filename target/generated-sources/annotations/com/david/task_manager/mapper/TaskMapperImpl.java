package com.david.task_manager.mapper;

import com.david.task_manager.domain.Task;
import com.david.task_manager.request.TaskPostRequestBody;
import com.david.task_manager.request.TaskPutRequestBody;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import javax.annotation.processing.Generated;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-11T09:02:46-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl implements TaskMapper {

    private final DatatypeFactory datatypeFactory;

    public TaskMapperImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public Task toTask(TaskPostRequestBody taskPostRequestBody) {
        if ( taskPostRequestBody == null ) {
            return null;
        }

        Task task = new Task();

        task.setTitle( taskPostRequestBody.getTitle() );
        task.setDescription( taskPostRequestBody.getDescription() );
        task.setEndDate( taskPostRequestBody.getEndDate() );
        task.setScore( taskPostRequestBody.getScore() );
        task.setStage( taskPostRequestBody.getStage() );

        task.setInitDate( java.time.LocalDate.now() );

        return task;
    }

    @Override
    public Task toTask(TaskPutRequestBody taskPutRequestBody) {
        if ( taskPutRequestBody == null ) {
            return null;
        }

        Task task = new Task();

        task.setTitle( taskPutRequestBody.getTitle() );
        task.setDescription( taskPutRequestBody.getDescription() );
        task.setEndDate( xmlGregorianCalendarToLocalDate( localDateTimeToXmlGregorianCalendar( taskPutRequestBody.getEndDate() ) ) );
        task.setScore( taskPutRequestBody.getScore() );
        task.setStage( taskPutRequestBody.getStage() );

        return task;
    }

    private XMLGregorianCalendar localDateTimeToXmlGregorianCalendar( LocalDateTime localDateTime ) {
        if ( localDateTime == null ) {
            return null;
        }

        return datatypeFactory.newXMLGregorianCalendar(
            localDateTime.getYear(),
            localDateTime.getMonthValue(),
            localDateTime.getDayOfMonth(),
            localDateTime.getHour(),
            localDateTime.getMinute(),
            localDateTime.getSecond(),
            localDateTime.get( ChronoField.MILLI_OF_SECOND ),
            DatatypeConstants.FIELD_UNDEFINED );
    }

    private static LocalDate xmlGregorianCalendarToLocalDate( XMLGregorianCalendar xcal ) {
        if ( xcal == null ) {
            return null;
        }

        return LocalDate.of( xcal.getYear(), xcal.getMonth(), xcal.getDay() );
    }
}
