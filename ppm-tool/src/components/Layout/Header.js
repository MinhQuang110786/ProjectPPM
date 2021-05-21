import React, { Component } from 'react';
import { Link } from 'react-router-dom';
export default class Header extends Component {
  render() {
    return (
      <nav className="navbar navbar-expand-sm navbar-dark bg-primary mb-4">
        <div className="container">
          <Link className="navbar-brand" to="/dashboard">
            Personal Project Management Tool
            </Link>
          <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#mobile-nav">
            <span className="navbar-toggler-icon" />
          </button>

          <div className="collapse navbar-collapse" id="mobile-nav">
            <ul className="navbar-nav mr-auto">
              <li className="nav-item">
                <a className="nav-link" href="/dashboard">Dashboard</a>
              </li>
            </ul>

            <ul className="navbar-nav ml-auto">
              <li className="nav-item">
                <a className="nav-link " href="register.html">Sign Up</a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="login.html">Login</a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    )
  }
}
