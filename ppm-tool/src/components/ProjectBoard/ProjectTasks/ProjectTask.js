import React, {Component} from 'react';
import {Link} from 'react-router-dom';
import {deleteProjectTask} from "../../../actions/backlogAction";
import PropTypes from "prop-types";
import {connect} from "react-redux";

class ProjectTask extends Component {

    onDeleteClick = (backlog_id,pt_id)=>{
        this.props.deleteProjectTask(backlog_id,pt_id);
    }

    render() {
        const {projectSequence,summary,acceptanceCriteria,priority,projectIdentifier} = this.props.task;
        let priorityClass="";
        let priorityString="";
        if(priority===1){
            priorityClass = "bg-danger text-light";
            priorityString="HIGH";
        }else if(priority===2){
            priorityClass = "bg-warning text-light";
            priorityString="MEDIUM";
        }else {
            priorityClass = "bg-info text-light";
            priorityString="LOW";
        }
        return (
            <div className="card mb-1 bg-light">

                <div className={`card-header text-primary ${priorityClass}`}>
                    ID: {projectSequence} -- Priority: {priorityString}
                </div>
                <div className="card-body bg-light">
                    <h5 className="card-title">{summary}</h5>
                    <p className="card-text text-truncate ">
                        {acceptanceCriteria}
                    </p>
                    <Link to={`/updateProjectTask/${projectIdentifier}/${projectSequence}`} className="btn btn-primary">
                        View / Update
                    </Link>

                    <button className="btn btn-danger ml-4" onClick={this.onDeleteClick.bind(this,projectIdentifier,projectSequence)}>
                        Delete
                    </button>
                </div>
            </div>
        );
    }
}

ProjectTask.propTypes = {
    deleteProjectTask: PropTypes.func.isRequired
}

export default connect(null,{deleteProjectTask})(ProjectTask);