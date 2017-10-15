package cat.tecnocampus.controllers;

import cat.tecnocampus.domain.Classroom;
import cat.tecnocampus.persintence.ClassroomDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ClassroomController")
public class ClassroomController {

    private final ClassroomDAO classroomDAO;

    public ClassroomController(ClassroomDAO classroomDAO){
        this.classroomDAO = classroomDAO;
    }

    public Classroom createClassroom(String name, int capacity, String orientation, boolean plug){
        Classroom classroom = new Classroom.ClassroomBuilder()
                .name(name)
                .capacity(capacity)
                .orientation(orientation)
                .plugs(plug)
                .build();
        insert(classroom);
        return classroom;
    }

    @Transactional
    public int insert(Classroom classroom){
        return  classroomDAO.insert(classroom);
    }

    public List<Classroom> findAll(){
        return classroomDAO.findAll();
    }

    public List<Classroom> findCapacityLargerThan(int capacity){
        return classroomDAO.findCapacityLargerThan(capacity);
    }

    public List<Classroom> findCapacityLowerThan(int capacity) {
        return  classroomDAO.findCapacityLowerThan(capacity);
    }

    public List<Classroom> findWithPlugs() {
        return classroomDAO.findWithPlugs();
    }

    public List<Classroom> findWithNoPlugs() {
        return  classroomDAO.findWithNoPlugs();
    }

    public int[] insertBatch(List<Classroom> classrooms) {
        return classroomDAO.insertBatch(classrooms);
    }


}
