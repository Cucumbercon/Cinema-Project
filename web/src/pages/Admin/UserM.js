import React from 'react';
import './UserM.css';

function UserManagement() {
    const users = [
        {id: 1, name: "John Doe", email: "john@example.com", status: "Active", lastOrder: "2023-04-10"},
        // Add more users as needed
    ];

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
                                <button className="edit-btn">Edit Profile</button>
                                <button className="edit-order-btn">Edit Order</button>
                                <button className="delete-btn">Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default UserManagement;

