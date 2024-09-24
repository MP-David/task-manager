package com.david.task_manager.mapper;

import com.david.task_manager.domain.Task;
import com.david.task_manager.dto.TaskDTO;
import com.david.task_manager.request.TaskPostRequestBody;
import com.david.task_manager.request.TaskPutRequestBody;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-24T09:46:51-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class TaskMapperImpl extends TaskMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;
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

        task.setResponsible( mapIdToUsuario( taskPostRequestBody.getResponsibleId() ) );
        task.setTitle( taskPostRequestBody.getTitle() );
        task.setDescription( taskPostRequestBody.getDescription() );
        task.setEndDate( taskPostRequestBody.getEndDate() );
        task.setScore( taskPostRequestBody.getScore() );
        task.setPriority( taskPostRequestBody.getPriority() );
        task.setStage( usuarioMapper.mapStringToStageEnum( taskPostRequestBody.getStage() ) );

        task.setInitDate( java.time.LocalDate.now() );

        return task;
    }

    @Override
    public Task toTask(TaskPutRequestBody taskPutRequestBody) {
        if ( taskPutRequestBody == null ) {
            return null;
        }

        Task task = new Task();

        task.setResponsible( mapIdToUsuario( taskPutRequestBody.getResponsibleId() ) );
        task.setTitle( taskPutRequestBody.getTitle() );
        task.setDescription( taskPutRequestBody.getDescription() );
        task.setEndDate( xmlGregorianCalendarToLocalDate( localDateTimeToXmlGregorianCalendar( taskPutRequestBody.getEndDate() ) ) );
        task.setScore( taskPutRequestBody.getScore() );
        task.setStage( taskPutRequestBody.getStage() );

        return task;
    }

    @Override
    public TaskDTO toTaskDTO(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setResponsible( usuarioMapper.toUsuarioLimitadoDTO( task.getResponsible() ) );
        taskDTO.setId( task.getId() );
        taskDTO.setTitle( task.getTitle() );
        taskDTO.setDescription( task.getDescription() );
        taskDTO.setInitDate( task.getInitDate() );
        taskDTO.setEndDate( task.getEndDate() );
        taskDTO.setScore( task.getScore() );
        taskDTO.setPriority( task.getPriority() );
        taskDTO.setStage( task.getStage() );

        return taskDTO;
    }

    @Override
    public List<TaskDTO> toTaskDTOList(List<Task> tasks) {
        if ( tasks == null ) {
            return null;
        }

        List<TaskDTO> list = new ArrayList<TaskDTO>( tasks.size() );
        for ( Task task : tasks ) {
            list.add( toTaskDTO( task ) );
        }

        return list;
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
