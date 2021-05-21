import React, {Component} from 'react';
import ProjectTask from "./ProjectTasks/ProjectTask";

class Backlog extends Component {
    render() {
        const tasks = this.props.project_tasks_props;
        let todoTasks = [];
        let inprogressTasks = [];
        let doneTasks = [];
        tasks.forEach(task=>{
            if(task.status==="TO_DO"){
                todoTasks.push(task);
            }else if(task.status==="IN_PROGRESS"){
                inprogressTasks.push(task);
            }else
                doneTasks.push(task);
        })
        return (
            <div className="container">
                <div className="row">
                    <div className="col-md-4">
                        <div className="card text-center mb-2">
                            <div className="card-header bg-secondary text-white">
                                <h3>TO DO</h3>
                            </div>
                        </div>
                        {todoTasks.map(task=>{
                            return(
                                <ProjectTask key={task.id} task={task}/>
                            )
                        })}

                    </div>

                    <div className="col-md-4">
                        <div className="card text-center mb-2">
                            <div className="card-header bg-primary text-white">
                                <h3>In Progress</h3>
                            </div>

                        </div>
                        {inprogressTasks.map(task=>{
                            return(
                                <ProjectTask key={task.id} task={task}/>
                            )
                        })}
                    </div>

                    <div className="col-md-4">
                        <div className="card text-center mb-2">
                            <div className="card-header bg-success text-white">
                                <h3>Done</h3>
                            </div>

                        </div>
                        {doneTasks.map(task=>{
                            return(
                                <ProjectTask key={task.id} task={task}/>
                            )
                        })}

                    </div>

                </div>
            </div>
        );
    }
}

export default Backlog;