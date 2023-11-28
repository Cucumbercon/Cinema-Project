import React, { useState, useEffect } from 'react'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './UserM.css';

function UserManagement() {
    const [users, setUsers] = useState([
        {id: 1, name: "John Doe", email: "john@example.com", status: "Active", lastOrder: "2023-04-10"},
        {id: 2, name: "Elon Musk", email: "MuskX@example.com", status: "Active", lastOrder: "2023-05-21"}, 
    ]);

    // const [users, setUsers] = useState([]);
    useEffect(() => {
    
    fetch('http://localhost:8000/api/getUsers', {
            method: 'GET',
        })
            .then((response) => response.text()) // JSON
            .then((data) => {
                const parsedData = JSON.parse(data);
                console.log('availableMoviedata:', parsedData);
            })
            .catch((error) => {
                console.error('Error sending message to Spring:', error);
            });

        }, []);

    // const removeUser = async (userId) => {
    //     try {
    //         await axios.delete(`/api/users/${userId}`); // API call to delete the user
    //         setUsers(prevUsers => prevUsers.filter(user => user.id !== userId));
    //     } catch (error) {
    //         console.error('Error removing user:', error);
    //     }
    // };

    const removeUser = (userId) => {        
        setUsers(users.filter(user => user.id !== userId));
    };
    const editProfile = (userId) => {
        // Navigate to edit profile page with the user's ID
        navigate('/updateprofile');
    };

    const editOrder = (userId) => {
        // Navigate to edit order page with the user's ID
        navigate('/updateprofile');
    };

    //need to update the backend to handle the suspension status of a user.
    const toggleUserStatus = async (userId, isActive) => {
        try {
            await axios.post(`/api/users/${userId}/toggleStatus`, { active: !isActive }); // Replace with actual API endpoint
            setUsers(prevUsers => prevUsers.map(user => user.id === userId ? { ...user, status: isActive ? 'Inactive' : 'Active' } : user));
        } catch (error) {
            console.error('Error toggling user status:', error);
        }
    };
    

    const navigate = useNavigate();


    
    return (
        <div className="user-management-container">
            <h1>User Management</h1>
            <table className="users-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Status</th>
                        <th>Last Order</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {users.map(user => (
                        <tr key={user.id}>
                            <td>{user.id}</td>
                            <td>{user.name}</td>
                            <td>{user.email}</td>
                            <td>{user.status}</td>
                            <td>{user.lastOrder}</td>
                            <td>
                                <button className="edit-btn" onClick={editProfile}>Edit Profile</button>
                                <button className="edit-order-btn" onClick={editOrder}>Edit Order</button>
                                <button className="toggle-status-btn" onClick={() => toggleUserStatus(user.id, user.status === 'Active')}>{user.status === 'Active' ? 'Suspend' : 'Unsuspend'}</button>
                                <button className="remove-btn" onClick={() => removeUser(user.id)}>Remove</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default UserManagement;

