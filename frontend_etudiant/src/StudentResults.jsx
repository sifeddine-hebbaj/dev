import React from 'react';
import Result from "./components/resultats/StudentResults.jsx";
import Header from "./components/Header/header.jsx";
import NotificationsEtudiant from "./components/NotificationEtudiant/Notifications.jsx"

const StudentResults = () => {
    return (
            <div className="container">
                <Header/>
                <NotificationsEtudiant />
                <Result/>
            </div>
    )
}


export default StudentResults;
