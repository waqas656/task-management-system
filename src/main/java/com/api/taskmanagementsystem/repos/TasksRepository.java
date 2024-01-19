package com.api.taskmanagementsystem.repos;

import com.api.taskmanagementsystem.models.entities.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TasksRepository extends MongoRepository<Task, String> {

    @Query("{title: '?0'}")
    Task findTaskByTitle(String title);

    @Query("{'dueDate': { $gte: ?0, $lte: ?1 }, 'completed' : false}")
    List<Task> findAllDueDateTasks(String startDate, String endDate);

    @Query("{'completed' : true}")
    List<Task> getCompletedTasks();

//    @Query("""
//            [
//              {
//                '$search': {
//                  index: "default",
//                  text: {
//                    query: "?0",
//                    path: "title"
//                  }
//                }
//              }
//            ]
//            """)

    @Query("{'$text': {'$search': ?0}}")
    List<Task> searchTasks(String query);
}
