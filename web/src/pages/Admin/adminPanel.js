import React, {useState} from 'react'
import { useNavigate } from 'react-router-dom';
import { encrypt } from '../Users/encryption';
import './adminPanel.css';


function AdminPanel(props) {

    const adminUsername = "Admin321";
    const teamMembers = [
        { id: 1, name: "John", icon: "/adminIcon2.png" },
        { id: 2, name: "Jane", icon: "/adminIcon3.png" },
        { id: 3, name: "Doe", icon: "/adminIcon4.png" }
    ];
    const [searchTerm, setSearchTerm] = useState('');
    const navigate = useNavigate();

    if (localStorage.getItem('isAdmin') != 'true') {
        return <div>You do not have the permission to access this page!!</div>;
    }

    const goToPromotion = () =>{
        navigate('/promotion');
    }

    const goToMoiveDetail = () =>{
        navigate('/promotion');
    }

    const goToMoiveManagement = () =>{
        navigate('/moviemanagement');
    }

    const goToScheduleMoive = () =>{
        navigate('/scheduleMovie');
    }

    const goToUserManagement = () =>{
        navigate('/usermanagement');
    }



 
  return (
    <div>
        {/* basically this selection is builing the top bar include Admin Icon, Admin Name */}
        {/* and a seaching bar for user to seach the key word of permissions */}
        <selection className = "top-bar">
            {/* this part shows the Admin's Icon and username*/}
            <div className="admin-info">
                <img src={require('./adminIcon1.png')}alt="Admin Icon" className="admin-icon"/>
                <span className="admin-username">{adminUsername}</span>
            </div>
            {/* this part is for admin to search the key word of the permisson */}
            {/* the priority is low tho, we can delete it if it's not nesscesary */}
            {/* <div className="search-bar-container">
                        <input 
                            type="text" 
                            placeholder="Search anything..." 
                            value={searchTerm} 
                            onChange={(e) => setSearchTerm(e.target.value)}
                            />
            </div> */}
        </selection>

        {/* this selection is for admin to check his team, and able to add or delete the admin */}
        <div className="team-section">
                {/* <h2>My Team</h2>
                <div className="team-grid">
                    {teamMembers.map(member => (
                        <div key={member.id} className="team-member">
                            <img src={member.icon} alt={member.name} />
                            <p>{member.name}</p>
                        </div>
                    ))}
                </div> */}
                {/* <button onClick={() => alert('Add New Admin Functionality Here!')}>Add New Admin</button> */}
                <button onClick={() => navigate('/adminsignup')}>Add New Admin</button>
            </div>

        <div>

        </div>
        {/* this selection is working on the admin's permission */}
        {/* admin's permission should include send movie promotion, view customer information, update ticket price
            update movie information  */}
        <div className="adminpermission">
                <h2>Admin Permissions</h2>
                <div className="permissions-grid">
                <button className="permission-box" onClick={goToMoiveDetail}>Update Movie Details
                </button>
                <button className="permission-box" onClick={goToScheduleMoive}>Schedule Movie
                </button>
                <button className="permission-box" onClick={goToUserManagement}>User Management
                </button>
                <button className="permission-box" onClick={goToMoiveManagement}>Add Movie
                </button>
                <button className="permission-box" onClick={goToPromotion}>Promotion & Ticket Management
                </button>
                </div>
            </div>
        </div>

    );
}

export default AdminPanel;
